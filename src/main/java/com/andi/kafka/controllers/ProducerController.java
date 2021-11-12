package com.andi.kafka.controllers;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.andi.Mmessage;
import com.andi.kafka.producers.KafkaAvroProducer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ProducerController {

    @Value("${kafka.topic}")
    private String topic;

    private final KafkaAvroProducer kafkaProducer;

    @PostMapping
    public Mono<String> produce(@RequestBody Mmessage message) throws InterruptedException, ExecutionException {

	log.info("Recieved message to produce to Kafka: " + message);

	var result = kafkaProducer.send(topic, message).get();

	return Mono.just(result.toString());
    }

}
