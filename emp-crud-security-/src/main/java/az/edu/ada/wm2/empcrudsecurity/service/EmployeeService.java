package az.edu.ada.wm2.empcrudsecurity.service;


import az.edu.ada.wm2.empcrudsecurity.model.entity.Employee;
import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();

    Employee save(Employee employee);

    Employee getById(Long id);

    void deleteById(Long id);


}
