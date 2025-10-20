package com.taskManagement.demo.DAO;

import com.taskManagement.demo.model.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<TaskEntity,Long> {

}
