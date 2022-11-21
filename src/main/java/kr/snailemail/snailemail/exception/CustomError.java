package kr.snailemail.snailemail.exception;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CustomError {
    private int status;
    private String message;
    private Object data;

    public CustomError(int status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}