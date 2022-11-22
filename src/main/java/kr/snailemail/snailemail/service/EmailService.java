package kr.snailemail.snailemail.service;

import kr.snailemail.snailemail.dto.GeneralResponse;
import kr.snailemail.snailemail.entity.Email;
import kr.snailemail.snailemail.repository.EmailRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailService {

    private final EmailRepository emailRepository;

    public EmailService(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
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
}
