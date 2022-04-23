package io.github.spaceymonk.khb.centralunit.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvalidSensorConfigException extends RuntimeException {
    private final Error error;

    public InvalidSensorConfigException(Error error) {
        super("[ErrorCode: " + error.getErrorCode() + "] " + error.getErrorMessage());
        this.error = error;
    }
}
