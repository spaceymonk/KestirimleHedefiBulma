package io.github.spaceymonk.khb.centralunit.service.impl;

import io.github.spaceymonk.khb.centralunit.dto.SensorDataDto;
import io.github.spaceymonk.khb.centralunit.dto.SensorDto;
import io.github.spaceymonk.khb.centralunit.exception.InvalidSensorException;
import io.github.spaceymonk.khb.centralunit.exception.SensorError;
import io.github.spaceymonk.khb.centralunit.model.Location2D;
import io.github.spaceymonk.khb.centralunit.model.Sensor;
import io.github.spaceymonk.khb.centralunit.service.SensorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class SensorServiceImpl implements SensorService {
    private final Map<UUID, Sensor> sensorMap = new HashMap<>();

    @Override
    public void save(SensorDto sensorDto) {
        final Sensor sensor = Sensor.builder()
                .id(sensorDto.getSensorId())
                .createdAt(sensorDto.getTimestamp())
                .location(new Location2D(sensorDto.getLocationX(), sensorDto.getLocationY()))
                .build();
        sensorMap.put(sensorDto.getSensorId(), sensor);
    }

    @Override
    public void updateData(SensorDataDto sensorDataDto) {
        final Sensor sensor = findById(sensorDataDto.getSensorId());
        if (sensor == null) {
            throw new InvalidSensorException(SensorError.INVALID_SENSOR_ACCESS, sensorDataDto.getSensorId());
        }
        sensor.setFoundAt(sensorDataDto.getTimestamp());
        sensor.setTargetAngle(sensorDataDto.getAngle());
        sensorMap.put(sensor.getId(), sensor);
    }

    @Override
    public Sensor findById(UUID id) {
        return sensorMap.get(id);
    }

    @Override
    public List<Sensor> findAll() {
        return new ArrayList<>(sensorMap.values());
    }
}
