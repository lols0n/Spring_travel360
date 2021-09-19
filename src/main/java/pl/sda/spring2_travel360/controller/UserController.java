package pl.sda.spring2_travel360.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.sda.spring2_travel360.dto.CountryDto;
import pl.sda.spring2_travel360.dto.UserDto;
import pl.sda.spring2_travel360.request.AddCountryRequest;
import pl.sda.spring2_travel360.request.CreateUserRequest;
import pl.sda.spring2_travel360.response.GetCountriesResponse;
import pl.sda.spring2_travel360.response.GetUsersResponse;
import pl.sda.spring2_travel360.service.UserService;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/user")
public class UserController {

    private final UserService service;

    @GetMapping
    public GetUsersResponse getUsers() {
        var users = service.getAllUser();
        return GetUsersResponse.of(users);
    }


    @PostMapping
    public void addUser(@Valid @RequestBody CreateUserRequest request) {
        var userDto = UserDto.builder()
                .login(request.getLogin())
                .password(request.getPassword())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .build();
        service.addUser(userDto);
    }
}
