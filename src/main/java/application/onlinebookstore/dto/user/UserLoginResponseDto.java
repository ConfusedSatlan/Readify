package application.onlinebookstore.dto.user;

import lombok.Data;

@Data
public class UserLoginResponseDto {
    private Long id;
    private String email;
    private String password;
    private String first_name;
    private String last_name;
}