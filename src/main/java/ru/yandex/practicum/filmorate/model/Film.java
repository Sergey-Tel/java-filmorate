package ru.yandex.practicum.filmorate.model;

import lombok.Getter;
import lombok.Setter;
import ru.yandex.practicum.filmorate.validator.Annotation.AfterData;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
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
