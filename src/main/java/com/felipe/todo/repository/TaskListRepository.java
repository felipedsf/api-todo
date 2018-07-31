package com.felipe.todo.repository;

import com.felipe.todo.domain.TaskList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskListRepository extends JpaRepository<TaskList, Long> {
}
