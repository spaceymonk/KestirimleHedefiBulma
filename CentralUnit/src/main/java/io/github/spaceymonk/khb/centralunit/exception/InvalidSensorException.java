package io.github.spaceymonk.khb.centralunit.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class InvalidSensorException extends RuntimeException {
    private final Error error;
    private final UUID sensorId;

    public InvalidSensorException(Error error, UUID sensorId) {
        super("[ErrorCode: " + error.getErrorCode() + "] " + error.getErrorMessage() + " Sensor Id: " + sensorId);
        this.error = error;
        this.sensorId = sensorId;
    }
}
