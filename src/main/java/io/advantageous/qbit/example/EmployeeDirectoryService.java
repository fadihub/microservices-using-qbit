package io.advantageous.qbit.example;


import io.advantageous.qbit.annotation.PathVariable;
import io.advantageous.qbit.annotation.RequestMapping;
import io.advantageous.qbit.annotation.RequestMethod;
import io.advantageous.qbit.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequestMapping("/dir")
public class EmployeeDirectoryService {


    private final List<Department> departmentList = new ArrayList<>();


    @RequestMapping("/employee/{employeeId}/")
    public Employee listEmployee(@PathVariable("employeeId") final long employeeId) {

        /* Find the department that has the employee. */
        final Optional<Department> departmentOptional = departmentList.stream()
                .filter(department -> department.employeeList().stream()
                        .anyMatch(employee -> employee.getId() == employeeId)).findFirst();

        /* Find employee in department. */
        if (departmentOptional.isPresent()) {
            return departmentOptional.get().employeeList()
                    .stream().filter(employee -> employee.getId() == employeeId)
                    .findFirst().get();
        } else {
            return null;
        }
    }



    @RequestMapping("/department/{departmentId}/")
    public Department listDepartment(@PathVariable("departmentId") final long departmentId) {

        return departmentList.stream()
                .filter(department -> department.getId() == departmentId).findFirst().get();
    }



    @RequestMapping(value = "/department/", method = RequestMethod.POST)
    public boolean addDepartment(  @RequestParam("departmentId")   final long departmentId,
                                   @RequestParam("name")           final String name) {
        final Optional<Department> departmentOptional = departmentList.stream()
                .filter(department -> department.getId() == departmentId).findAny();
        if (departmentOptional.isPresent()) {
            throw new IllegalArgumentException("Department " + departmentId + " already exists");
        }
        departmentList.add(new Department(departmentId, name));
        return true;
    }


    @RequestMapping(value = "/department/employee/", method = RequestMethod.POST)
    public boolean addEmployee(    @RequestParam("departmentId") final long departmentId,
                                   final Employee employee) {

        final Optional<Department> departmentOptional = departmentList.stream()
                .filter(department -> department.getId() == departmentId).findAny();
        if (!departmentOptional.isPresent()) {
            throw new IllegalArgumentException("Department not found");
        }


        final boolean alreadyExists = departmentOptional.get().employeeList().stream()
                .anyMatch(employeeItem -> employeeItem.getId() == employee.getId());

        if (alreadyExists) {
            throw new IllegalArgumentException("Employee with id already exists " + employee.getId());
        }
        departmentOptional.get().addEmployee(employee);
        return true;
    }




    @RequestMapping(value = "/employee", method = RequestMethod.DELETE)
    public boolean removeEmployee(@RequestParam("id") final long employeeId) {

        /* Find the department that has the employee. */
        final Optional<Department> departmentOptional = departmentList.stream()
                .filter(department -> department.employeeList().stream()
                        .anyMatch(employee -> employee.getId() == employeeId)).findFirst();

        /* Remove employee from department. */
        if (departmentOptional.isPresent()) {
            departmentOptional.get().removeEmployee(employeeId);
            return true;
        } else {
            return false;
        }
    }


    @RequestMapping(value = "/department", method = RequestMethod.DELETE)
    public boolean removeDepartment(@RequestParam("id") final long departmentId) {

        return departmentList.removeIf(department -> departmentId == department.getId());
    }



    @RequestMapping(value = "/department/{departmentId}/employee", method = RequestMethod.DELETE)
    public boolean removeEmployeeFromDepartment(
            @PathVariable("departmentId")   final long departmentId,
            @RequestParam("id")             final long employeeId) {

        /* Find the department by id. */
        final Optional<Department> departmentOptional = departmentList.stream()
                .filter(department -> department.getId() == departmentId).findFirst();

        /* Remove employee from department. */
        if (departmentOptional.isPresent()) {
            departmentOptional.get().removeEmployee(employeeId);
            return true;
        } else {
            return false;
        }
    }

}