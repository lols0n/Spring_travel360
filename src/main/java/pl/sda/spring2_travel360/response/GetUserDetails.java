package pl.sda.spring2_travel360.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.sda.spring2_travel360.dto.UserDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetUserDetails {


    private UserDto user;
}
