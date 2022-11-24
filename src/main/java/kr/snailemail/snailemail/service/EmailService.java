package kr.snailemail.snailemail.service;

import kr.snailemail.snailemail.model.dto.EmailReserveRequest;
import kr.snailemail.snailemail.model.dto.GeneralResponse;
import kr.snailemail.snailemail.model.entity.Email;
import kr.snailemail.snailemail.model.entity.User;
import kr.snailemail.snailemail.repository.EmailRepository;
import kr.snailemail.snailemail.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailService {

    private final EmailRepository emailRepository;

    private final UserRepository userRepository;

    public EmailService(EmailRepository emailRepository, UserRepository userRepository) {
        this.emailRepository = emailRepository;
        this.userRepository = userRepository;
    }

    public ResponseEntity<GeneralResponse> getEmails(String userEmail) {
        List<Email> list = emailRepository.findByUserEmail(userEmail);

        return ResponseEntity.ok().body(
                GeneralResponse.builder()
                        .status(200)
                        .message("success")
                        .data(list)
                        .build()
        );
    }

    public ResponseEntity<GeneralResponse> reserveEmail(EmailReserveRequest request) {
        Email newEmail = request.toEntity();
        User user = userRepository.findByEmailAddr(request.getEmailAddr());
        newEmail.updateEmailSender(user);
        System.out.println("newEmail ===> " + newEmail);
        Email reservedEmail = emailRepository.save(newEmail);
        System.out.println("reservedEmail ==> " + reservedEmail);

        return ResponseEntity.ok().body(
                GeneralResponse.builder()
                        .status(200)
                        .message("success")
                        .data(reservedEmail)
                        .build()
        );
    }
}
