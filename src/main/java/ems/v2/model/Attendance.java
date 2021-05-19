package ems.v2.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table
public class Attendance extends BaseModel{
    private boolean attendanceStatus;
    @CreationTimestamp
    private LocalDateTime date;
    @ManyToOne
    private Employee employee;
}
