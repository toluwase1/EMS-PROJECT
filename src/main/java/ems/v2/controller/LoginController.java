package ems.v2.controller;

import ems.v2.model.Admin;
import ems.v2.model.Employee;
import ems.v2.service.AdminService;
import ems.v2.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth")
public class LoginController {
    private EmployeeService employeeService;
    private AdminService adminService;

    @Autowired
    public LoginController(EmployeeService employeeService, AdminService adminService) {
        this.employeeService = employeeService;
        this.adminService = adminService;
    }

    @GetMapping("/login")
    public String login (Model model){
        model.addAttribute("admin", new Admin());
        model.addAttribute("employee", new Employee());
        model.addAttribute("invalid", null);
       // model.addAttribute()
        return "login";
    }

    //Login logic
    @PostMapping("/login")
    public String login (HttpSession session, Admin admin, Model model, Employee employee) {
        Admin onlyAdmin = adminService.login(admin.getEmail(), admin.getPassword());
        Employee onlyEmployee = employeeService.login(employee.getEmail(), employee.getPassword());
        if (onlyAdmin == null && onlyEmployee == null) {
            model.addAttribute("errorMessage", "Invalid Username/Password."); /** error message for wrong email/password input
                                                                                                            */
            return "login";
        } else if(onlyAdmin != null) {
            session.setAttribute("admin", onlyAdmin);
            return "redirect:/dashboard";
        }else{
            model.addAttribute("name", onlyEmployee.getFirstName());
            session.setAttribute("employee", onlyEmployee);
            return "redirect:/employee";
        }
    }

}
