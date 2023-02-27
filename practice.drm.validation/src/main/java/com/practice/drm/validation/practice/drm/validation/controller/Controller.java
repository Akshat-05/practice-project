package com.practice.drm.validation.practice.drm.validation.controller;

import com.practice.drm.validation.practice.drm.validation.client.OkHttp3Client;
import com.practice.drm.validation.practice.drm.validation.pojo.Doc;
import com.practice.drm.validation.practice.drm.validation.pojo.SolrResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.LongStream;

@Slf4j
@RestController
public class Controller {

    @Autowired
    private OkHttp3Client okHttp3Client;

    @GetMapping("/drmValidation")
    public ResponseEntity<String> okHttp3Client(@RequestParam int start, @RequestParam int row,
    @RequestParam String assetType, @RequestParam String assetSubtype) throws IOException {
        SolrResponse response = okHttp3Client.callSync(start, row, assetType, assetSubtype);
        List<Doc> result = response.getResponse().getDocs();
        AtomicInteger countBothNull = new AtomicInteger(0);
        AtomicInteger countDrmKeyOnlyNull = new AtomicInteger(0);
        AtomicInteger countResourceOnlyNull = new AtomicInteger(0);
        AtomicInteger countMissMatch = new AtomicInteger(0);
        System.out.println("size:  "  + result.size());
        LongStream.range(0, row).parallel().forEach(i -> {
            int index = (int) i;
            if (result.get(index).getDrmKeyId() == null) {
                if (result.get(index).getDrmResourceId() != null) {
                    countDrmKeyOnlyNull.getAndIncrement();
                    System.out.print(result.get(index).getId() + "  ");
                } else {
                    countBothNull.getAndIncrement();
                }
            } else if (!result.get(index).getDrmKeyId().equals(result.get(index).getDrmResourceId())) {
                System.out.print(result.get(index).getId() + "  ");
                countMissMatch.getAndIncrement();
            } else if (result.get(index).getDrmResourceId()==null) {
                countResourceOnlyNull.getAndIncrement();
            }
        });
        System.out.println();
        System.out.println("countBothNull " +  countBothNull);
        System.out.println("countDrmKeyOnlyNull " +  countDrmKeyOnlyNull);
        System.out.println("countResourceOnlyNull " + countResourceOnlyNull);
        System.out.println("countMissMatch " + countMissMatch);
        return new ResponseEntity<>("completed", HttpStatus.OK);
    }

}
