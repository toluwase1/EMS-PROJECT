package ems.v2.service.implementation;

import ems.v2.model.Employee;
import ems.v2.model.EmployeeLeave;
import ems.v2.repository.EmployeeLeaveRepository;
import ems.v2.repository.EmployeeRepository;
import ems.v2.service.EmployeeLeaveService;
import ems.v2.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeLeaveServiceImpl implements EmployeeLeaveService {
    private EmployeeLeaveRepository employeeLeaveRepository;
    private EmployeeRepository employeeRepository;
    private EmployeeService employeeService;

    @Autowired
    public EmployeeLeaveServiceImpl(EmployeeLeaveRepository employeeLeaveRepository, EmployeeRepository employeeRepository, EmployeeService employeeService) {
        this.employeeLeaveRepository = employeeLeaveRepository;
        this.employeeRepository = employeeRepository;
        this.employeeService = employeeService;
    }

    @Override
    public List<EmployeeLeave> getEmployeeLeaves(Long employeeId) {
        Employee employee = employeeService.getEmployeeById(employeeId);
        return employeeLeaveRepository.findAllByEmployee(employee);
    }



    @Override
    public String addLeave(Employee employee) {
        Optional<Employee> employeeOptional = employeeRepository.findById(employee.getId());
        EmployeeLeave leave = new EmployeeLeave();
        leave.setEmployee(employeeOptional.get());
        leave.setLeaveStatus(true);

        employeeLeaveRepository.save(leave);
        return "Successful";
    }

    @Override
    public String addNewLeave(Employee employee) {
        return null;
    }
}
