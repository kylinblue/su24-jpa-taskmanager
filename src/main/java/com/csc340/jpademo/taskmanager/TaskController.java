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
    @Autowired GoalService goalService;

    /*@GetMapping("/all")
    public String getAllTasks(Model model) {
        model.addAttribute("taskList", taskService.getAllTasks());
        return "task-list";
    }*/

    @GetMapping("/{goalId}")
    public String getTaskByGoalId(@PathVariable int goalId, Model model) {
        model.addAttribute("goal", goalService.getGoalByGoalId(goalId));
        model.addAttribute("taskList", taskService.getTaskByGoalId(goalId));
        return "goal-detail";
    }

    @GetMapping("/taskid/{taskId}")
    public String getTaskByTaskId(@PathVariable int taskId, Model model) {
        model.addAttribute("task", taskService.getTaskByTaskId(taskId));
        return "task-detail";
    }

    @PostMapping("/write")
    public String addNewTask(Task task) {
        taskService.createTask(task);
        int goalId = task.getGoalId();
        return "redirect:/taskmanager/goals/goalid/" + goalId;
    }

    @GetMapping("/delete/{taskId}")
    public String deleteTaskById(@PathVariable int taskId) {
        taskService.deleteTaskById(taskId);
        return "redirect:/taskmanager/goals/lookup";
    }
}
