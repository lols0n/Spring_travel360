package pl.sda.spring2_travel360.response;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sda.spring2_travel360.request.RegistrationUserRequest;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/user/registration")
public class UserRegistrationController {


    @PostMapping
    public ResponseEntity<Void> addUser(@RequestBody RegistrationUserRequest request) {
        return ResponseEntity.ok().build();
    }

}
