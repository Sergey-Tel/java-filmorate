package ru.yandex.practicum.filmorate.service;

import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.Interface.UserInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService implements UserInterface {
    private final Map<Integer, User> users = new HashMap<>();

    @Override
    public User createUser(int id, User user) {
        User saveUser = validateName(user);
        saveUser.setId(id);
        users.put(id, saveUser);
        return saveUser;
    }

    @Override
    public User updateUser(int id, User user) {
        User saveUser = validateName(user);
        users.put(id, saveUser);
        return saveUser;
    }

    @Override
    public List<User> findAllUsers() {
        return new ArrayList<>(users.values());
    }

    public boolean isContains(int id) {
        return users.containsKey(id);
    }

    private User validateName(User user) {
        if (user.getName() == null) {
            user.setName(user.getLogin());
        }
        return user;
    }
}
