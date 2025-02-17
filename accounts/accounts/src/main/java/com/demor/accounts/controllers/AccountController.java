package com.demor.accounts.controllers;

import com.demor.accounts.dtos.CustomerDto;
import com.demor.accounts.dtos.ResponseDto;
import com.demor.accounts.entities.Customer;
import com.demor.accounts.services.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class AccountController {

    private AccountService accountService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@RequestBody CustomerDto request) {

        accountService.createAccount(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseDto(HttpStatus.CREATED, "Account successfully created.")
        );
    }

    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> getAccountDetails(@RequestParam String mobileNumber) {
       CustomerDto customerDto =  accountService.getAccountDetails(mobileNumber);

       return ResponseEntity.ok(customerDto);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccount(@RequestBody CustomerDto request) {
        boolean isUpdated = accountService.UpdateAccount(request);
        if (isUpdated) return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseDto(
                HttpStatus.NO_CONTENT, "Account successfully updated."
        ));

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(
                HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred while updating account: "
                + request.getAccount().getAccountNumber()
        ));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccount(@RequestParam String mobileNumber) {
        boolean isDeleted = accountService.deleteAccount(mobileNumber);
        if (isDeleted) return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseDto(
                HttpStatus.NO_CONTENT, "Account successfully deleted."
        ));

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(
                HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred while deleting account."
        ));
    }

}
