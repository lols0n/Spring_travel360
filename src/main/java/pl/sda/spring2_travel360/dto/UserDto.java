package pl.sda.spring2_travel360.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;
    private String login;
    @JsonInclude(JsonInclude.Include.NON_NULL) //password sie nie wyswietli przy metodzie get
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private boolean confirmationStatus;
    private String confirmationId;
    private LocalDateTime validTo;
}
