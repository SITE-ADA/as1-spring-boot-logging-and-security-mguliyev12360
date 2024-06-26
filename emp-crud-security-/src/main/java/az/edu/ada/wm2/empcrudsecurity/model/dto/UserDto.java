package az.edu.ada.wm2.empcrudsecurity.model.dto;

import az.edu.ada.wm2.empcrudsecurity.model.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private String roles;
    private List<Role> rolesList;


}
