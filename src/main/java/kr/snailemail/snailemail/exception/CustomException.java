package kr.snailemail.snailemail.exception;

import java.io.Serializable;

public class CustomException extends RuntimeException implements Serializable {

    private final CustomError customError;

    public CustomException(CustomError customError) {
        super(customError.getMessage());
        this.customError = customError;
    }

    public CustomError getCustomError() {
        return customError;
    }
}
