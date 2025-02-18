package com.demor.accounts.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountDto {

    @NotEmpty(message = "Phone number can not be empty.")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Phone number must be 10 digits.")
    private Long accountNumber;

    @NotEmpty(message = "Account type can not be empty.")
    private String accountType;

    @NotEmpty(message = "Branch address can not be empty.")
    private String branchAddress;
}
