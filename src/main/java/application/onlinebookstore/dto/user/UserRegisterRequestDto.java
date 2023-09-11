package application.onlinebookstore.dto.user;

import jakarta.validation.constraints.NotNull;

public record UserRegisterRequestDto(@NotNull
                                     String email,
                                     @NotNull
                                     String password) {
}
