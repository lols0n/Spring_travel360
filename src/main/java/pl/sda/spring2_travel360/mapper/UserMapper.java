package pl.sda.spring2_travel360.mapper;

import org.springframework.stereotype.Service;
import pl.sda.spring2_travel360.domain.User;
import pl.sda.spring2_travel360.dto.UserDto;

@Service
public class UserMapper {

    public UserDto mapUserToDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .login(user.getLogin())
                .email(user.getEmail())
                .password(user.getPassword())
                .firstName(user.getFirstName())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }

    public User mapToUser(UserDto userDto) {
        return User.builder()
                .firstName(userDto.getFirstName())
                .build();
    }
}
