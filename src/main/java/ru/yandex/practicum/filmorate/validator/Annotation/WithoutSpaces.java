package ru.yandex.practicum.filmorate.validator.Annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import ru.yandex.practicum.filmorate.validator.SpaceValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = SpaceValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)

public @interface WithoutSpaces {
    String message() default "Логин не должен содержать пробелы";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
