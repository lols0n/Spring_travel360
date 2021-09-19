package pl.sda.spring2_travel360.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.sda.spring2_travel360.dto.UserDto;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class GetUsersResponse {

    private List<UserDto> users;
}
