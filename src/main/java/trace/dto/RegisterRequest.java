package trace.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
        @NotBlank(message = "We want to trace an username")
        String username,
        @NotBlank(message = "We want to trace an email")
        @Email(message = "We want to trace a valid email id")
        String email,
        @NotBlank(message = "We want to trace a password")
        @Size(min = 8 , message = "Password should atleast be of 8 traceable characters")
        String password
) {}