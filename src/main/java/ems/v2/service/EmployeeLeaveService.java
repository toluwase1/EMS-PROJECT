package ems.v2.service;

import ems.v2.model.Employee;
import ems.v2.model.EmployeeLeave;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeLeaveService {
    List<EmployeeLeave> getEmployeeLeaves(Long id);
    String addNewLeave(Employee employee);

    String addLeave(Employee employee);
}
