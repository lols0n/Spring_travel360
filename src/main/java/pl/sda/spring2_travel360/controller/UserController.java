package pl.sda.spring2_travel360.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import pl.sda.spring2_travel360.dto.UserDto;
import pl.sda.spring2_travel360.request.CreateUserRequest;
import pl.sda.spring2_travel360.response.GetUserDetails;
import pl.sda.spring2_travel360.response.GetUsersResponse;
import pl.sda.spring2_travel360.service.UserService;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/user")
public class UserController extends WebMvcConfigurerAdapter {

    private final UserService service;

    @GetMapping
    public GetUsersResponse getUsers() {
        var users = service.getAllUser();
        return GetUsersResponse.of(users);
    }

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

//    @GetMapping("/{id}")
//    public ResponseEntity<GetUserDetails> getUserDetails(@PathVariable Long id) {
//        var user = service.getUser(id);
//        return user.map(userDto -> ResponseEntity.ok(new GetUserDetails(userDto)))
//                .orElseGet(() -> ResponseEntity.notFound().build());
//
//    }
//
//    @RequestMapping(value = "/signup", method = RequestMethod.POST)
//    public


}
