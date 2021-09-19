package pl.sda.spring2_travel360.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;
    private String login;

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private boolean confirmedEmail;
}
