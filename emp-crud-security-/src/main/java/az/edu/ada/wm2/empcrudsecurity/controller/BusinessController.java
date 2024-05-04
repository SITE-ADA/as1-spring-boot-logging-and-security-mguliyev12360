package az.edu.ada.wm2.empcrudsecurity.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BusinessController {

    // Admin home page
    @GetMapping("/admin")
    public String getAdminsHomePage(Model model) {
        model.addAttribute("message", "Welcome to the Admin Dashboard");
        return "admin/home";
    }

    // User home page
    @GetMapping("/user")
    public String getUsersHomePage(Model model) {
        model.addAttribute("message", "Welcome to the User Dashboard");
        return "user/home";
    }

//    @GetMapping("/employee/dashboard")
//    public String getEmployeeDashboard(Model model) {
//        model.addAttribute("message", "Welcome to the Employee Dashboard");
//        return "employee/dashboard"; // You might want an employee-specific dashboard
//    }
}
