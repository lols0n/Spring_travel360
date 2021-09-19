package pl.sda.spring2_travel360.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
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
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private boolean confirmedEmail;
}
