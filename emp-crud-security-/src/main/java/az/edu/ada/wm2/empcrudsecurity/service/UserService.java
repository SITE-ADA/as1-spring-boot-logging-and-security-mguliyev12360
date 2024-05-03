package az.edu.ada.wm2.empcrudsecurity.service;

import az.edu.ada.wm2.empcrudsecurity.model.entity.User;

import java.util.List;
public interface UserService {
    User getById(Long id);

    List<User> getAllUsers();


    void saveRoleToUser(String roles, Long userId);
}
