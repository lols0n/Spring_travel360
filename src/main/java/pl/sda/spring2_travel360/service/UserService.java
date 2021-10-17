package pl.sda.spring2_travel360.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.sda.spring2_travel360.dto.UserDto;
import pl.sda.spring2_travel360.mapper.UserMapper;
import pl.sda.spring2_travel360.repository.UserRepository;
import pl.sda.spring2_travel360.security.TravelUserDetails;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    PasswordEncoder encoder = new BCryptPasswordEncoder();

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
        var passwordHash = encoder.encode(user.getPassword());
        user.setPassword(passwordHash);
        userRepository.save(user);
        emailService.sendEmail(userDto.getEmail(),"Witam w travel360", "Witaj. Założyłeś konto w travel 360. link aktywacyjny "
                + "http://localhost:8080/v1/user/confirmation/"
                + user.getConfirmationId());
    }


    public Optional<UserDto> getUser(Long userId) {
        return userRepository.findById(userId)
                .map(userMapper::mapUserToDto);
    }

    public boolean checkIfUserExists(String login, String email) {
        return userRepository.existsByLoginOrEmail(login, email);
    }

    public void confirmUser(String confrimationId) {
        var user = userRepository.findByConfirmationId(confrimationId);
        user.ifPresent(u -> {
            u.setConfirmationStatus(true);
            userRepository.save(u);
        });
    }

    public void resetPasswordForUser(Long userId, String oldPassword, String newPassword) {
    var user = userRepository.findById(userId)
                    .get();
        if(encoder.matches(oldPassword,user.getPassword())) {

            user.setPassword(encoder.encode(newPassword));
            userRepository.save(user);
        } else {
            throw new BadCredentialsException("Wrong old pasword");
        }
    }
}
