package com.felipe.todo.service;

import com.felipe.todo.domain.TaskList;

import java.util.List;

public interface TaskListService {

    TaskList saveOrUpdate(TaskList taskList);

    TaskList findById(Long id);

    List<TaskList> listAll();

    void deleteById(Long id);
}
