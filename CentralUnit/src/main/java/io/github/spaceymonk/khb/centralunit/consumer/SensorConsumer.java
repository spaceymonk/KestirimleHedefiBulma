package io.github.spaceymonk.khb.centralunit.consumer;

import io.github.spaceymonk.khb.centralunit.dto.SensorDto;
import io.github.spaceymonk.khb.centralunit.service.CentralUnitService;
import io.github.spaceymonk.khb.centralunit.service.SensorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class SensorConsumer {

    private final SensorService sensorService;

    @KafkaListener(topics = "${app.kafka.topic.sensor.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(SensorDto sensorDto) {
        log.info("Sensor found: {}", sensorDto);
        sensorService.save(sensorDto);
    }
}
