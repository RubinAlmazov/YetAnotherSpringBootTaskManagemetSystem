package com.taskManagement.demo.db;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<TaskEntity,Long> {

}
