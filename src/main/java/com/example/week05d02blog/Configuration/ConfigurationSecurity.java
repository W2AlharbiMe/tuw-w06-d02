package com.example.week05d02blog.Configuration;

import com.example.week05d02blog.Service.SecurityUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ConfigurationSecurity {

    private final SecurityUserDetailsService userDetailsService;

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();

        // set custom user details service
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);

        // set password hash algorithm
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());

        return daoAuthenticationProvider;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 1.1. disable CSRF
                .csrf().disable()
                // 1.2. use session management
                .sessionManagement()
                // 1.3. when to create the session ?
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                // use the custom authentication provider
                .authenticationProvider(daoAuthenticationProvider())

                // 2.1 specify authorized requests
                .authorizeHttpRequests()
                // 2.2 permit endpoint register, so users can create account.
                .requestMatchers(HttpMethod.POST, "/api/v1/auth/sign-up").permitAll()
                // 2.3 ensure that only users with role `ADMIN` can view all registered users.
                .requestMatchers("/api/v1/admin/**").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/blogs/**").hasAuthority("USER")
                // 2.4 to use or to view any other endpoints the user must be logged in.
                .anyRequest().authenticated()

                .and()

                // 3.1 configure logout options
                .logout().logoutUrl("/api/v1/auth/logout")
                // 3.2 delete cookie from user browser
                .deleteCookies("JSESSIONID")
                // 3.3 delete session from server
                .invalidateHttpSession(true)

                .and()

                // 4.1 set authentication type
                .httpBasic()
        ;

        return http.build();
    }

}
