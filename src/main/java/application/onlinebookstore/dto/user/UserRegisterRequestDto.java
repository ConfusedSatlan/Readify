package application.onlinebookstore.dto.user;

import application.onlinebookstore.validation.Password;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record UserRegisterRequestDto(@NotNull
                                     @Email
                                     String email,
                                     @NotNull
                                     String first_name,
                                     @NotNull
                                     String last_name,
                                     @NotNull
                                     @Password
                                     String password,
                                     @NotNull
                                     String repeatPassword) {
}
