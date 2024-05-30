package com.csc340.jpademo.taskmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import java.util.Date;

import java.text.SimpleDateFormat;

@Controller
@RequestMapping("/taskmanager/goals")

public class GoalController {
    @Autowired GoalService goalService;

    @GetMapping("/all")
    public String getAllGoals(Model model) {
        model.addAttribute("goalList", goalService.getAllGoals());
        return "goal-list";
    }

    @GetMapping("/{userId}")
    public String getGoalByUserId(@PathVariable int userId, Model model) {
        model.addAttribute("goal", goalService.getGoalByUserId(userId));
        return "goal-by-userid";
    }

    @PostMapping("/create")
    public String addNewGoal(Goal goal) {
        goalService.createGoal(goal);
        return "redirect:/taskmanager/goals/all";
    }

    @PostMapping("/update")
    public String updateGoal(Goal goal) {
        goalService.updateGoal(goal.getGoalId(), goal);
        return "redirect:/taskmanager/goals/all";
    }

    @GetMapping("/update/{goalId}")
    public String updateGoalForm(@PathVariable int goalId, Model model) {
        model.addAttribute("goal", goalService.getGoalByGoalId(goalId));
        return "goal-update";
    }

    @GetMapping("/delete/{goalId}")
    public String deleteGoalById(@PathVariable int goalId) {
        goalService.deleteGoalById(goalId);
        return "redirect:/taskmanager/goals/all";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // Date - yyyy - MM - dd
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
}
