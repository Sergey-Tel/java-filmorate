package ru.yandex.practicum.filmorate.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import ru.yandex.practicum.filmorate.validator.Annotation.AfterData;

import java.time.LocalDate;

@Data
@Builder
public class Film {

    private Integer id;
    @NotBlank(message = "название не может быть пустым")
    private String name;
    @Size(max = 200, message = "максимальная длина описания — 200 символов")
    private String description;
    @AfterData
    private LocalDate releaseDate;
    @Positive(message = "продолжительность фильма должна быть положительной")
    private Integer duration;

}
