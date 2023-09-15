package application.onlinebookstore.service.impl;

import application.onlinebookstore.dto.user.UserRegisterRequestDto;
import application.onlinebookstore.dto.user.UserResponseDto;
import application.onlinebookstore.exception.EntityNotFoundException;
import application.onlinebookstore.exception.RegistrationException;
import application.onlinebookstore.mapper.UserMapper;
import application.onlinebookstore.model.Role;
import application.onlinebookstore.model.User;
import application.onlinebookstore.repository.user.RoleRepository;
import application.onlinebookstore.repository.user.UserRepository;
import application.onlinebookstore.service.UserService;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public UserResponseDto register(UserRegisterRequestDto request) throws RegistrationException {
        if (userRepository.findByEmail(request.email()).isPresent()) {
            throw new RegistrationException("User is already registered!");
        }
        User user = userMapper.toModel(request);
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRoles(Set.of(roleRepository.findByName(Role.RoleName.ROLE_USER).orElseThrow(
                () -> new EntityNotFoundException("Can't find default role for user")
        )));
        return userMapper.toUserResponse(userRepository.save(user));
    }
}
