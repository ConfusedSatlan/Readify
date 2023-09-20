package application.onlinebookstore.dto.user;

import application.onlinebookstore.validation.FieldMatch;
import application.onlinebookstore.validation.Password;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

@FieldMatch(
        first = "password",
        second = "repeatPassword",
        message = "Passwords don't match"
)
public record UserRegisterRequestDto(@NotNull
                                     @Email
                                     String email,
                                     @NotNull
                                     String firstName,
                                     @NotNull
                                     String lastName,
                                     @NotNull
                                     @Password
                                     String password,
                                     @NotNull
                                     String repeatPassword) {
}
