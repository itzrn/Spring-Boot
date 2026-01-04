package com.codingshuttle.youtube.hospitalManagement.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig {
    // when we make any class as configuration, we can add beans inside it

    private final PasswordEncoder passwordEncoder;
    private final JWTAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {  // here making the singleton bean of SecurityFilterChain
        httpSecurity
                .csrf(csrfConfig -> csrfConfig.disable())
                .sessionManagement(sessionConfig -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/public/**", "/auth/**").permitAll() // this is permitting all the api inside public as public
//                        .requestMatchers("/admin/**").hasRole("ADMIN") // this is permitting an access to admin after login
//                        .requestMatchers("/patients/**").hasRole("PATIENT") // this will allow to access only patient
//                        .requestMatchers("/doctors/**").hasAnyRole("DOCTOR", "PATIENT") // can be accessed by any two

                                .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
//                .formLogin(Customizer.withDefaults());
        return httpSecurity.build();
    }

//    @Bean // bean is something you can put anywhere which gets automatically checked
    UserDetailsService userDetailsService(){
        UserDetails user1 = User.withUsername("admin")
                .password(passwordEncoder.encode("pass"))
                .roles("ADMIN")
                .build();
        UserDetails user2 = User.withUsername("patient")
                .password(passwordEncoder.encode("pass")) // passwords always get store in encoded form
                .roles("PATIENT")
                .build();

        return new InMemoryUserDetailsManager(user1, user2);
    } // now we don't want to use in memory username and password, so removed this particular bean


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}


/*

How spring security works behind?
    The request which comes it pass through tomcat server and get reach to servletfilterchain
    now srvletfilterchain contains many filters inside which check to the coming request (different filters check differently)

    as we added spring security as dependency, it will add security filter chain inside the servlet filter chain
    security filter chain have some of its checker which also add the checker function of servlet filter chain

    now the request will go through security filter chain before going to the controller layer

 */