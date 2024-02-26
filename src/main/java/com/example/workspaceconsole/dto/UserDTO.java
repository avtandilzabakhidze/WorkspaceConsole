package com.example.workspaceconsole.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDTO {
    private long id;

    @NotBlank(message = "Personal number is required")
    @Size(max = 20, message = "Personal number must be less than or equal to 20 characters")
    private String personalNo;

    @NotBlank(message = "Full name is required")
    @Size(max = 100, message = "Full name must be less than or equal to 100 characters")
    private String fullName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @Pattern(regexp = "\\d{10}", message = "Phone number must be a 10-digit number")
    private String phoneNumber;

    @NotBlank(message = "Address is required")
    @Size(max = 200, message = "Address must be less than or equal to 200 characters")
    private String address;
}
