package com.taskManagement.demo;

import com.taskManagement.demo.enums.TaskPriority;
import com.taskManagement.demo.enums.TaskStatus;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

import java.time.LocalDateTime;

public record Task (
        @Null
        Long id,

        @NotNull
        Long creatorId,

        @NotNull
        Long assignedUserId,

        @NotNull
        TaskStatus status,

        @FutureOrPresent
        @NotNull
        LocalDateTime createDateTime,

        @FutureOrPresent
        @NotNull
        LocalDateTime deadlineDate,

        LocalDateTime doneDateTime,

        @NotNull
        TaskPriority taskPriority
) {



}
