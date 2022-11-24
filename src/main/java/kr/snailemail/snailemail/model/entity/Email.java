package kr.snailemail.snailemail.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "Emails")
@Entity
@EntityListeners(AuditingEntityListener.class)
@SequenceGenerator(
        name = "EMAIL_SEQ_GENERATOR",
        sequenceName = "EMAIL_SEQ",
        initialValue = 1,
        allocationSize = 1
)
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
    generator = "EMAIL_SEQ")
    @Column(name = "emailId")
    private Long emailId;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "email_title")
    private String emailTitle;

    @Column(name = "email_content")
    private String emailContent;

    @CreatedDate
    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "send_date")
    private LocalDateTime sendDate;

    @Column(name = "send_yn")
    private boolean sendYn;

}
