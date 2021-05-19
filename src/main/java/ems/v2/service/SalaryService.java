package ems.v2.service;

import ems.v2.model.Salary;
import org.springframework.stereotype.Service;

import java.util.List;


public interface SalaryService {
    List<Salary> getSalaries(Long id);
    void saveEmployeeSalary(Long id, Salary salary);

    void saveSalary(Long id, Salary salary);
}
