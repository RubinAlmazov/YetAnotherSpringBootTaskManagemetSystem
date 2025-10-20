package com.taskManagement.demo.service;

import com.taskManagement.demo.model.TaskEntity;
import com.taskManagement.demo.dao.TaskRepository;
import com.taskManagement.demo.Task;
import com.taskManagement.demo.enums.TaskStatus;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TaskService {
    private final TaskRepository repository;


    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public Task getTaskByID(Long id) {
        TaskEntity entity = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Not found task by id"));
        return entityToTask(entity);
    }

    public List<Task> getAllTasks() {
        List<TaskEntity> listOfEntities = repository.findAll();
        return listOfEntities.stream().map(this::entityToTask).toList();
    }

    public Task createTask(@Valid Task taskToCreate) {
        if (taskToCreate.id() != null) {
            throw new IllegalArgumentException("Id should be empty");
        }
        if (taskToCreate.status() != null) {
            throw new IllegalArgumentException("Status should be empty");
        }

        var newTask = new TaskEntity(
                null,
                taskToCreate.creatorId(),
                taskToCreate.assignedUserId(),
                TaskStatus.CREATED,
                taskToCreate.createDateTime(),
                taskToCreate.deadlineDate(),
                null,
                taskToCreate.taskPriority()

        );
        repository.save(newTask);
        return entityToTask(newTask);
    }

    public Task updateTask(Long id, @Valid Task taskToUpdate) {

        TaskEntity entity = repository.findById(id)
                .orElseThrow( () -> new NoSuchElementException("Not found task by id"));
        var update = new TaskEntity(
                entity.getId(),
                taskToUpdate.creatorId(),
                taskToUpdate.assignedUserId(),
                taskToUpdate.status(),
                taskToUpdate.createDateTime(),
                taskToUpdate.deadlineDate(),
                null,
                taskToUpdate.taskPriority()
        );

        var updatedTask = repository.save(update);
        return entityToTask(updatedTask);
    }

    public void deleteTask(Long id) {
        if (!repository.existsById(id)) {
            throw new NoSuchElementException("Id doesnt exist: "  + id);
        }
        repository.deleteById(id);
    }

    public Task startTask(Long id) {
        if (!repository.existsById(id)) {
            throw new NoSuchElementException("Id doesnt exist: "  + id);
        }
        long count = repository.findAllById(Collections.singleton(id)).stream().
                filter(n -> n.getStatus().equals(TaskStatus.IN_PROGRESS)).count();
        if (count > 4 ) {
            throw new NoSuchElementException("Maximum number of tasks in progress");
        }

        TaskEntity taskToStart = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Not found task by id"));

        taskToStart.setStatus(TaskStatus.IN_PROGRESS);
        taskToStart.setDoneDateTime(null);

        var update = repository.save(taskToStart);
        return entityToTask(update);

    }

    public Task completeTask(Long id) {
        TaskEntity taskToStart = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Not found task by id"));
        var update = new TaskEntity(
                taskToStart.getId(),
                taskToStart.getCreatorId(),
                taskToStart.getAssignedUserId(),
                TaskStatus.IN_PROGRESS,
                taskToStart.getCreateDateTime(),
                taskToStart.getDeadlineDate(),
                LocalDateTime.now(),
                taskToStart.getTaskPriority()
        );
        repository.save(update);
        return entityToTask(update);
    }

    public Task entityToTask(TaskEntity entity) {
        return new Task(
                entity.getId(),
                entity.getCreatorId(),
                entity.getAssignedUserId(),
                entity.getStatus(),
                entity.getCreateDateTime(),
                entity.getDeadlineDate(),
                LocalDateTime.now(),
                entity.getTaskPriority()
        );
    }
}