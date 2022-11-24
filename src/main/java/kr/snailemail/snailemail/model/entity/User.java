package kr.snailemail.snailemail.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import kr.snailemail.snailemail.model.dto.UserDto;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@Table(name = "Users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@EntityListeners(AuditingEntityListener.class)
@SequenceGenerator(
        name = "USER_SEQ_GENERATOR",
        sequenceName = "USER_SEQ",
        initialValue = 1,
        allocationSize = 1
)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
    generator = "USER_SEQ")
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "email_addr")
    private String emailAddr;

    @CreatedDate
    @Column(name = "join_date")
    private LocalDateTime joinDate;

    @LastModifiedDate
    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @JsonManagedReference
    @OneToMany(mappedBy = "user")
    private List<Email> emails = new ArrayList<>();

    @Builder
    public User(Long userId, String emailAddr, LocalDateTime joinDate, LocalDateTime updateDate) {
        this.userId = userId;
        this.emailAddr = emailAddr;
        this.joinDate = joinDate;
        this.updateDate = updateDate;
    }

    public UserDto toUserDto() {
        return UserDto.builder()
                .userId(userId)
                .emailAddr(emailAddr)
                .joinDate(joinDate)
                .updateDate(updateDate)
                .build();
    }
}
