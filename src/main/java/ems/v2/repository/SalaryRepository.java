package ems.v2.repository;

import ems.v2.model.Employee;
import ems.v2.model.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SalaryRepository extends JpaRepository<Salary, Long> {
    List<Salary> findAllByEmployee(Employee employee);
}
