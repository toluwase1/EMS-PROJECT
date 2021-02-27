package ems.v2.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Entity
@Table
public class Attendance extends BaseModel{
    private boolean attendanceStatus;
    private LocalDateTime date;
    @ManyToOne
    private Employee employee;


}
