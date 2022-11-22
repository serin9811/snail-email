package kr.snailemail.snailemail.controller;

import kr.snailemail.snailemail.dto.GeneralResponse;
import kr.snailemail.snailemail.service.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/emails")
@RestController
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<GeneralResponse> getEmails(@PathVariable String userId) {
        return emailService.getEmails(userId);
    }

}
