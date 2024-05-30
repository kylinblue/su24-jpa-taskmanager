package com.csc340.jpademo.taskmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/taskmanager/tasks")

public class TaskController {
    @Autowired TaskService taskService;

    @GetMapping("/all")
    public String getAllTasks(Model model) {
        model.addAttribute("taskList", taskService.getAllTasks());
        return "task-list";
    }

    @GetMapping("/{goalId}")
    public String getTaskByGoalId(@PathVariable int goalId, Model model) {
        model.addAttribute("task", taskService.getTaskByGoalId(goalId));
        return "task-by-goalid";
    }

    @PostMapping("/create")
    public String addNewTask(Task task) {
        taskService.createTask(task);
        return "redirect:/taskmanager/tasks/all";
    }

    @PostMapping("/update")
    public String updateTask(Task task) {
        taskService.updateTask(task.getTaskId(), task);
        return "redirect:/taskmanager/tasks/all";
    }

    @GetMapping("/update/{taskId}")
    public String updateTaskForm(@PathVariable int taskId, Model model) {
        model.addAttribute("task", taskService.getTaskByTaskId(taskId));
        return "task-update";
    }

    @GetMapping("/delete/taskId")
    public String deleteTaskById(@PathVariable int taskId) {
        taskService.deleteTaskById(taskId);
        return "redirect:/taskmanager/tasks/all";
    }
}
