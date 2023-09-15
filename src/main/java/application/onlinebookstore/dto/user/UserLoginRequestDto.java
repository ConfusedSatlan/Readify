package application.onlinebookstore.dto.user;

import application.onlinebookstore.validation.Password;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;

public record UserLoginRequestDto(@NotNull
                                  @Length(min = 4, max = 40)
                                  @Email
                                  String email,
                                  @NotNull
                                  @Password
                                  String password) {
}
