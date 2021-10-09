
package pl.sda.spring2_travel360.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true) // włączenie roli
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .mvcMatchers("/v1/country/**").permitAll()
                .mvcMatchers(HttpMethod.POST, "/v1/user/registration").anonymous()
                .mvcMatchers("/v1/user/**").authenticated()
                .anyRequest().permitAll()
                .and()
                .httpBasic()
                .and()
                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("pawel")
                .password("$2a$10$IFQbIeOiHRY6i0vQvl0og.5dcJ97bEQfg.sQSvtREiz4zw8guDiP6")  // {noop} - nic nie hashuje hasła, może być np. bcrypt, argon2
                .roles("USER")
                .and()
                .withUser("admin")
                .password("$2a$10$NVrFwWkAHgy4nyUFIkBK9ODGitKf0Wmu4SevlobZMOdBCWVLly0ha")
                .roles("ADMIN");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}