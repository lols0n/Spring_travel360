package pl.sda.spring2_travel360.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import pl.sda.spring2_travel360.dto.UserDto;
import pl.sda.spring2_travel360.request.CreateUserRequest;
import pl.sda.spring2_travel360.response.GetUserDetails;
import pl.sda.spring2_travel360.response.GetUsersResponse;
import pl.sda.spring2_travel360.service.UserService;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/user")
public class UserController extends WebMvcConfigurerAdapter {

    private final UserService service;

    @GetMapping
    @Secured("ROLE_ADMIN")
    public GetUsersResponse getUsers() {
        var users = service.getAllUser();
        return GetUsersResponse.of(users);
    }
}
