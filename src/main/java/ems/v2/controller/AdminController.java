package ems.v2.controller;

import ems.v2.model.Attendance;
import ems.v2.model.Employee;
import ems.v2.model.Salary;
import ems.v2.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class AdminController {
    private AdminService adminService;
    private EmployeeService employeeService;
    private AttendanceService attendanceService;
    private EmployeeLeaveService employeeLeaveService;
    private SalaryService salaryService;
    @Autowired
    public AdminController(AdminService adminService, EmployeeService employeeService) {
        this.adminService = adminService;
        this.employeeService=employeeService;

    }

    @GetMapping("/dashboard")
    public String dashboardRoute(Model model, HttpSession session){
        Object adminObject = session.getAttribute("admin");
        if (adminObject == null) return "redirect:/auth/login";

        model.addAttribute("listEmployees", employeeService.findAllEmployees());
        return "dashboard1";
    }

    @GetMapping("/newform")
    public String newEmployeeForm (Model model, HttpSession session){
        Object adminObject = session.getAttribute("admin");
        if (adminObject == null) return "redirect:/auth/login";

        //create model attribute to bind form data
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "newform";
    }

    @PostMapping("/save")
    public String saveEmployeeMethod(@ModelAttribute("employee") Employee employee, HttpSession session){
        Object adminObject = session.getAttribute("admin");
        if (adminObject == null) return "redirect:/auth/login";

        employeeService.saveEmployee(employee);
        return "redirect:/dashboard";
    }

    @GetMapping("/update-form/{id}")
    public String updateFormDisplay(@PathVariable( value = "id") long id, Model model, HttpSession session){
        Object adminObject = session.getAttribute("admin");
        if (adminObject == null) return "redirect:/auth/login";

//        //set employee as a model attribute to pre-populate the form
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "update-employee";
    }



    @PostMapping("/update-form/{id}")
    public String updateEmployee(@ModelAttribute("employee") Employee employee, @PathVariable( value = "id") long id, HttpSession session){
        Object adminObject = session.getAttribute("admin");
        if (adminObject == null) return "redirect:/auth/login";

        //save employee to database
        employeeService.updateEmployee(employee, id);
        return "redirect:/dashboard";
    }

    @GetMapping("/deleteemployee/{id}")
    public String deleteMethod(@PathVariable ( value = "id") long id, Model model, HttpSession session){
        Object adminObject = session.getAttribute("admin");
        if (adminObject == null) return "redirect:/auth/login";

        // call delete employee method
        this.employeeService.deleteEmployee(id);
        return "redirect:/dashboard";
    }

    @GetMapping("/viewemployee/{id}")
    public String showAttendance(@PathVariable ( value = "id") long id, Model model, HttpSession session){
        Object adminObject = session.getAttribute("admin");
        if (adminObject == null) return "redirect:/auth/login";

        // call delete employee method
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("attendanceList", attendanceService.getAttendanceByEmployeeId(employee));
        model.addAttribute("employee", employee);
        return "view-employee-page";
    }

//    @GetMapping("/employee-salary/{id}")
//    public String viewEmployeeSalary(@PathVariable ( value = "id") long id, Model model, HttpSession session){
//        Object adminObject = session.getAttribute("admin");
//        if (adminObject == null) return "redirect:/auth/login";
//
//        // call delete employee method
//        Employee employee = employeeService.getEmployeeById(id);
//        model.addAttribute("salaryList", salaryService.getSalaries(id));
//        model.addAttribute("employee", employee);
//        return "view-employee-page";
//    }

    @GetMapping("/employee-leave/{id}")
    public String showEmployeeLeaves(@PathVariable ( value = "id") long id, Model model, HttpSession session){
        Object adminObject = session.getAttribute("admin");
        if (adminObject == null) return "redirect:/auth/login";

        // call delete employee method
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("leaveList", employeeLeaveService.getEmployeeLeaves(id));
        model.addAttribute("employee", employee);
        return "view-employee-page";
    }



    @GetMapping("/employee")
    public String employee(Model model, HttpSession session){
        Object adminObject = session.getAttribute("employee");
        if (adminObject == null) return "redirect:/auth/login";

        model.addAttribute("employee", new Employee());
        return "employee";
    }
//
    @PostMapping("/mark-attendance")
    public String markAttendance(HttpSession session, RedirectAttributes redirectAttributes){
        Employee employee = (Employee) session.getAttribute("employee");
        Map<String, String> response = attendanceService.addAttendance(employee);
        if (response.containsKey("alreadyMarked")){
            redirectAttributes.addFlashAttribute("marked", response.get("alreadyMarked"));
            return "redirect:/employee";
        }else if (response.containsKey("notFound")){
            redirectAttributes.addFlashAttribute("notFound", response.get("notFound"));
            return "redirect:/employee";
        }else if (response.containsKey("beforeTime")){
            redirectAttributes.addFlashAttribute("beforeTime", response.get("beforeTime"));
            return "redirect:/employee";
        }else if (response.containsKey("afterTime")){
            redirectAttributes.addFlashAttribute("afterTime", response.get("afterTime"));
            return "redirect:/employee";
        }else if(response.containsKey("success")) {
            redirectAttributes.addFlashAttribute("success", response.get("success"));
            return "redirect:/employee";
        }
        return "employee";
    }

    @PostMapping("/leave-application")
    public String requestForLeave(HttpSession session){
        Employee employee = (Employee) session.getAttribute("employee");
        employeeLeaveService.addLeave(employee);
        return "employee";
    }


    @GetMapping("/fix-salary/{id}")
    public String updateSalary(@PathVariable (value = "id") long id, HttpSession session, Model model){
        Object adminObject = session.getAttribute("admin");
        if (adminObject == null) return "redirect:/auth/login";
        Salary salary = new Salary();
        model.addAttribute("salary", salary);
        model.addAttribute("employee", employeeService.getEmployeeById(id));
        return "fix-employee-salary";
    }
    //
////
////
    @PostMapping("/save-salary/{id}")
    public String saveEmployeeSalary(@ModelAttribute("salary") Salary salary, @PathVariable (value = "id") long id, HttpSession session){
        salaryService.saveEmployeeSalary(id, salary);
        return "redirect:/dashboard";
    }

    @GetMapping("/daily-attendance")
    public String dailyAttendance(Model model, HttpSession session){
        Object adminObject = session.getAttribute("admin");
        if (adminObject == null) return "redirect:/auth/login";

        List<Attendance> listAttendance = attendanceService.findAllAttendance();
        List<Employee> listEmployeeAttendance = listAttendance.stream().map(Attendance::getEmployee).collect(Collectors.toList());
        model.addAttribute("listEmployeesAttendance", listEmployeeAttendance);
        return "view-daily-attendance";
    }



    @GetMapping("/employee-attendance")
    public String employeeAttendance(HttpSession session){
        Object userObj = session.getAttribute("employee");
        if (userObj == null) return "redirect:/auth/login";


        return "employee-attendance";
    }


    @GetMapping("/employee-salary/{id}")
    public String employeeSalary(@PathVariable(value = "id") long Id, HttpSession session){
        Object userObj = session.getAttribute("employee");
        if (userObj == null) return "redirect:/auth/login";


        return "employee-salary";
    }



    @GetMapping("/admin_empl-leave")
    public String showEmployeeLeave(HttpSession session){
        Object adminObject = session.getAttribute("admin");
        if (adminObject == null) return "redirect:/auth/login";

//        Employee employee = employeeService.getEmployeeById(id);
//        model.addAttribute("employee", employee);

        return "redirect:/dashboard";
    }




}