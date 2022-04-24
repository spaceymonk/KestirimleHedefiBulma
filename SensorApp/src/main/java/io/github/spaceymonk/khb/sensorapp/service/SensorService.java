package io.github.spaceymonk.khb.sensorapp.service;

import io.github.spaceymonk.khb.sensorapp.dto.SensorDataDto;
import io.github.spaceymonk.khb.sensorapp.dto.SensorDto;
import io.github.spaceymonk.khb.sensorapp.model.Location2D;

public interface SensorService {
    SensorDto getSensor();

    SensorDataDto scan(Location2D targetLoc);
}
