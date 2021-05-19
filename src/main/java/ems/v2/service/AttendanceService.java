package ems.v2.service;

import ems.v2.model.Attendance;
import ems.v2.model.Employee;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface AttendanceService {
    Map<String, String> addAttendance(Employee employee);

    List<Attendance> findAllAttendance();

//    List<Attendance> getAttendanceByEmployeeId(Employee employee);
List<Attendance> getAttendanceByEmployeeId(Long id);
}
