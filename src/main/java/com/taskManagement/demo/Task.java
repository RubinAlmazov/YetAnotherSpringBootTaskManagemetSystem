package com.taskManagement.demo;

import com.taskManagement.demo.enums.TaskPriority;
import com.taskManagement.demo.enums.TaskStatus;

import java.time.LocalDateTime;

public record Task (
        Long id,

        Long creatorId,

        Long assignedUserId,

        TaskStatus status,

        LocalDateTime createDateTime,

        LocalDateTime deadlineDate,

        LocalDateTime doneDateTime,

        TaskPriority taskPriority
) {



}
