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
    @Autowired TaskService taskService;

    @GetMapping("/creator")
    public String creatorMapping(){
        return "goal-creator";
    }

    @GetMapping("/lookup")
    public String getAllGoals(Model model) {
        model.addAttribute("goalList", goalService.getAllGoals());
        return "goal-by-userid";
    }

    @PostMapping("/userid")
    public String getGoalByUserId(@RequestParam int userId, Model model) {
        model.addAttribute("goalList", goalService.getGoalByUserId(userId));
        return "goal-by-userid-result";
    }

    @GetMapping("/goalid/{goalId}")
    public String getGoalByGoalId(@PathVariable int goalId, Model model) {
        model.addAttribute("goal", goalService.getGoalByGoalId(goalId));
        model.addAttribute("taskList", taskService.getTaskByGoalId(goalId));
        return "goal-detail";
    }

    @PostMapping("/write")
    public String writeGoal(Goal goal) {
        goalService.createGoal(goal);
        return "redirect:/taskmanager/goals/goalid/" + goal.getGoalId();
    }

    @GetMapping("/update/{goalId}")
    public String updateGoalForm(@PathVariable int goalId, Model model) {
        model.addAttribute("goal", goalService.getGoalByGoalId(goalId));
        return "goal-update";
    }

    @GetMapping("/delete/{goalId}")
    public String deleteGoalById(@PathVariable int goalId) {
        goalService.deleteGoalById(goalId);
        return "redirect:/taskmanager/goals/lookup";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // Date - yyyy - MM - dd
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
}
