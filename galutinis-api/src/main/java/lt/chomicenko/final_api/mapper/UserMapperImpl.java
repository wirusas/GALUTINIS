package lt.chomicenko.final_api.mapper;

import lt.chomicenko.final_api.dto.UserDto;
import lt.chomicenko.final_api.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toUserDto(User user) {
        if (user == null) {
            return null;
        }

        return new UserDto(user.getId(), user.getUsername(), user.getName(), user.getEmail(), user.getRole());

    }

    @Override
    public List<UserDto> toUserDtoList(List<User> users) {
        return users.stream()
                .map(this::toUserDto)
                .collect(Collectors.toList());
    }
}


