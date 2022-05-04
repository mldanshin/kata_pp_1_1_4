package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Ivan", "Ivanov", (byte) 18);
        printReportSave("Ivan");

        userService.saveUser("Oleg", "Petrov", (byte) 29);
        printReportSave("Oleg");

        userService.saveUser("Denis", "Sidorov", (byte) 25);
        printReportSave("Denis");

        userService.saveUser("Maxim", "Danshin", (byte) 45);
        printReportSave("Maxim");

        userService.getAllUsers().forEach(text -> System.out.println(text));

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }

    private static void printReportSave(String name) {
        System.out.printf("User с именем – %s добавлен в базу данных \n", name);
    }
}
