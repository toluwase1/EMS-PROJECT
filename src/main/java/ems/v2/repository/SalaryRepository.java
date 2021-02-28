package ems.v2.repository;

import ems.v2.model.Employee;
import ems.v2.model.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SalaryRepository extends JpaRepository<Salary, Long> {
//    Optional<Employee> findByEmail(String email);
    List<Salary> findAllByEmployee(Employee employee);
}
