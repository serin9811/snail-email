package kr.snailemail.snailemail.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignUpUserDto {

    private String userEmail;
    
    @Builder
    public SignUpUserDto(String userEmail) {
        this.userEmail = userEmail;
    }
}
