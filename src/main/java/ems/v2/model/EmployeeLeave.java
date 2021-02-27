package ems.v2.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Date;

@Setter
@Getter
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeLeave extends BaseModel{

    private LocalDateTime date;

    private String leaveReason;
    private boolean leaveStatus;



    @ManyToOne
    private Employee employee;

//    public void setLeaveStatus(boolean b) {
//    }
}
