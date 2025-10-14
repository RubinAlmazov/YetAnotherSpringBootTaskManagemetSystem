package com.taskManagement.demo.controller;

import com.taskManagement.demo.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public Task getTask(@PathVariable Long id) {
        log.info("get task by id={}", id);
        return taskService.getTaskByID(id);
    }

    @GetMapping
    public List<Task> getAllTask() {
        log.info("get all tasks");
        return taskService.getAllTasks();
    }
}
