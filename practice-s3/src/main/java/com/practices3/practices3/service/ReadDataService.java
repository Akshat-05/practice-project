package com.practices3.practices3.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReadDataService {

    @Autowired
    private AmazonS3 amazonS3;

    public List<String> fetchFile() {
        List<Bucket> buckets = amazonS3.listBuckets();
        List<String> bucketsName = new ArrayList<>();
        for(Bucket bucket : buckets) {
            bucketsName.add(bucket.getName());
            //System.out.println(bucket.getName());
        }
        return bucketsName;
        //return amazonS3.getObject(bucketName, fileKey).getObjectContent();
    }

}
