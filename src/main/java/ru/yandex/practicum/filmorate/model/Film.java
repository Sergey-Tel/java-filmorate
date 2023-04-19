package ru.yandex.practicum.filmorate.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Film {

    int id;
    @NotBlank
    String name;
    String description;
    LocalDate releaseDate;
    int duration;

}
