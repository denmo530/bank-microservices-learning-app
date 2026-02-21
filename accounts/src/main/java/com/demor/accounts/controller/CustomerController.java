package com.demor.accounts.controller;

import com.demor.accounts.dto.CustomerDetailsDto;
import com.demor.accounts.service.CustomerService;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
@RequiredArgsConstructor
public class CustomerController {
    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    private final CustomerService customerService;

    @GetMapping("/fetchCustomerDetails")
    public ResponseEntity<CustomerDetailsDto> fetchCustomerDetails(@RequestHeader("bank-correlation-id")
                                                                       String correlationId,
            @RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Phone number must be 10 digits.")
                                                                    String mobileNumber) {
        logger.debug("bank-correlation-id found: {}", correlationId);
        return ResponseEntity.ok(customerService.fetchCustomerDetails(mobileNumber, correlationId));
    }
}
