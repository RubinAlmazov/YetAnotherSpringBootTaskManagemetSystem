package com.taskManagement.demo.exceptionhandler;

import java.time.LocalDateTime;

public record ErrorResponseDto (
        String message,
        String detailedMessage,
        LocalDateTime time
) {
}
