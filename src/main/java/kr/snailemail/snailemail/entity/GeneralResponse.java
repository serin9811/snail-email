package kr.snailemail.snailemail.entity;

import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class GeneralResponse <T> {
    private boolean status;
    private String message;
    private T data;
    
    @Builder
    GeneralResponse (boolean status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
