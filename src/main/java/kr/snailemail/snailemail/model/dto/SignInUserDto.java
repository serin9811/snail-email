package kr.snailemail.snailemail.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignInUserDto {

    private String userEmail;
    private String password;

}
