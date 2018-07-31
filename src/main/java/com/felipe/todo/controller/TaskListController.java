package com.felipe.todo.controller;

import com.felipe.todo.domain.TaskList;
import com.felipe.todo.service.TaskListService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/taskList")
public class TaskListController {

    private TaskListService taskListService;

    public TaskListController(TaskListService taskListService) {
        this.taskListService = taskListService;
    }

    @GetMapping
    public List<TaskList> getAllTaskList() {
        return taskListService.listAll();
    }

    @GetMapping("/{id}")
    public TaskList getTaskList(@PathVariable Long id) {
        return taskListService.findById(id);
    }

    @PostMapping
    public TaskList createTaskList(@RequestBody TaskList taskList) {
        return taskListService.saveOrUpdate(taskList);
    }

    @PutMapping("/{id}")
    public TaskList updateTaskList(@PathVariable Long id, @RequestBody TaskList taskList) {
        TaskList taskListOld = taskListService.findById(id);
        taskListOld.setName(taskList.getName());
        return taskListService.saveOrUpdate(taskListOld);
    }
}
