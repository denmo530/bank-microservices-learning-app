package com.demor.loans.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorDto {
    private String message;

    private String path;

    private HttpStatus status;

    private LocalDateTime timestamp;

}
