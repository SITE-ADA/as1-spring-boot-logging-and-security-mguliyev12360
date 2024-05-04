package az.edu.ada.wm2.empcrudsecurity.controller;
import az.edu.ada.wm2.empcrudsecurity.model.entity.Employee;
import az.edu.ada.wm2.empcrudsecurity.model.entity.User;
import az.edu.ada.wm2.empcrudsecurity.service.EmployeeService;
import az.edu.ada.wm2.empcrudsecurity.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/user/")
public class EmployeeController {
    static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    private final EmployeeService employeeService;
    private UserService userService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("employees")
    public String listEmployees(Model model) {
        List<Employee> employees = employeeService.getAllEmployees();
        LOGGER.info("Employees listed");
        model.addAttribute("employees", employees);
        return "employee/index";
    }





    @GetMapping("employees/new")
    public String createNewEmployee(Model model) {
        model.addAttribute("employee", new Employee());
        LOGGER.info("New Employee Form is displayed");
        return "employee/new";
    }

    @PostMapping("employees/new")
    public String saveEmployee(Model model, @ModelAttribute("employee") @Valid Employee employee, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "employee/new";
        }
        employeeService.save(employee);
        LOGGER.info("Employee saved: {}", employee);
        return "redirect:/user/employees";
    }


    @GetMapping("employees/{id}/update")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Employee employee = employeeService.getById(id);
        if (employee == null) {
            LOGGER.error("Employee not found with id: {}", id);
            return "redirect:/user/employees";  // Redirect or show an error message
        }
        model.addAttribute("employee", employeeService.getById(id));
        return "employee/update";
    }

    @PostMapping("employees/{id}/update")
    public String updateEmployee(@ModelAttribute("employee") @Valid Employee employee, BindingResult bindingResult, @PathVariable Long id) {
        if (bindingResult.hasErrors()) {
            return "employee/update";
        }
        employee.setId(id); // Ensure the ID is set for updating the right employee
        employeeService.save(employee);
        LOGGER.info("Employee updated: {}", employee);
        return "redirect:/user/employees";
    }

    @GetMapping("employees/{id}/delete")
    public String deleteEmployee(@PathVariable Long id) {
        employeeService.deleteById(id);
        LOGGER.info("Employee deleted with id: {}", id);
        return "redirect:/user/employees";
    }

}
