package io.advantageous.qbit.example;

import java.util.ArrayList;
import java.util.List;

public class Department {

    private String name;
    private final long id;
    private final List<Employee> employees = new ArrayList();

    public Department(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addEmployee(final Employee employee) {
        employees.add(employee);
    }


    public boolean removeEmployee(final long id) {
        return employees.removeIf(employee -> employee.getId() == id);
    }

    public List<Employee> employeeList() {
        return employees;
    }


    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}