package ru.yandex.practicum.filmorate.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.yandex.practicum.filmorate.validator.NoSpaces;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;

    @NotBlank(message = "Email не может быть пустым")
    @Email(message = "Email введен с ошибкой")
    private String email;

    private String name;


    @NotNull(message = "Логин не может быть пустым")
    @NoSpaces
    private String login;

    @Past(message = "Дата рождения не может быть в будущем")
    private LocalDate birthday;
}
