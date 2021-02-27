package ems.v2.service.implementation;

import ems.v2.model.Employee;
import ems.v2.repository.EmployeeRepository;
import ems.v2.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    @Override
    public Employee login(String email, String password) {
        Optional<Employee> employee = employeeRepository.findByEmailAndPassword(email, password);
        if (employee.isPresent()) return employee.get();
        return null;
    }

    @Override
    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }



    @Override
    public Employee getEmployeeById(Long employeeId) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if (employee.isPresent()) return employee.get();
        return null;
    }

    @Override
    public void updateEmployee(Employee employee, Long employeeId) {
        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        employeeOptional.get().setFirstName(employee.getFirstName());
        employeeOptional.get().setLastName(employee.getLastName());
        employeeOptional.get().setEmail(employee.getEmail());
        employeeRepository.save(employeeOptional.get());
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        employeeRepository.delete(employeeRepository.findById(employeeId).get());
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

//    @Override
//    public Employee getEmployeeByEmail(String email) {
//
//        return employeeRepository.findByEmail(email).orElse(null);
//    }






}
