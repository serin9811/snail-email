package kr.snailemail.snailemail.model.dto;

import kr.snailemail.snailemail.model.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
public class UserDto {

    private Long userId;
    private String userEmailAddr;
    private LocalDateTime joinDate;
    private LocalDateTime updateDate;

    public User toEntity() {
        return User.builder()
                .userId(userId)
                .userEmailAddr(userEmailAddr)
                .joinDate(joinDate)
                .updateDate(updateDate)
                .build();
    }

}
