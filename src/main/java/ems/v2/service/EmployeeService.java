package ems.v2.service;

import ems.v2.model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

public interface EmployeeService {
    Employee login (String email, String password);
    List<Employee> findAllEmployees();

    public Employee saveEmployee(Employee employee);


    Employee getEmployeeById(Long employeeId);

    void updateEmployee(Employee employee, Long employeeId);

    void deleteEmployee(Long employeeId);

    List<Employee> getAllEmployee();

    //Employee getEmployeeByEmailAndPassword (String email, String password);
}
