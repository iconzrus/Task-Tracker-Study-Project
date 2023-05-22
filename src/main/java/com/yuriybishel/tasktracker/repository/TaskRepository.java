package com.yuriybishel.tasktracker.repository;

import com.yuriybishel.tasktracker.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
