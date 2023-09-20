package application.onlinebookstore.validation.impl;

import application.onlinebookstore.validation.Isbn;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class IsbnValidator implements ConstraintValidator<Isbn, String> {
    private static final String ISBN_PATTERN = "\\d-\\d{4}-\\d{4}-\\d";

    @Override
    public boolean isValid(String isbn,
                           ConstraintValidatorContext constraintValidatorContext) {
        return isbn != null && Pattern.compile(ISBN_PATTERN).matcher(isbn).matches();
    }
}
