package com.mallang.hightrafficcommunity.service;

import com.mallang.hightrafficcommunity.config.AWSConfig;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;

@Service
public class SnsService {

    AWSConfig awsConfig;

    public SnsService(AWSConfig awsConfig) {
        this.awsConfig = awsConfig;
    }

    /* AWS 자격증명 제공자 생성 */
    public AwsCredentialsProvider getAwsCredentials(String accessKey, String secretAccessKey) {
        // accessKey + secretAccessKey -> AWS에 액세스 할 수 있는 자격증명을 생성
        AwsBasicCredentials awsBasicCredentials = AwsBasicCredentials.create(accessKey, secretAccessKey);
        return () -> awsBasicCredentials;
    }

    /* SNS 클라이언트 생성 */
    public SnsClient getSnsClient() {
        return SnsClient.builder()
                .credentialsProvider(
                                getAwsCredentials(awsConfig.getAwsAccessKey(), awsConfig.getAwsSecretKey())
                ).region(Region.of(awsConfig.getAwsRegion()))
                .build();
    }

}