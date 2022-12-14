package kr.snailemail.snailemail.controller;

import kr.snailemail.snailemail.model.dto.ConfirmCodeDto;
import kr.snailemail.snailemail.model.dto.GeneralResponse;
import kr.snailemail.snailemail.model.dto.SignInUserDto;
import kr.snailemail.snailemail.service.CognitoUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthController {

    private final CognitoUserService userService;

    AuthController(CognitoUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/sign-in")
    public ResponseEntity<GeneralResponse> signInUser(@RequestBody SignInUserDto request) {
        return userService.signInUser(request);
    }

    @PostMapping("/sign-out")
    public GeneralResponse<?> signOutUser(@RequestBody String accessToken) {
        return userService.signOutUser(accessToken);
    }

    @PostMapping("/confirm/sign-up")
    public GeneralResponse<?> confirmSignUp(@RequestBody ConfirmCodeDto request) {
        return userService.confirmSignUp(request);
    }

}
