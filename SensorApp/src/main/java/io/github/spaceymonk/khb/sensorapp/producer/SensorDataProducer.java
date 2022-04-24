package io.github.spaceymonk.khb.sensorapp.producer;

import io.github.spaceymonk.khb.sensorapp.common.KafkaMessageCallback;
import io.github.spaceymonk.khb.sensorapp.dto.SensorDataDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

@Component
@RequiredArgsConstructor
public class SensorDataProducer {

    @Value("${app.kafka.topic.sensor_data.name}")
    private String topicName;

    private final KafkaTemplate<String, SensorDataDto> kafkaTemplate;

    public void sendMessage(SensorDataDto sensorData) {
        ListenableFuture<SendResult<String, SensorDataDto>> future = kafkaTemplate.send(topicName, sensorData);
        future.addCallback(new KafkaMessageCallback<>(sensorData));
    }
}
