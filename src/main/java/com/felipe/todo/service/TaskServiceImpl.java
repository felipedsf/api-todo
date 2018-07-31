package com.felipe.todo.service;

import com.felipe.todo.domain.Task;
import com.felipe.todo.domain.TaskList;
import com.felipe.todo.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task saveOrUpdate(Long taskListId, Task task) {
        if (task.getId() != null) {
            Task taskOld = taskRepository.findTaskByTaskListIdAndId(taskListId, task.getId()).orElseThrow(() -> new RuntimeException("Task Not Found"));
            updateTask(task, taskOld);
            task = taskRepository.save(taskOld);
        } else {
            task = taskRepository.save(task);
        }
        return task;
    }

    private void updateTask(Task task, Task taskOld) {
        taskOld.setChecked(task.isChecked());
        taskOld.setDueDate(task.getDueDate());
        taskOld.setDescription(task.getDescription());
    }

    @Override
    public Task findById(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new RuntimeException(("Task Not Found")));
    }

    @Override
    public Task findByTaskListAndId(Long taskListid, Long id) {
        return taskRepository.findTaskByTaskListIdAndId(taskListid, id).orElseThrow(() -> new RuntimeException(("Task Not Found")));
    }

    @Override
    public List<Task> listByTaskList(TaskList taskList) {
        return taskRepository.findAllByTaskList(taskList).orElse(new ArrayList<>());
    }

    @Override
    public List<Task> listByTaskListId(Long taskListId) {
        return taskRepository.findAllByTaskListId(taskListId).orElse(new ArrayList<>());
    }

    @Override
    public List<Task> listAll() {
        return taskRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }
}
