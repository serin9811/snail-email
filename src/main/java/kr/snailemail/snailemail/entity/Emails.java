package kr.snailemail.snailemail.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "emails")
@Entity
public class Emails {

    @Id
    private Long emailId;

    @Column(name = "email_sender_id")
    private Long emailSenderId;

    @Column(name = "email_title")
    private String emailTitle;

    @Column(name = "email_content")
    private String emailContent;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "send_date")
    private LocalDateTime sendDate;

    @Column(name = "send_yn")
    private boolean sendYn;

}
