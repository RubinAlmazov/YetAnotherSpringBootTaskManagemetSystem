package com.taskManagement.demo;

import java.time.LocalDateTime;

public record Task (
        Long id,

        Long craterId,

        Long assignedUserId,

        TaskStatus status,

        LocalDateTime craeteDateTime,

        LocalDateTime deadlineDate,

        TaskPriority taskPriority
) {



}
