package ru.yandex.practicum.filmorate.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Builder;
import lombok.Data;
import ru.yandex.practicum.filmorate.validator.Annotation.WithoutSpaces;

import java.time.LocalDate;

@Data
@Builder

public class User {
    private Integer id;
    @NotBlank(message = "электронная почта не может быть пустой")
    @Email(message = "Email должен быть с символом @ и не содержать ошибок")
    private String email;
    @NotNull(message = "не должен быть пустым")
    @WithoutSpaces
    private String login;
    private String name;
    @Past(message = "Дата рождения должна быть в прошлом")
    private LocalDate birthday;
}
