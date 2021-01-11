package com.jornwer.forumdemo.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
public class AuthenticationRequestDTO {

    @Email(message = "Email should be valid")
    private String email;
    @Size(min = 8, max = 24, message = "Password size must be between 8 and 24")
    private String password;
}
