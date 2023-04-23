package ru.yandex.practicum.filmorate.service.Interface;

import ru.yandex.practicum.filmorate.model.User;

import java.util.List;

public interface UserInterface {
    User addUser(int id,User user);
    User updateUser(int id,User user);
    List<User> getUsers();
}
