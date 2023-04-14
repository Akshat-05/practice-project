package com.sping.solr.banchmarking.client;

import com.sping.solr.banchmarking.constants.URLConstants;
import com.sping.solr.banchmarking.pojo.SolrResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Slf4j
@Component
public class WebFluxClient {

    @Autowired
    private WebClient webClient;
//    public void WebFluxClient() {
//        webClient = WebClient.create();
//    }
    public Flux<SolrResponse> callAsync(){
        return webClient.get()
                .uri(URLConstants.SOLR_URL)
                .retrieve()
                .bodyToFlux(SolrResponse.class).timeout(Duration.ofSeconds(1));
    }

}
