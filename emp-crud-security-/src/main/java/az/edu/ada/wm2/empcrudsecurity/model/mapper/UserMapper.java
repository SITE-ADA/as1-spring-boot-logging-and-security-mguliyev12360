package az.edu.ada.wm2.empcrudsecurity.model.mapper;
import az.edu.ada.wm2.empcrudsecurity.model.dto.UserDto;
import az.edu.ada.wm2.empcrudsecurity.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    List<UserDto> userListToUserDtoList(List<User> user);

    UserDto userToUserDto(User user);


}