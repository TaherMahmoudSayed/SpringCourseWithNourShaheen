package com.book.booksproject.error;

import lombok.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponse {
    private Boolean success;
    private String errorMessage;
    private LocalDateTime dateTime;
    private List<String> details;
}
