package ems.v2.service;

import ems.v2.model.Admin;
import ems.v2.model.Employee;
import org.springframework.stereotype.Service;
//import ems.v2.model.At;

public interface AdminService {
    Admin getAdminByEmail (String email);
    Admin login (String email, String password);

    //public Admin saveEmployee(Employee );

    // Admin getAdminByEmailAndPassword(String email, String password);
}
