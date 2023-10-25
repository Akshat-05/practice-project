package com.practice.bucket4j.caffeine.config;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.Expiry;
import io.github.bucket4j.TimeMeter;
import io.github.bucket4j.distributed.proxy.AbstractProxyManager;
import io.github.bucket4j.distributed.proxy.ClientSideConfig;
import io.github.bucket4j.distributed.remote.CommandResult;
import io.github.bucket4j.distributed.remote.MutableBucketEntry;
import io.github.bucket4j.distributed.remote.RemoteBucketState;
import io.github.bucket4j.distributed.remote.Request;
import org.springframework.beans.factory.annotation.Configurable;

import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Configurable
public class CaffeineProxyManager<K> extends AbstractProxyManager<K> {

    private final Cache<K, RemoteBucketState> cache;

    public CaffeineProxyManager(Caffeine<? super K, ? super RemoteBucketState> builder, Duration keepAfterRefillDuration) {
        this(builder, keepAfterRefillDuration, ClientSideConfig.getDefault());
    }

    public CaffeineProxyManager(Caffeine<? super K, ? super RemoteBucketState> builder, Duration keepAfterRefillDuration, ClientSideConfig clientSideConfig) {
        super(clientSideConfig);
        this.cache = builder
                .expireAfter(new Expiry<K, RemoteBucketState>() {
                    @Override
                    public long expireAfterCreate(K key, RemoteBucketState bucketState, long currentTime) {
                        long currentTimeNanos = getCurrentTime(clientSideConfig);
                        long nanosToFullRefill = bucketState.calculateFullRefillingTime(currentTimeNanos);
                        return nanosToFullRefill + keepAfterRefillDuration.toNanos();
                    }

                    @Override
                    public long expireAfterUpdate(K key, RemoteBucketState bucketState, long currentTime, long currentDuration) {
                        long currentTimeNanos = getCurrentTime(clientSideConfig);
                        long nanosToFullRefill = bucketState.calculateFullRefillingTime(currentTimeNanos);
                        return nanosToFullRefill + keepAfterRefillDuration.toNanos();
                    }

                    @Override
                    public long expireAfterRead(K key, RemoteBucketState bucketState, long currentTime, long currentDuration) {
                        long currentTimeNanos = getCurrentTime(clientSideConfig);
                        long nanosToFullRefill = bucketState.calculateFullRefillingTime(currentTimeNanos);
                        return nanosToFullRefill + keepAfterRefillDuration.toNanos();
                    }
                })
                .build();
    }

    public Cache<K, RemoteBucketState> getCache() {
        return cache;
    }

    @Override
    public <T> CommandResult<T> execute(K key, Request<T> request) {
        CommandResult<T>[] resultHolder = new CommandResult[1];

        cache.asMap().compute(key, (K k, RemoteBucketState previousState) -> {
            Long clientSideTime = request.getClientSideTime();
            long timeNanos = clientSideTime != null ? clientSideTime : System.currentTimeMillis() * 1_000_000;
            RemoteBucketState[] stateHolder = new RemoteBucketState[] {
                    previousState == null ? null : previousState.copy()
            };
            MutableBucketEntry entry = new MutableBucketEntry() {
                @Override
                public boolean exists() {
                    return stateHolder[0] != null;
                }
                @Override
                public void set(RemoteBucketState state) {
                    stateHolder[0] = state;
                }
                @Override
                public RemoteBucketState get() {
                    return stateHolder[0];
                }
            };
            resultHolder[0] = request.getCommand().execute(entry, timeNanos);
            return stateHolder[0];
        });

        return resultHolder[0];
    }

    @Override
    public boolean isAsyncModeSupported() {
        return true;
    }

    @Override
    public <T> CompletableFuture<CommandResult<T>> executeAsync(K key, Request<T> request) {
        CommandResult<T> result = execute(key, request);
        return CompletableFuture.completedFuture(result);
    }

    @Override
    public void removeProxy(K key) {
        cache.asMap().remove(key);
    }

    @Override
    protected CompletableFuture<Void> removeAsync(K key) {
        cache.asMap().remove(key);
        return CompletableFuture.completedFuture(null);
    }

    private static long getCurrentTime(ClientSideConfig clientSideConfig) {
        Optional<TimeMeter> clock = clientSideConfig.getClientSideClock();
        return clock.isPresent() ? clock.get().currentTimeNanos() : System.currentTimeMillis() * 1_000_000;
    }

}
