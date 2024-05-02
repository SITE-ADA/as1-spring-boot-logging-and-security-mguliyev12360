package az.edu.ada.wm2.empcrudsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "az.edu.ada.wm2.empcrudsecurity")
@EntityScan("az.edu.ada.wm2.empcrudsecurity.model") // Adjust if your entities are in another package
@EnableJpaRepositories("az.edu.ada.wm2.empcrudsecurity.repository")
public class EmpCrudSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmpCrudSecurityApplication.class, args);
	}

}
