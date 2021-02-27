package ems.v2.service.implementation;

import ems.v2.model.Employee;
import ems.v2.model.Salary;
import ems.v2.repository.SalaryRepository;
import ems.v2.service.EmployeeService;
import ems.v2.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SalaryServiceImpl implements SalaryService {
    EmployeeService employeeService;
    SalaryRepository salaryRepository;

    @Autowired
    public SalaryServiceImpl(EmployeeService employeeService, SalaryRepository salaryRepository) {
        this.employeeService = employeeService;
        this.salaryRepository = salaryRepository;
    }

    @Override
    public List<Salary> getSalaries(Long employeeId) {
        Employee employee = employeeService.getEmployeeById(employeeId);
        return salaryRepository.findAllByEmployee(employee);
    }

    @Override
    public void saveEmployeeSalary(Long id, Salary salary) {

    }

    @Override
    public void saveSalary(Long id, Salary salary) {
        Employee employee = employeeService.getEmployeeById(id);
        Salary newSalary = new Salary();
        newSalary.setAmount(salary.getAmount());
        newSalary.setMonth(salary.getMonth());
        newSalary.setEmployee(employee);
        salaryRepository.save(newSalary);
    }
}
