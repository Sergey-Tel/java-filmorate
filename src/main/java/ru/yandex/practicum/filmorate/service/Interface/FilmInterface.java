package ru.yandex.practicum.filmorate.service.Interface;

import ru.yandex.practicum.filmorate.model.Film;

import java.util.List;

public interface FilmInterface {
    Film addFilm(int id,Film film);
    Film updateFilm(int id,Film film);
    List<Film> getFilms();
}
