package com.mykah.s3learn.utilities;

import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.io.IOException;
import java.io.InputStream;

public class S3Util {

    public void saveS3ObjectInEuWest3InMykahBucket(String filenameForUpload, InputStream inputStream)
    throws S3Exception, AwsServiceException, SdkClientException, IOException {

        final String BUCKET = "experimental-bucket-mykah";
        Region mainRegion = Region.EU_WEST_3;

        S3Client mainS3Client = S3Client.builder()
                .region(mainRegion)
                .build();

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(BUCKET)
                .key(filenameForUpload)
                .build();

        mainS3Client.putObject(putObjectRequest, RequestBody.fromInputStream(inputStream, inputStream.available()));
    }
}
