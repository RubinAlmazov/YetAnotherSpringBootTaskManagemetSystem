package com.taskManagement.demo.model;

import com.taskManagement.demo.enums.TaskPriority;
import com.taskManagement.demo.enums.TaskStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Table( name = "Tasks")
@Entity
public class TaskEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "creatorId", nullable = false)
    private Long creatorId;

    @Column(name = "assignedUserId", nullable = false)
    private Long assignedUserId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private TaskStatus status;

    @Column(name = "createDateTime", nullable = false)
    private LocalDateTime createDateTime;

    @Column(name = "deadlineDate", nullable = false)
    private LocalDateTime deadlineDate;

    @Column(name = "doneDateTime")
    private LocalDateTime doneDateTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "taskPriority", nullable = false)
    private TaskPriority taskPriority;

    public TaskEntity() {}

    public TaskEntity(Long id, Long creatorId, Long assignedUserId, TaskStatus status, LocalDateTime createDateTime, LocalDateTime deadlineDate, LocalDateTime doneDateTime, TaskPriority taskPriority) {
        this.id = id;
        this.creatorId = creatorId;
        this.assignedUserId = assignedUserId;
        this.status = status;
        this.createDateTime = createDateTime;
        this.deadlineDate = deadlineDate;
        this.doneDateTime = doneDateTime;
        this.taskPriority = taskPriority;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Long getAssignedUserId() {
        return assignedUserId;
    }

    public void setAssignedUserId(Long assignedUserId) {
        this.assignedUserId = assignedUserId;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }

    public LocalDateTime getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(LocalDateTime deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public LocalDateTime getDoneDateTime() {
        return doneDateTime;
    }

    public void setDoneDateTime(LocalDateTime doneDateTime) {
        this.doneDateTime = doneDateTime;
    }

    public TaskPriority getTaskPriority() {
        return taskPriority;
    }

    public void setTaskPriority(TaskPriority taskPriority) {
        this.taskPriority = taskPriority;
    }
}
