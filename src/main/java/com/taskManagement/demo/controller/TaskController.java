package com.taskManagement.demo.controller;

import com.taskManagement.demo.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.taskManagement.demo.service.TaskService;

import java.util.List;
import java.util.NoSuchElementException;


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
        log.info("getting task by id={}", id);
        try {
            Task task = taskService.getTaskByID(id);
            log.info("task successfully received by id={}", id);
            return ResponseEntity.ok(task);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }

    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        log.info("getting all tasks");
        try {
            List<Task> tasks = taskService.getAllTasks();
            log.info("all tasks successfully received");
            return ResponseEntity.ok(tasks);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }

    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task taskToCreate) {
        log.info("creating new task");
        Task task = taskService.createTask(taskToCreate);
        log.info("successfully created new task");
        return ResponseEntity.ok(task);



    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task taskToUpdate) {
        log.info("updating task by id={}", id);
        Task task = taskService.updateTask(id, taskToUpdate);
        log.info("successfully update task by id={}", id);
        return ResponseEntity.ok(task);


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        log.info("deleting task by id={}", id);
        taskService.deleteTask(id);
        log.info("successfully delete task by id={}", id);
        return ResponseEntity.ok(null);
    }

    @PostMapping("/{id}/start")
    public ResponseEntity<Task> startTask(@PathVariable Long id) {
        log.info("starting task by id={}",id);
        Task task = taskService.startTask(id);
        log.info("successfully started task");
        return ResponseEntity.ok(task);
    }

    @PostMapping("/{id}/complete")
    public ResponseEntity<Task> completeTask(@PathVariable Long id) {
        log.info("completing task by id={}",id);
        Task task = taskService.completeTask(id);
        log.info("successfully completed task");
        return ResponseEntity.ok(task);
    }


}

