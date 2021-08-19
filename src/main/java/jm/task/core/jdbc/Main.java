package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Геннадий", "Евдокимов", (byte) 32);
        userService.saveUser("Кира", "Найтли", (byte) 17);
        userService.saveUser("Семен", "Слепаков", (byte) 52);
        userService.saveUser("Жанна", "Агузарова", (byte) 64);

        List<User> list = userService.getAllUsers();
        for (User u : list) {
            System.out.println(u.toString());
        }

        userService.cleanUsersTable();
        userService.dropUsersTable();
    }

}
