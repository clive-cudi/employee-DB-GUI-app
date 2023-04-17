package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println("Hello world!");

        Employee[] employees = {};
        // render the table in JFrame
        // pull data from DB

        try {
            // driver init
            Class.forName("com.mysql.cj.jdbc.Driver");

            // connection string
            String db_connection = "jdbc:mysql://localhost/maina";
            // connection init
            Connection connection = DriverManager.getConnection(db_connection, "root", "");

            // statement
            Statement statement = connection.createStatement();

            // query
            ResultSet usersResultSet = statement.executeQuery("select * from `employee`");

            // iterate through the result set and push each user to list
            while (usersResultSet.next()) {
                int[] cols = {1, 2, 3, 4};

                /* [DEBUG] */
                for(int col: cols) {
                    System.out.println(usersResultSet.getString(col));
                }
                Employee fetchedEmployee = new Employee(usersResultSet.getInt(1), usersResultSet.getString(2), usersResultSet.getDouble(3), usersResultSet.getBoolean(4));

                employees = addToUsers(employees, fetchedEmployee);
            };

            System.out.println("Database connection successful!!");
        } catch (Exception e) {
            System.out.println("An Error occurred while connecting to DB!");
            System.out.println(e);
        }

        new AppFrame(employees);
    }

    private static Employee[] addToUsers(Employee[] initialUsers, Employee user) {
        Employee[] newArr = new Employee[initialUsers.length + 1];

        for (int i = 0; i < initialUsers.length; i++) {
            newArr[i] = initialUsers[i];
        }

        newArr[initialUsers.length]  = user;

        return newArr;
    }
}