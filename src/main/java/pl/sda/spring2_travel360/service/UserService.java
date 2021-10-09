package pl.sda.spring2_travel360.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.sda.spring2_travel360.dto.UserDto;
import pl.sda.spring2_travel360.mapper.UserMapper;
import pl.sda.spring2_travel360.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final EmailService emailService;

    public List<UserDto> getAllUser() {
        log.info("Get Users");
        return userRepository.findAll()
                .stream()
                .map(userMapper::mapUserToDto)
                .collect(Collectors.toList());
    }

    public void addUser(UserDto userDto) {
        log.info("Add User: {}", userDto);
        var user = userMapper.mapToUser(userDto);
        var confrimationId = UUID.randomUUID().toString();
        var validTo = LocalDateTime.now().plusMinutes(15);
        user.setConfirmationId(confrimationId);
        user.setValidTo(validTo);
        userRepository.save(user);
//        emailService.sendEmail(userDto.getEmail(),"Witamy w travel360", "witamy. Twoj kod aktywacyjny: "
//                + user.getConfirmationId()) + "\n" + "Link aktywacyjny do konta: ";
    }


    public Optional<UserDto> getUser(Long userId) {
       return userRepository.findById(userId)
                .map(userMapper::mapUserToDto);
    }

    public boolean checkIfUserExists(String login, String email) {
        return userRepository.existsByLoginOrEmail(login,email);
    }

    public void confirmUser(String confrimationId) {
        var user = userRepository.findByConfirmationId(confrimationId);
        user.ifPresent(u -> {u.setConfirmationStatus(true);});
    }
}
