package com.andi.kafka.producers;

import java.util.UUID;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import com.andi.Mmessage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaAvroProducerImpl implements KafkaAvroProducer {

    private final KafkaTemplate<String, Mmessage> kafkaTemplate;

    @Override
    public ListenableFuture<SendResult<String, Mmessage>> send(String topicName, Mmessage message) {
	final Mmessage mmessage = Mmessage.newBuilder().setId(UUID.randomUUID().toString())
		.setPayload("BOOM SHAKA LAKA").build();

	final ProducerRecord<String, Mmessage> record = new ProducerRecord<String, Mmessage>(topicName, mmessage);

	log.info("Producing record: " + record);

	return kafkaTemplate.send(record);
    }

}
