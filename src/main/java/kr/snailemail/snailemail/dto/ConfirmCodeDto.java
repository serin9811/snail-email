package kr.snailemail.snailemail.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConfirmCodeDto {
    private String userEmail;
    private String code;
}
