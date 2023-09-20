package application.onlinebookstore.controller;

import application.onlinebookstore.dto.user.UserLoginRequestDto;
import application.onlinebookstore.dto.user.UserLoginResponseDto;
import application.onlinebookstore.dto.user.UserRegisterRequestDto;
import application.onlinebookstore.dto.user.UserResponseDto;
import application.onlinebookstore.exception.RegistrationException;
import application.onlinebookstore.security.AuthenticationService;
import application.onlinebookstore.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Authentication management", description = "Endpoints for managing authentication")
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    @Operation(summary = "Login user",
            description = "Check if we have user in DB")
    public UserLoginResponseDto login(@RequestBody @Valid UserLoginRequestDto request) {
        return authenticationService.authenticate(request);
    }

    @PostMapping("/register")
    @Operation(summary = "Register user",
            description = "Register and create new user in DB")
    public UserResponseDto register(@RequestBody @Valid UserRegisterRequestDto request)
            throws RegistrationException {
        return userService.register(request);
    }

}
