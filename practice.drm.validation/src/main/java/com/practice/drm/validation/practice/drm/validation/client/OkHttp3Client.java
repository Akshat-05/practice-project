/*
 * Copyright (C) 2022 @ ZEE
 */

package com.practice.drm.validation.practice.drm.validation.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.drm.validation.practice.drm.validation.constants.URLConstants;
import com.practice.drm.validation.practice.drm.validation.pojo.SolrResponse;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Slf4j
@Component
public class OkHttp3Client {

    @Autowired
    private OkHttpClient client;

    @Autowired
    private ObjectMapper objectMapper;


    public SolrResponse callSync(int start, int row, String assetType, String assetSubtype) throws IOException {
        String url = String.format(URLConstants.SOLR_URL, assetType, assetSubtype, row, start);
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        Response response = client.newCall(request).execute();
        return objectMapper.readValue(response.body().string(), SolrResponse.class);

    }
}
