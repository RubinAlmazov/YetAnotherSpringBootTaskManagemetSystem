package com.taskManagement.demo.service;

import com.taskManagement.demo.api.TaskMapper;
import com.taskManagement.demo.db.TaskEntity;
import com.taskManagement.demo.db.TaskRepository;
import com.taskManagement.demo.api.Task;
import com.taskManagement.demo.api.TaskStatus;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TaskService {
    private final TaskRepository repository;
    private final TaskMapper mapper;

    public TaskService(TaskRepository repository, TaskMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Task getTaskByID(Long id) {
        TaskEntity entity = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Not found task by id"));
        return mapper.toTask(entity);
    }

    public List<Task> getAllTasks() {
        List<TaskEntity> listOfEntities = repository.findAll();
        return listOfEntities.stream().map(mapper::toTask).toList();
    }

    public Task createTask(@Valid Task taskToCreate) {
        if (taskToCreate.id() != null) {
            throw new IllegalArgumentException("Id should be empty");
        }
        if (taskToCreate.status() != null) {
            throw new IllegalArgumentException("Status should be empty");
        }

        var newTask = mapper.toEntity(taskToCreate);
        newTask.setId(null);
        newTask.setStatus(TaskStatus.CREATED);
        newTask.setDoneDateTime(null);
        repository.save(newTask);
        return mapper.toTask(newTask);
    }

    public Task updateTask(Long id, @Valid Task taskToUpdate) {

        TaskEntity entity = repository.findById(id)
                .orElseThrow( () -> new NoSuchElementException("Not found task by id"));

        var update = mapper.toEntity(taskToUpdate);
        update.setId(entity.getId());
        update.setDoneDateTime(null);
        var updatedTask = repository.save(update);
        return mapper.toTask(updatedTask);
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
        return mapper.toTask(update);

    }

    public Task completeTask(Long id) {
        TaskEntity taskToStart = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Not found task by id"));

        taskToStart.setStatus(TaskStatus.DONE);
        taskToStart.setDoneDateTime(LocalDateTime.now());
        repository.save(taskToStart);
        return mapper.toTask(taskToStart);
    }
}