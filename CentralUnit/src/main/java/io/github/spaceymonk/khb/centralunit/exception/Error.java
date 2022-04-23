package io.github.spaceymonk.khb.centralunit.exception;

import java.io.Serializable;

public interface Error extends Serializable {
    String getErrorMessage();
    String getErrorCode();
}
