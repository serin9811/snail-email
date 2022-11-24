package kr.snailemail.snailemail.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignUpUserDto {

    private String userEmail;
    private String password;
    
    @Builder
    public SignUpUserDto(String userEmail, String password) {
        this.userEmail = userEmail;
        this.password = password;
    }
}
