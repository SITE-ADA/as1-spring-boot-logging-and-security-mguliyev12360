package az.edu.ada.wm2.empcrudsecurity.init;
import az.edu.ada.wm2.empcrudsecurity.model.entity.Employee;
import az.edu.ada.wm2.empcrudsecurity.model.entity.User;
import az.edu.ada.wm2.empcrudsecurity.repository.EmployeeRepository;
import az.edu.ada.wm2.empcrudsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DbInitializer {

    @Bean
    public ApplicationRunner initUser(UserRepository userRepo,
                                      PasswordEncoder encoder) {
        return (args) -> {
            User adminUser = new User("admin", encoder.encode("admin"),
                    "admin@ada.edu.az");

            userRepo.save(adminUser.addRole("ROLE_ADMIN"));

            userRepo.save(new User("murad", encoder.encode("murad"),
                    "murad@ada.edu.az"));
        };
    }

    @Bean
    @Autowired
    public CommandLineRunner initEmployee(EmployeeRepository employeeRepo) {
        return args -> {
            employeeRepo.save(new Employee(1L, "John", "Doe", 50000.0));
            employeeRepo.save(new Employee(2L, "Jane", "Doe", 55000.0));
            employeeRepo.save(new Employee(3L, "Jim", "Beam", 60000.0));
        };
    }
}
