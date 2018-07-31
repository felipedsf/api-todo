package com.felipe.todo.controller;

import com.felipe.todo.domain.Task;
import com.felipe.todo.domain.TaskList;
import com.felipe.todo.service.TaskListService;
import com.felipe.todo.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/{taskListId}/task")
public class TaskController {

    private TaskService taskService;

    private TaskListService taskListService;


    public TaskController(TaskService taskService, TaskListService taskListService) {
        this.taskListService = taskListService;
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getAllTasks(@PathVariable Long taskListId) {
        return taskService.listByTaskListId(taskListId);
    }

    @GetMapping("/{id}")
    public Task getAllTasks(@PathVariable Long taskListId, @PathVariable Long id) {
        return taskService.findByTaskListAndId(taskListId, id);
    }

    @PostMapping
    public Task createTask(@PathVariable Long taskListId, @RequestBody Task task) {
        TaskList taskList = taskListService.findById(taskListId);
        task.setTaskList(taskList);
        return taskService.saveOrUpdate(taskListId, task);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long taskListId, @PathVariable Long id, @RequestBody Task task) {
        task.setId(id);
        return taskService.saveOrUpdate(taskListId, task);
    }


}
