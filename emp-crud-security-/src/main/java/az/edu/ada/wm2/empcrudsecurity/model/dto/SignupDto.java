package az.edu.ada.wm2.empcrudsecurity.model.dto;
import az.edu.ada.wm2.empcrudsecurity.model.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.Data;

@Data
public class SignupDto {

    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

//    public User toUser(PasswordEncoder encoder) {
//        User newUser = new User();
//        newUser.setUsername(this.username);
//        newUser.setPassword(encoder.encode(this.password));
//        newUser.setEmail(this.email);
//        return newUser;
//    }

    public User toUser(PasswordEncoder encoder) {
        return new User(username, encoder.encode(password), email );
    }
}
