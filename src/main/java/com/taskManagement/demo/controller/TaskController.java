package com.taskManagement.demo.controller;

import com.taskManagement.demo.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.taskManagement.demo.service.TaskService;

import java.util.List;


@RestController
@RequestMapping("/tasks")
public class TaskController {

    private static final Logger log = LoggerFactory.getLogger(TaskController.class);

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTask(@PathVariable Long id) {
        log.info("get task by id={}", id);
        return ResponseEntity.status(HttpStatus.OK).body(taskService.getTaskByID(id));
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        log.info("get all tasks");
        return ResponseEntity.status(HttpStatus.OK).body(taskService.getAllTasks());
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task taskToCreate) {
        log.info("crete new task");
        return ResponseEntity.status(HttpStatus.OK).body(taskService.createTask(taskToCreate));

    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task taskToUpdate) {
        log.info("update task id={}", id);
        return ResponseEntity.status(HttpStatus.OK).body(taskService.updateTask(id, taskToUpdate));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        log.info("delete task id={}", id);
        taskService.deleteTask(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}

