package pl.sda.spring2_travel360.response;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sda.spring2_travel360.dto.UserDto;
import pl.sda.spring2_travel360.request.CreateUserRequest;
import pl.sda.spring2_travel360.request.RegistrationUserRequest;
import pl.sda.spring2_travel360.service.UserService;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/user/registration")
public class UserRegistrationController {

    private final UserService service;

    @PostMapping
    public ResponseEntity<Void> addUser(@Valid @RequestBody CreateUserRequest request) {
        var isExist = service.checkIfUserExists(request.getLogin(), request.getEmail());
        if (isExist) {
            ResponseEntity.unprocessableEntity().build();
        }
        var userDto = UserDto.builder()
                .login(request.getLogin())
                .password(request.getPassword())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .build();
        service.addUser(userDto);
        return ResponseEntity.ok().build();
    }

}
