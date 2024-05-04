package az.edu.ada.wm2.empcrudsecurity.service.impl;
import az.edu.ada.wm2.empcrudsecurity.model.entity.User;
import az.edu.ada.wm2.empcrudsecurity.repository.UserRepository;
import az.edu.ada.wm2.empcrudsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepo) {
        this.userRepository = userRepo;
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> getAllUsers() {
        return (List<User>) userRepository.getAllUsersUsingJPAQuery();
    }

    @Override
    public void saveRoleToUser(String roles, Long userId) {
        userRepository.saveToUser(roles, userId);
    }

}
