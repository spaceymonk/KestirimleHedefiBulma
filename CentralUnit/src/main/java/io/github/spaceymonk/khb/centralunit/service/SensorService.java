package io.github.spaceymonk.khb.centralunit.service;

import io.github.spaceymonk.khb.centralunit.dto.SensorDataDto;
import io.github.spaceymonk.khb.centralunit.dto.SensorDto;
import io.github.spaceymonk.khb.centralunit.model.Sensor;

import java.util.List;
import java.util.UUID;

public interface SensorService {
    void save(SensorDto sensorDto);

    void updateData(SensorDataDto sensorDataDto);

    Sensor findById(UUID id);

    List<Sensor> findAll();
}
