package com.felipe.todo.service;

import com.felipe.todo.domain.TaskList;
import com.felipe.todo.repository.TaskListRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskListServiceImpl implements TaskListService {

    private TaskListRepository taskListRepository;

    public TaskListServiceImpl(TaskListRepository taskListRepository) {
        this.taskListRepository = taskListRepository;
    }

    @Override
    public TaskList saveOrUpdate(TaskList taskList) {
        if (taskList.getId() != null) {
            TaskList taskListOld = taskListRepository.findById(taskList.getId()).orElseThrow(() -> new RuntimeException("TaskList Not Found"));
            taskListOld.setName(taskList.getName());
            taskListOld.setTasks(taskList.getTasks());
            taskList = taskListRepository.save(taskListOld);
        } else {
            taskList = taskListRepository.save(taskList);
        }

        return taskList;
    }

    @Override
    public TaskList findById(Long id) {
        return taskListRepository.findById(id).orElseThrow(() -> new RuntimeException("TaskList Not Found"));
    }

    @Override
    public List<TaskList> listAll() {
        return taskListRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        taskListRepository.deleteById(id);
    }
}
