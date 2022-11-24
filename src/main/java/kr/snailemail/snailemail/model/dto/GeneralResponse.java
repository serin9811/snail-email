package kr.snailemail.snailemail.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class GeneralResponse <T> {
    private int status;
    private String message;
    private T data;
    
    @Builder
    GeneralResponse (int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
