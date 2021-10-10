package pl.sda.spring2_travel360.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import pl.sda.spring2_travel360.repository.UserRepository;

@AllArgsConstructor
public class TravelUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByLogin(username)
                .map(TravelUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        //szukamy uzytkownika po bazie danych po jego loginie jezeli go nie ma to rzucamy wyjatek a jezeli jest to zwracamy
        //jego obiekt userDetails (operacja map w Optional)
    }
}
