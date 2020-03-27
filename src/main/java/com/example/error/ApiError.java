package com.example.error;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
public class ApiError {

    private HttpStatus status;

    private String message;

    private LocalDateTime timestamp;

    private List<ApiSubError> subErrors;

}
