package kr.snailemail.snailemail.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignInUserDto {

    private String email;
    private String password;

}
