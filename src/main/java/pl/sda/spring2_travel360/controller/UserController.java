package pl.sda.spring2_travel360.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import pl.sda.spring2_travel360.dto.UserDto;
import pl.sda.spring2_travel360.request.CreateUserRequest;
import pl.sda.spring2_travel360.request.ResetPasswordRequest;
import pl.sda.spring2_travel360.response.GetUserDetails;
import pl.sda.spring2_travel360.response.GetUsersResponse;
import pl.sda.spring2_travel360.security.TravelUserDetails;
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

    @GetMapping("/{id}")
    public ResponseEntity<GetUserDetails> getUsersDetails(@PathVariable Long id) {
        var user = service.getUser(id);
        return user.map(userDto -> ResponseEntity.ok(new GetUserDetails(userDto)))
                .orElseGet(() -> ResponseEntity.unprocessableEntity().build());
    }

    @GetMapping("/confirmation/{confrimationId}")
    public void getConfirmedUsers(@PathVariable String confrimationId) {
         service.confirmUser(confrimationId);
    }

    @PutMapping("/reset-password")
    public ResponseEntity<Void> resetPassword(@Valid @RequestBody ResetPasswordRequest request) {
        var user = (TravelUserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        service.resetPasswordForUser(user.getUserId(), request.getOldPassword(), request.getNewPassword());
        return ResponseEntity.ok().build();
    }
}
// przypomnienie hasla