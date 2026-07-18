package com.demor.accounts.controller;

import com.demor.accounts.dto.AccountContactInfoDto;
import com.demor.accounts.dto.CustomerDto;
import com.demor.accounts.dto.ResponseDto;
import com.demor.accounts.service.AccountService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class AccountController {

    private final AccountService accountService;

    @Value("${build.version}")
    private String buildVersion;

    @Autowired
    private Environment environment;

    @Autowired
    private AccountContactInfoDto accountContactInfoDto;

    private static final Logger logger  = LoggerFactory.getLogger(AccountController.class);

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto request) {

        accountService.createAccount(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseDto(HttpStatus.CREATED, "Account successfully created.")
        );
    }

    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> getAccountDetails(
            @RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Phone number must be 10 digits.")
                                                             String mobileNumber) {
       CustomerDto customerDto =  accountService.getAccountDetails(mobileNumber);

       return ResponseEntity.ok(customerDto);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccount(@Valid @RequestBody CustomerDto request) {
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
    public ResponseEntity<ResponseDto> deleteAccount(
            @RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Phone number must be 10 digits.")
                                                         String mobileNumber) {
        boolean isDeleted = accountService.deleteAccount(mobileNumber);
        if (isDeleted) return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseDto(
                HttpStatus.NO_CONTENT, "Account successfully deleted."
        ));

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(
                HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred while deleting account."
        ));
    }

    @Retry(name = "getBuildInfo", fallbackMethod = "getBuildInfoFallback")
    @GetMapping("/build-info")
    public ResponseEntity<String> getBuildInfo() throws TimeoutException {
        logger.debug("getBuildInfo() method invoked");
        return ResponseEntity.ok(buildVersion);
    }

    public ResponseEntity<String> getBuildInfoFallback(Throwable throwable) {
        logger.debug("getBuildInfoFallback() method invoked");
        return ResponseEntity.ok("0.0");
    }

    @RateLimiter(name = "getJavaVersion", fallbackMethod = "getJavaVersionFallback")
    @GetMapping("/java-version")
    public ResponseEntity<String> getJavaVersion() {
        return ResponseEntity.ok(environment.getProperty("JAVA_HOME"));
    }

    public ResponseEntity<String> getJavaVersionFallback(Throwable throwable) {
        return ResponseEntity.ok("Fallback method");
    }

    @GetMapping("/contact-info")
    public ResponseEntity<AccountContactInfoDto> getContactInfo() {
        return ResponseEntity.ok(accountContactInfoDto);
    }

}
