package com.felipe.todo.repository;

import com.felipe.todo.domain.Task;
import com.felipe.todo.domain.TaskList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {

    Optional<List<Task>> findAllByTaskList(TaskList taskList);

    Optional<List<Task>> findAllByTaskListId(Long taskList);

    Optional<Task> findTaskByTaskListIdAndId(Long taskList, Long id);

}
