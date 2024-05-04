package az.edu.ada.wm2.empcrudsecurity.controller;

import az.edu.ada.wm2.empcrudsecurity.model.dto.SignupDto;
import az.edu.ada.wm2.empcrudsecurity.model.entity.User;
import az.edu.ada.wm2.empcrudsecurity.repository.UserRepository;

import az.edu.ada.wm2.empcrudsecurity.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class SignupController {


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private final UserService userService;

    @Autowired
    public SignupController(PasswordEncoder passwordEncoder, UserService userService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @GetMapping
    public String showSignUp(Model model) {
        model.addAttribute("signupDto", new SignupDto());
        return "signup";
    }


    @PostMapping()
    public String doSignUp(@ModelAttribute("signupDto") @Valid SignupDto signupDto, BindingResult result) {
        if (result.hasErrors()) {
            return "signup"; // Stay on the signup page if there are form errors
        }
        // Continue with user creation and redirect after successful signup
        User newUser = signupDto.toUser(passwordEncoder);  // Use the injected PasswordEncoder directly
        userRepository.save(newUser);
        return "redirect:/login"; // Redirect to login page only after successful signup
    }


}
