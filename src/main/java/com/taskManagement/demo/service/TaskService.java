package com.taskManagement.demo.service;

import com.taskManagement.demo.Task;
import com.taskManagement.demo.TaskPriority;
import com.taskManagement.demo.TaskStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class TaskService {
    private final HashMap<Long, Task> store = new HashMap<>(Map.of(
            1L, new Task(
                    1L,
                    1L,
                    1L,
                    TaskStatus.IN_PROGRESS,
                    LocalDateTime.now(),
                    LocalDateTime.now().plusDays(5),
                    TaskPriority.MEDIUM

            ),
            2L, new Task(
                    2L,
                    2L,
                    2L,
                    TaskStatus.CREATED,
                    LocalDateTime.now().plusDays(6),
                    LocalDateTime.now().plusDays(10),
                    TaskPriority.LOW

            ),
            3L, new Task(
                    3L,
                    3L,
                    3L,
                    TaskStatus.DONE,
                    LocalDateTime.now().plusDays(11),
                    LocalDateTime.now().plusDays(20),
                    TaskPriority.HIGH

            )
    ));


    public Task getTaskByID(Long id) {
        if (!store.containsKey(id)) {
            throw new RuntimeException("Такого айди не существует:" + id);
        }
        return store.get(id);
    }

    public List<Task> getAllTasks() {
        if (store.isEmpty()) {
            throw new NoSuchElementException("Нету ни одной задачи");
        }
        return store.values().stream().toList();
    }
}