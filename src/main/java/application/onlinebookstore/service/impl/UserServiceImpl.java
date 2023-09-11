package application.onlinebookstore.service.impl;

import application.onlinebookstore.dto.user.UserRegisterRequestDto;
import application.onlinebookstore.dto.user.UserResponseDto;
import application.onlinebookstore.exception.RegistrationException;
import application.onlinebookstore.mapper.UserMapper;
import application.onlinebookstore.model.User;
import application.onlinebookstore.repository.user.UserRepository;
import application.onlinebookstore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public UserResponseDto register(UserRegisterRequestDto request) throws RegistrationException {
        String email = request.email();
        if (userRepository.findByEmail(email).isPresent()) {
            throw new RegistrationException("User is already registered!");
        }
        User user = new User();
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setEmail(email);
        User savedUser = userRepository.save(user);
        return userMapper.toUserResponse(savedUser);
    }
}
