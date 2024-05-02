package az.edu.ada.wm2.empcrudsecurity.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfig {
    @Bean
    public String greetText() {
        return "Hello all, Welcome to the Employee Management System!";
    }

    @Bean
    public String byeText() {
        return "Farewell, until next time!";
    }
}
