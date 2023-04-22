package ru.yandex.practicum.filmorate.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ru.yandex.practicum.filmorate.validator.Annotation.AfterData;

import java.time.LocalDate;

public class DateValidator implements ConstraintValidator<AfterData, LocalDate> {
    private static final LocalDate MIN_DATE_RELEASE = LocalDate.parse("1895-12-28");

    @Override
    public boolean isValid(LocalDate date, ConstraintValidatorContext constraintValidatorContext) {
        if (date == null) {
            return false;
        }
        return date.isAfter(MIN_DATE_RELEASE);
    }
}
