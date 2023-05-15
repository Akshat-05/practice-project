package com.practices3.practices3.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RenameObjectService {

    @Value("${aws.bucket.name}")
    private String bucketName;

    @Autowired
    private AmazonS3 amazonS3;

    public void renameObject(String key){
        amazonS3.copyObject(bucketName, key, bucketName, key);
    }

    public void renameObject(String key, String filename){
        // remove old fileName and add new fileName
        String newKey = key.substring(0,key.lastIndexOf("/")).concat(filename);
        amazonS3.copyObject(bucketName, key, bucketName, newKey);
        amazonS3.deleteObject(bucketName, key);
    }

}
