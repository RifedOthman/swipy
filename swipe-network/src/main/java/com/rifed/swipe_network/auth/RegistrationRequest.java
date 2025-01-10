package com.rifed.swipe_network.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegistrationRequest {

    @NotBlank(message="Firstname is mandatory")
    private String firstName;

    @NotBlank(message="Lastname is mandatory")
    private String lastName;

    @Email(message="Email is not formatted")
    @NotBlank(message="Email is mandatory")
    private String email;

    @NotBlank(message="Password is mandatory")
    @Size(min = 8, message="Password should be at least 8 characters")
    private String password;
}
