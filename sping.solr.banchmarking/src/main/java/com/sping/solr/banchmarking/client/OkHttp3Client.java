/*
 * Copyright (C) 2022 @ ZEE
 */

package com.sping.solr.banchmarking.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sping.solr.banchmarking.constants.URLConstants;
import com.sping.solr.banchmarking.pojo.SolrResponse;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class OkHttp3Client {

    @Autowired
    private OkHttpClient client;

    @Autowired
    private ObjectMapper objectMapper;


    public SolrResponse callSync() throws IOException {
        Request request = new Request.Builder()
                .url(URLConstants.SOLR_URL)
                .get()
                .build();
        Response response = client.newCall(request).execute();
        return objectMapper.readValue(response.body().string(), SolrResponse.class);

    }
}
