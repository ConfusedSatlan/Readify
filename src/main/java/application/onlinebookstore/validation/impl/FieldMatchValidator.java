package application.onlinebookstore.validation.impl;

import application.onlinebookstore.validation.FieldMatch;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {
    private String firstName;
    private String secondName;


    @Override
    public void initialize(FieldMatch constraintAnnotation) {
        firstName = constraintAnnotation.first();
        secondName = constraintAnnotation.second();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        Object firstObj = getFieldValue(o, firstName);
        Object secondObj = getFieldValue(o, secondName);

        return firstObj == null && secondObj == null
                || firstObj != null && firstObj.equals(secondObj);
    }

    private Object getFieldValue(Object object, String fieldName) {
        BeanWrapperImpl wrapper = new BeanWrapperImpl(object);
        return wrapper.getPropertyValue(fieldName);
    }
}
