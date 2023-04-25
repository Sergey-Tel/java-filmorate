package ru.yandex.practicum.filmorate.model;

import lombok.Data;
import ru.yandex.practicum.filmorate.validator.Annotation.AfterData;


import javax.validation.constraints.*;

import java.time.LocalDate;
import java.util.Set;

@Data
public class Film {
    private Integer id;
    private Set<Integer> likes;

    @NotBlank(message = "Название фильма не может быть пустым")
    private String name;

    @Size(max = 200, message = "Описание не может превышать 200 символов")
    private String description;

    @AfterData
    private LocalDate releaseDate;

    @Positive(message = "Продолжительность фильма не может быть отрицательным")
    private Integer duration;
}
