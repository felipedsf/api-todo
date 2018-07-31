package com.felipe.todo.service;

import com.felipe.todo.domain.Task;
import com.felipe.todo.domain.TaskList;

import java.util.List;

public interface TaskService {

    Task saveOrUpdate(Long taskListId, Task task);

    Task findById(Long id);

    Task findByTaskListAndId(Long taskListid, Long id);

    List<Task> listByTaskList(TaskList taskList);

    List<Task> listByTaskListId(Long taskListId);

    List<Task> listAll();

    void deleteById(Long id);


}
