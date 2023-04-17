package com.company;

public class Employee {
    private int id;
    private String name;
    private double salary;
    private boolean isActive;
    public Employee(int id, String name, double salary, boolean isActive) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.isActive = isActive;
    }

    public int getId() {
        return id;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public double getSalary() {
        return salary;
    }

    public String getName() {
        return name;
    }
}