package com.madeleine.madeleine.service;

import java.io.InputStream;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class AWSS3UploadService implements UploadService{
    private final AmazonS3 amazonS3;
    private final S3Component component;

    @Override
    public void uploadFile(InputStream inputStream, ObjectMetadata objectMetadata, String filename) {
        amazonS3.putObject(new PutObjectRequest(component.getBucket(), filename, inputStream, objectMetadata).withCannedAcl(CannedAccessControlList.PublicRead));
    }

    @Override
    public String getFileUrl(String filename){
        return amazonS3.getUrl(component.getBucket(), filename).toString();
    }

    @Override
    public void deleteFile(String filename){
        amazonS3.deleteObject(new DeleteObjectRequest(component.getBucket(), filename));
    }
}
