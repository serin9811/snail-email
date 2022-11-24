package kr.snailemail.snailemail.model.dto;

import kr.snailemail.snailemail.model.entity.Email;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class EmailReserveRequest {

    private String emailAddr;
    private String emailTitle;
    private String emailContent;
    //private LocalDateTime sendDate;

    public Email toEntity() {
        return Email.builder()
                .emailTitle(emailTitle)
                .emailContent(emailContent)
                .sendDate(LocalDateTime.now().plusYears(1))
                //.sendDate(sendDate)
                .build();
    }
}
