package ems.v2.repository;

import ems.v2.model.Attendance;
import ems.v2.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
   // Optional<Attendance> findByEmployeeAndTimeAppliedIsBetween(Employee employee, LocalDateTime createdAt, LocalDateTime updatedAt);
//    List<Attendance> findAllByDateBetween(LocalDateTime createdAt, LocalDateTime updatedAt);
   List<Attendance> findAllByDateBetween(LocalDateTime createdAt, LocalDateTime updatedAt);

    List<Attendance> findByEmployeeId(Long id);
//    List<Attendance> findAllByEmployee(Employee employee);
    Optional<Attendance> findByEmployeeAndDateIsBetween(Employee employee, LocalDateTime createdAt, LocalDateTime updatedAt);
}
