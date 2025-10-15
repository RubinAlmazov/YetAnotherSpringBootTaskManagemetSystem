package com.taskManagement.demo.service;

import com.taskManagement.demo.Task;
import com.taskManagement.demo.TaskStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TaskService {
    private final Map<Long, Task> store;

    private final AtomicLong counter;

    public TaskService() {
        this.store = new HashMap<>();
        this.counter = new AtomicLong();
    }

    public Task getTaskByID(Long id) {
        if (!store.containsKey(id)) {
            throw new RuntimeException("Id doesnt exist: " + id);
        }
        return store.get(id);
    }

    public List<Task> getAllTasks() {
        if (store.isEmpty()) {
            throw new NoSuchElementException("No one task exist");
        }
        return store.values().stream().toList();
    }

    public Task createTask(Task taskToCreate) {
        if (taskToCreate.id() != null) {
            throw new IllegalArgumentException("Id should be empty");
        }
        if (taskToCreate.status() != null) {
            throw new IllegalArgumentException("Status should be empty");
        }

        var newTask = new Task(
                counter.incrementAndGet(),
                taskToCreate.creatorId(),
                taskToCreate.assignedUserId(),
                TaskStatus.CREATED,
                taskToCreate.createDateTime(),
                taskToCreate.deadlineDate(),
                taskToCreate.taskPriority()

        );
        store.put(newTask.id(), newTask);
        return newTask;
    }

    public Task updateTask(Long id, Task taskToUpdate) {
        if (!store.containsKey(id)) {
            throw new NoSuchElementException("Id not found: " + id);
        }
        if (store.get(id).status() == TaskStatus.DONE) {
            throw new IllegalArgumentException("Update doesnt available, task finished");
        }
        if (taskToUpdate.status() != null) {
            throw new IllegalArgumentException("Status should be empty");
        }

        var update = new Task(
                id,
                taskToUpdate.creatorId(),
                taskToUpdate.assignedUserId(),
                TaskStatus.IN_PROGRESS,
                taskToUpdate.createDateTime(),
                taskToUpdate.deadlineDate(),
                taskToUpdate.taskPriority()
        );

        store.put(id, update );
        return update;
    }

    public void deleteTask(Long id) {
        if (!store.containsKey(id)) {
            throw new NoSuchElementException("Id doesnt exist: "  + id);
        }
        store.remove(id);
    }
}