package io.github.spaceymonk.khb.sensorapp.service.impl;

import io.github.spaceymonk.khb.sensorapp.dto.SensorDataDto;
import io.github.spaceymonk.khb.sensorapp.dto.SensorDto;
import io.github.spaceymonk.khb.sensorapp.model.Location2D;
import io.github.spaceymonk.khb.sensorapp.model.Sensor;
import io.github.spaceymonk.khb.sensorapp.service.SensorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
@Slf4j
public class SensorServiceImpl implements SensorService {

    private static Sensor SENSOR;

    SensorServiceImpl(@Value("${app.sensor.location.x}") Double sensorLocX, @Value("${app.sensor.location.y}") Double sensorLocY) {
        SENSOR = Sensor.builder()
                .id(UUID.randomUUID())
                .createdAt(new Date())
                .location(new Location2D(sensorLocX, sensorLocY)).build();
    }

    @Override
    public SensorDto getSensor() {
        return SensorDto.builder()
                .sensorId(SENSOR.getId())
                .locationX(SENSOR.getLocation().getX())
                .locationY(SENSOR.getLocation().getY())
                .timestamp(SENSOR.getCreatedAt()).build();
    }

    @Override
    public SensorDataDto scan(Location2D targetLoc) {
        double opposite = targetLoc.getY() - SENSOR.getLocation().getY();
        double adjacent = targetLoc.getX() - SENSOR.getLocation().getX();
        double angle = Math.atan(opposite / adjacent);
        return SensorDataDto.builder()
                .sensorId(SENSOR.getId())
                .angle(angle)
                .timestamp(new Date()).build();
    }
}
