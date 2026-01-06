package com.spring.learning.config;

import com.spring.learning.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
//@Profile("prod") // this allows when the application depends on application-prod.properties then this class will get created as bean in spring
// in the same way we have @ActiveProfile annotations also
public class SpringSecurity {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

//    public SpringSecurity(UserDetailsServiceImpl userDetailsService,
//                          PasswordEncoder passwordEncoder) {
//        this.userDetailsService = userDetailsService;
//        this.passwordEncoder = passwordEncoder;
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // this is basically which request is need to be secure and which not
        return http.authorizeHttpRequests(request -> request
                        .requestMatchers("/public/**").permitAll()
                        .requestMatchers("/journalV2/**", "/user/**").authenticated()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable) // csrf -> cross site request forgery
                .build();

        // .authorizeHttpRequests -> this tell spring to start authorizing the requests

        // when we use the .formLogin() method in our security configuration without specifying .loginPage(""/custom-path), the default login page become active

        // spring security controller that handles the /login path. This is responsible for rendering the default login form when a get request is made to /login

        // By default, Spring Security also provides logout functionality. When .logout() is configured, a POST request to /logout will log the user out and invalidate their session.

        // Basic Authentication, by its design, is stateless -> means that every request work individually
        // suppose if there is request done then for another request you have to again put the userName and password to use that request

        /*
        Some applications do mix Basic Authentication with session management for various reasons. This isn't standard
        behaviour and requires additional setup and logic. In such scenario behaviour, once the user's credentials are varified
        via Basic Authentication, a session might be established, and the client is provided a session cookie.
        This way, the client won't need to send the authorization header with every request, and the server can rely on the session
        cookie to identify the authenticated user.
         */
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }
}
