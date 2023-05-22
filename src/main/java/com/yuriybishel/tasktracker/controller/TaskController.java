package com.yuriybishel.tasktracker.controller;

import com.yuriybishel.tasktracker.model.Task;
import com.yuriybishel.tasktracker.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.findAll();
    }

    @GetMapping("/{id}")
    public String getTask(@PathVariable Long id, Model model) {
        Task task = taskService.findById(id);
        model.addAttribute("task", task);
        return "task";
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskService.save(task);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task task) {
        Task existingTask = taskService.findById(id);
        if (existingTask == null) {
            return null;
        }
        existingTask.setTitle(task.getTitle());
        // Обновляйте все остальные поля здесь...

        taskService.save(existingTask);
        return existingTask;
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteById(id);
    }
}