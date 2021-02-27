package ems.v2.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@Entity
public class Employee extends BaseModel {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private String address;
    private enum gender {
        MALE, FEMALE, OTHERS
    }
    private enum role{
        JUNIOR, SENIOR, MANAGEMENT
    }
    private  Long Salary;

    @OneToMany(fetch = FetchType.LAZY)
    private List<EmployeeLeave> employeeLeaves;

    @OneToMany
    private List<Attendance> attendance;
}
