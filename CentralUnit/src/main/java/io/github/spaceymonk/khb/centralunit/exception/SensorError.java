package io.github.spaceymonk.khb.centralunit.exception;

public enum SensorError implements Error {
    INVALID_SENSOR_ACCESS("Sensor data received but no such sensor found!", "201"),
    MISSING_SENSOR_DATA("Sensor did not registered any information!", "202");


    SensorError(String errorMessage, String errorCode) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

    private final String errorMessage;
    private final String errorCode;

    @Override
    public String getErrorMessage() {
        return this.errorMessage;
    }

    @Override
    public String getErrorCode() {
        return this.errorCode;
    }
}
