package application.onlinebookstore.service;

import application.onlinebookstore.dto.user.UserRegisterRequestDto;
import application.onlinebookstore.dto.user.UserResponseDto;
import application.onlinebookstore.exception.RegistrationException;
import application.onlinebookstore.model.Users;

public interface UserService {
    UserResponseDto register(UserRegisterRequestDto request) throws RegistrationException;

    Users getUserById(Long id);
}
