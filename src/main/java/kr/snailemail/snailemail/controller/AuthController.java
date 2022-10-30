package kr.snailemail.snailemail.controller;

import kr.snailemail.snailemail.entity.GeneralResponse;
import kr.snailemail.snailemail.service.CognitoUserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
public class AuthController {

    private CognitoUserService userService;

    public AuthController(CognitoUserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public GeneralResponse<?> getUsers() {
        return GeneralResponse.builder()
            .status(true).message("").data(null).build();
    }
}
    
