package application.onlinebookstore.validation.impl;

import application.onlinebookstore.validation.Author;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class AuthorValidator implements ConstraintValidator<Author, String> {
    private static final String AUTHOR_PATTERN = "^[a-zA-Z. ]*$";

    @Override
    public boolean isValid(String author,
                           ConstraintValidatorContext constraintValidatorContext) {
        return author != null && Pattern.compile(AUTHOR_PATTERN).matcher(author).matches();
    }
}
