package pl.sda.spring2_travel360.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.LifecycleState;
import pl.sda.spring2_travel360.dto.CountryDto;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class GetCountriesResponse {

    private List<CountryDto> counturies;
}
