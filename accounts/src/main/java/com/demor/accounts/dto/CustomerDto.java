package com.demor.accounts.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDto {

    @NotEmpty(message = "Name can not be empty.")
    @Size(min = 5, max = 30, message = "Customer name should be between 5 - 30 characters")
    private String name;

    @Email(message = "Email address must be a valid email.")
    @NotEmpty(message = "Email address can not be empty.")
    private String email;

    @NotEmpty(message = "Phone number can not be empty.")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Phone number must be 10 digits.")
    private String mobileNumber;

    private AccountDto account;
}
