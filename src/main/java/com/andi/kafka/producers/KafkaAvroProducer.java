package com.andi.kafka.producers;

import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;

import com.andi.Mmessage;

public interface KafkaAvroProducer {
    ListenableFuture<SendResult<String, Mmessage>> send(String topicName, Mmessage message);
}
