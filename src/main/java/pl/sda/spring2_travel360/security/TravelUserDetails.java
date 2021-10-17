package pl.sda.spring2_travel360.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.sda.spring2_travel360.domain.User;

import java.util.Collection;
import java.util.stream.Collectors;

@AllArgsConstructor
public class TravelUserDetails implements UserDetails {

    private final User user;

    public Long getUserId(){
        return user.getId();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getRoles()
                .stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; //todo do zmiany
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; //todo do zmiany
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; //todo do zmiany
    }

    @Override
    public boolean isEnabled() {
        return user.isConfirmationStatus();
    }
}
