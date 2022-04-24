package io.github.spaceymonk.khb.centralunit.service.impl;

import io.github.spaceymonk.khb.centralunit.dto.SensorDataDto;
import io.github.spaceymonk.khb.centralunit.dto.SensorDto;
import io.github.spaceymonk.khb.centralunit.model.Location2D;
import io.github.spaceymonk.khb.centralunit.model.Sensor;
import io.github.spaceymonk.khb.centralunit.service.SensorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class SensorServiceImpl implements SensorService {
    private static final Map<UUID, Sensor> SENSOR_MAP = new HashMap<>();
    private static final Map<UUID, SensorDataDto> DANGLING_DATA = new HashMap<>();

    @Override
    public void save(SensorDto sensorDto) {
        final Sensor sensor = Sensor.builder()
                .id(sensorDto.getSensorId())
                .createdAt(sensorDto.getTimestamp())
                .location(new Location2D(sensorDto.getLocationX(), sensorDto.getLocationY()))
                .build();
        if (DANGLING_DATA.containsKey(sensorDto.getSensorId())) {
            final SensorDataDto sensorDataDto = DANGLING_DATA.remove(sensorDto.getSensorId());
            sensor.setFoundAt(sensorDataDto.getTimestamp());
            sensor.setTargetAngle(sensorDataDto.getAngle());
        }
        SENSOR_MAP.put(sensorDto.getSensorId(), sensor);
    }

    @Override
    public void updateData(SensorDataDto sensorDataDto) {
        final Sensor sensor = findById(sensorDataDto.getSensorId());
        if (sensor == null) {
//            throw new InvalidSensorException(SensorError.INVALID_SENSOR_ACCESS, sensorDataDto.getSensorId());
            log.warn("Sensor data arrived before sensor itself. Saving incoming data for further use.");
            DANGLING_DATA.put(sensorDataDto.getSensorId(), sensorDataDto);
            return;
        }
        sensor.setFoundAt(sensorDataDto.getTimestamp());
        sensor.setTargetAngle(sensorDataDto.getAngle());
        SENSOR_MAP.put(sensor.getId(), sensor);
    }

    @Override
    public Sensor findById(UUID id) {
        return SENSOR_MAP.get(id);
    }

    @Override
    public List<Sensor> findAll() {
        return new ArrayList<>(SENSOR_MAP.values());
    }
}
