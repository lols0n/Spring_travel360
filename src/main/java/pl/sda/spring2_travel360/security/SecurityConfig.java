package pl.sda.spring2_travel360.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
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
}
