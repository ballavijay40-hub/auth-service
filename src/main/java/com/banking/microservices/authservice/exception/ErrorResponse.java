package com.banking.microservices.authservice.exception;



import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class ErrorResponse {

    private LocalDateTime timeStamp;

    private int status;

    private String error;

    private String message;


}
