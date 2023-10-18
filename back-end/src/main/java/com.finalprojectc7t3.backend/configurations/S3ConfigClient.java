package com.finalprojectc7t3.backend.configurations;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class S3ConfigClient {

    private final static String AWS_URL= "http://%s.s3.amazonaws.com/%s";

    @Value("${cloud.aws.credentials.accessKey}")
    private String accessKey;

    @Value("${cloud.aws.credentials.secretKey}")
    private String accessSecret;

    @Value("${cloud.aws.region.static}")
    private String region;

    @Value("${cloud.aws.bucket.name}")
    private String bucket;

    @Bean
    public AmazonS3 s3Cliente() {
        AWSCredentials credentials = new BasicAWSCredentials(accessKey, accessSecret);
        return AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(region).build();
    }

    @Bean
    public String getNombreBucketS3() {
        return this.bucket;
    }

    @Bean
    public String getUrlAws() {
        return AWS_URL;
    }
}
