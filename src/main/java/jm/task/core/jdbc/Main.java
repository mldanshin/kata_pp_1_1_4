package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Ivan", "Ivanov", (byte) 18);
        printReportSave("Ivan");

        userService.saveUser("Oleg", "Petrov", (byte) 29);
        printReportSave("Oleg");

        userService.saveUser("Denis", "Sidorov", (byte) 25);
        printReportSave("Denis");

        userService.saveUser("Maxim", "Danshin", (byte) 45);
        printReportSave("Maxim");

        userService.getAllUsers().forEach(System.out::println);

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }

    private static void printReportSave(String name) {
        System.out.printf("User с именем – %s добавлен в базу данных \n", name);
    }
}
