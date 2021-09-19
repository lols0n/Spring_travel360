package pl.sda.spring2_travel360.request;

import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.*;

@Data
public class CreateUserRequest {

    @NonNull
    @NotEmpty
    private String login;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @Size(min = 8, max = 32)
    private String password;

    @Email
    private String email;

    @Pattern(regexp = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$")
    private String phoneNumber;
}
