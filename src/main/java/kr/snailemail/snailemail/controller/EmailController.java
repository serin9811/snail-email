package kr.snailemail.snailemail.controller;

import kr.snailemail.snailemail.model.dto.EmailReserveRequest;
import kr.snailemail.snailemail.model.dto.GeneralResponse;
import kr.snailemail.snailemail.service.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/emails")
@RestController
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/{userEmail}")
    public ResponseEntity<GeneralResponse> getEmails(@PathVariable String userEmail) {
        return emailService.getEmails(userEmail);
    }

    @PostMapping("")
    public ResponseEntity<GeneralResponse> reserveEmail(@RequestBody EmailReserveRequest request) {
        return emailService.reserveEmail(request);
    }

}
