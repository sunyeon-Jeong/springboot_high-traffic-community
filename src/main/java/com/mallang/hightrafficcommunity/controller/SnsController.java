package com.mallang.hightrafficcommunity.controller;

import com.mallang.hightrafficcommunity.config.AWSConfig;
import com.mallang.hightrafficcommunity.service.SnsService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.*;

@RestController
@Log4j2
public class SnsController {

    private final AWSConfig awsConfig;

    private final SnsService snsService;

    public SnsController(AWSConfig awsConfig, SnsService snsService) {
        this.awsConfig = awsConfig;
        this.snsService = snsService;
    }

    /* 토픽 생성 */
    @PostMapping("sns/create-topic")
    public ResponseEntity<String> createTopic(@RequestParam final String topicName) {

        final CreateTopicRequest createTopicRequest = CreateTopicRequest.builder()
                .name(topicName)
                .build();

        SnsClient snsClient = snsService.getSnsClient();

        final CreateTopicResponse createTopicResponse = snsClient.createTopic(createTopicRequest);

        if (!createTopicResponse.sdkHttpResponse().isSuccessful()) {
            throw getResponseStatusException(createTopicResponse);
        }

        log.info("topic name = " + createTopicResponse.topicArn());
        log.info("topic list = " + snsClient.listTopics());
        snsClient.close();

        return new ResponseEntity<>("TOPIC CREATED SUCCESSFULLY", HttpStatus.OK);

    }

    /* 토픽 구독 */
    @PostMapping("sns/subscribe")
    public ResponseEntity<String> topicSubscribe(@RequestParam final String endpoint,
                                                                                @RequestParam final String topicArn) {

        final SubscribeRequest subscribeRequest = SubscribeRequest.builder()
                .protocol("https")
                .topicArn(topicArn)
                .endpoint(endpoint)
                .build();

        SnsClient snsClient = snsService.getSnsClient();

        final SubscribeResponse subscribeResponse = snsClient.subscribe(subscribeRequest);

        if (!subscribeResponse.sdkHttpResponse().isSuccessful()) {
            throw getResponseStatusException(subscribeResponse);
        }

        log.info("topicARN to subscribe = " + subscribeResponse.subscriptionArn());
        log.info("subscription list = " + snsClient.listSubscriptions());
        snsClient.close();

        return new ResponseEntity<>("TOPIC SUBSCRIBED SUCCESSFULLY", HttpStatus.OK);

    }

    /* Exception */
    private ResponseStatusException getResponseStatusException(SnsResponse snsResponse) {
        return new ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR, snsResponse.sdkHttpResponse().statusText().get()
        );
    }

}