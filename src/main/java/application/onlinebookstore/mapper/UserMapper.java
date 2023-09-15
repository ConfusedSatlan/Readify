package application.onlinebookstore.mapper;

import application.onlinebookstore.config.MapperConfig;
import application.onlinebookstore.dto.user.UserRegisterRequestDto;
import application.onlinebookstore.dto.user.UserResponseDto;
import application.onlinebookstore.model.User;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
    UserResponseDto toUserResponse(User user);

    User toModel(UserRegisterRequestDto requestDto);
}
