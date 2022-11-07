package kr.snailemail.snailemail.controller;

import kr.snailemail.snailemail.dto.GeneralResponse;
import kr.snailemail.snailemail.dto.SignInUserDto;
import kr.snailemail.snailemail.dto.SignUpUserDto;
import kr.snailemail.snailemail.service.CognitoUserService;

import org.springframework.web.bind.annotation.*;

@RequestMapping("/users")
@RestController
public class AuthController {

    private CognitoUserService userService;

    public AuthController(CognitoUserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public GeneralResponse<?> getUsers() {
        return userService.getUsers();
    }

    @PostMapping("")
    public GeneralResponse<?> registerUser(@RequestBody SignUpUserDto request) {
        return userService.registerUser(request);
    }

    @PostMapping("/login")
    public GeneralResponse<?> signInUser(@RequestBody SignInUserDto request) {
        return userService.signInUser(request);

    }
}
    
