package lt.chomicenko.final_api.mapper;

import lt.chomicenko.final_api.dto.UserDto;
import lt.chomicenko.final_api.model.User;

import java.util.List;

public interface UserMapper {
    UserDto toUserDto(User user);

    List<UserDto> toUserDtoList(List<User> users);

    }
