package io.github.spaceymonk.khb.centralunit.consumer;

import io.github.spaceymonk.khb.centralunit.dto.SensorDataDto;
import io.github.spaceymonk.khb.centralunit.service.SensorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class SensorDataConsumer {

    private final SensorService sensorService;

    @KafkaListener(topics = "${app.kafka.topic.sensor_data.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(SensorDataDto sensorDataDto) {
        log.info("Sensor data arrived: {}", sensorDataDto);
        sensorService.updateData(sensorDataDto);
    }
}
