package application.onlinebookstore.controller;

import application.onlinebookstore.dto.user.UserLoginRequestDto;
import application.onlinebookstore.dto.user.UserLoginResponseDto;
import application.onlinebookstore.dto.user.UserRegisterRequestDto;
import application.onlinebookstore.dto.user.UserResponseDto;
import application.onlinebookstore.exception.RegistrationException;
import application.onlinebookstore.security.AuthenticationService;
import application.onlinebookstore.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public UserLoginResponseDto login(@RequestBody @Valid UserLoginRequestDto request) {
        return authenticationService.authenticate(request);
    }

    @PostMapping("/register")
    public UserResponseDto register(@RequestBody @Valid UserRegisterRequestDto request)
            throws RegistrationException {
        return userService.register(request);
    }

}
