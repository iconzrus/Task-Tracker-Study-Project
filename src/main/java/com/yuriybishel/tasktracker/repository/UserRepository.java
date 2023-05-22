package com.yuriybishel.tasktracker.repository;

import com.yuriybishel.tasktracker.model.Task;
import com.yuriybishel.tasktracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
