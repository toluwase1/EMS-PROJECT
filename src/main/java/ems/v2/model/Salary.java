package ems.v2.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Setter
@Getter
public class Salary extends BaseModel{
    private LocalDateTime date;
    private Long amount;
    private String month;

    @ManyToOne
    private Employee employee;
}
