package com.sping.solr.banchmarking.controller;

import com.sping.solr.banchmarking.client.OkHttp3Client;
import com.sping.solr.banchmarking.client.WebFluxClient;
import com.sping.solr.banchmarking.pojo.SolrResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.io.IOException;

@RestController
public class Controller {

    @Autowired
    private WebFluxClient webFluxClient;

    @Autowired
    private OkHttp3Client okHttp3Client;

    @GetMapping("/webFlux")
    public ResponseEntity<Flux<SolrResponse>> webFlux() {
        return new ResponseEntity<>(webFluxClient.callAsync(), HttpStatus.OK);
    }

    @GetMapping("/okHttp3Client")
    public ResponseEntity<SolrResponse> okHttp3Client() throws IOException {
        return new ResponseEntity<>(okHttp3Client.callSync(), HttpStatus.OK);
    }

}
