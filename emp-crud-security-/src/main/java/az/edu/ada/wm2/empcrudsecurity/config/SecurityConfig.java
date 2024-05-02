package az.edu.ada.wm2.empcrudsecurity.config;
import az.edu.ada.wm2.empcrudsecurity.model.entity.User;
import az.edu.ada.wm2.empcrudsecurity.repository.UserRepository;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.StaticHeadersWriter;

import java.util.Optional;


@Configuration
public class SecurityConfig {
    public static String username;

    @Bean
    public static String currentUser() {
        return username;
    }
    public static void nullUser() {
        username = null;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return username -> {
            SecurityConfig.username = username;
            System.out.println("username: " + username);
            Optional<User> res = userRepository.findByUsername(username);
            return res.orElseThrow(() ->
                    new UsernameNotFoundException("User not found: " + username)
            );
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .headers(headers -> headers
                        .addHeaderWriter(new StaticHeadersWriter("X-Frame-Options", "SAMEORIGIN"))  // Or use "ALLOW-FROM https://example.com"
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/user/*").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/admin/**", "/user/employees/**").hasRole("ADMIN")
                        .requestMatchers("/", "/signup/**").permitAll()
                        .requestMatchers(PathRequest.toH2Console()).permitAll()
                        .anyRequest().authenticated()
                )

                .formLogin(form -> form
                        .loginPage("/login")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .permitAll()
                );

        return http.build();
    }
}