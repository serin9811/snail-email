package kr.snailemail.snailemail.controller;

import kr.snailemail.snailemail.model.dto.GeneralResponse;
import kr.snailemail.snailemail.model.dto.SignUpUserDto;
import kr.snailemail.snailemail.service.CognitoUserService;

import org.springframework.web.bind.annotation.*;

@RequestMapping("/users")
@RestController
public class UserController {

    private final CognitoUserService userService;

    public UserController(CognitoUserService userService) {
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

}
    
