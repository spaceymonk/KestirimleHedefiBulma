package io.github.spaceymonk.khb.centralunit.service.impl;

import io.github.spaceymonk.khb.centralunit.exception.InvalidSensorConfigException;
import io.github.spaceymonk.khb.centralunit.exception.InvalidSensorException;
import io.github.spaceymonk.khb.centralunit.exception.SensorConfigurationError;
import io.github.spaceymonk.khb.centralunit.exception.SensorError;
import io.github.spaceymonk.khb.centralunit.model.Location2D;
import io.github.spaceymonk.khb.centralunit.model.Sensor;
import io.github.spaceymonk.khb.centralunit.service.CentralUnitService;
import io.github.spaceymonk.khb.centralunit.service.SensorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CentralUnitServiceImpl implements CentralUnitService {

    private final SensorService sensorService;

    @Override
    public Location2D findTargetLocation() {
        List<Sensor> sensorList = sensorService.findAll();
        validateSensorConfiguration(sensorList);
        Sensor s1 = sensorList.get(0);
        Sensor s2 = sensorList.get(1);

        // calculate target's position
        final double m1 = Math.tan(s1.getTargetAngle());
        final double a1 = s1.getLocation().getY() - s1.getLocation().getX() * m1;

        final double m2 = Math.tan(s2.getTargetAngle());
        final double a2 = s2.getLocation().getY() - s2.getLocation().getX() * m2;

        final double targetX = (a2 - a1) / (m1 - m2);
        final double targetY = m1 * targetX + a1;

        return new Location2D(targetX, targetY);
    }

    private void validateSensorConfiguration(List<Sensor> sensorList) throws RuntimeException {
        if (sensorList.size() < 2) {
            throw new InvalidSensorConfigException(SensorConfigurationError.NOT_ENOUGH_SENSOR);
        } else if (sensorList.size() > 2) {
            throw new InvalidSensorConfigException(SensorConfigurationError.TOO_MUCH_SENSOR);
        }
        for (Sensor s : sensorList) {
            if (s.getFoundAt() == null) {
                throw new InvalidSensorException(SensorError.MISSING_SENSOR_DATA, s.getId());
            }
        }
    }
}
