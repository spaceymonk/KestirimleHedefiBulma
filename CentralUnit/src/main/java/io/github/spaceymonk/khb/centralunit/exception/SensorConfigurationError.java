package io.github.spaceymonk.khb.centralunit.exception;

public enum SensorConfigurationError implements Error {
    NOT_ENOUGH_SENSOR("There should be at least 2 sensors for computation!", "100"),
    TOO_MUCH_SENSOR("There are more than 2 sensors. You should specify which sensors to use. [NOT IMPLEMENTED]", "101");

    private final String errorMessage;
    private final String errorCode;
    SensorConfigurationError(String errorMessage, String errorCode) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

    @Override
    public String getErrorMessage() {
        return this.errorMessage;
    }

    @Override
    public String getErrorCode() {
        return this.errorCode;
    }
}
