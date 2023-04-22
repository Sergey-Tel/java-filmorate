package ru.yandex.practicum.filmorate.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ru.yandex.practicum.filmorate.validator.Annotation.WithoutSpaces;

public class SpaceValidator implements ConstraintValidator<WithoutSpaces, String> {
    private static final String CONTAINS_CHAR = " ";

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {


        if (s == null) {
            return true;
        }
        return !s.contains(CONTAINS_CHAR);
    }
}
