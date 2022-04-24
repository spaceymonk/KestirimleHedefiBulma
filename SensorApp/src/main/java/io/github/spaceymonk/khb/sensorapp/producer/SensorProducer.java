package io.github.spaceymonk.khb.sensorapp.producer;

import io.github.spaceymonk.khb.sensorapp.common.KafkaMessageCallback;
import io.github.spaceymonk.khb.sensorapp.dto.SensorDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

@Component
@RequiredArgsConstructor
public class SensorProducer {

    @Value("${app.kafka.topic.sensor.name}")
    private String topicName;

    private final KafkaTemplate<String, SensorDto> kafkaTemplate;

    public void sendMessage(SensorDto sensor) {
        ListenableFuture<SendResult<String, SensorDto>> future = kafkaTemplate.send(topicName, sensor);
        future.addCallback(new KafkaMessageCallback<>(sensor));
    }
}
