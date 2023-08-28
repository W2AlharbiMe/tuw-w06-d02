package com.example.week05d02blog.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @NotEmpty(message = "the username field is required.")
    @Size(min = 4, max = 64, message = "the username value must be or between 4 and 64 chars.") // this will apply check constraint by default
    private String username;


    @NotEmpty(message = "the password field is required.")
    @Size(min = 8, max = 64, message = "the password value must be or between 8 and 64 chars.") // this will apply check constraint by default
    private String password;


    @NotEmpty(message = "the email field is required.")
    @Size(min = 6, message = "the email value must be more than or equal to 6 chars.") // this will apply check constraint by default
    @Email(message = "invalid email")
    private String email;
}
