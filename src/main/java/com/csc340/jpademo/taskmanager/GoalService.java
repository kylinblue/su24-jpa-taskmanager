package com.csc340.jpademo.taskmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoalService {

    @Autowired
    GoalRepository goalRepository;
    TaskRepository taskRepository;

    public Object getAllGoals(){
        return goalRepository.findAll();
    }

    public Goal getGoalByGoalId(int goalId) {
        return goalRepository.findById(goalId).orElse(null);
    }

    public Object getGoalByUserId(int userId) {
        return goalRepository.findByUserId(userId);
    }

    public void deleteGoalById(int goalId) {
        if(this.taskRepository!=null){
            List<Task> taskToDelete =  taskRepository.queryByGoalId(goalId);
            for (Task t : taskToDelete)
            {
                taskRepository.deleteById(t.getTaskId());
            }
        }
        goalRepository.deleteById(goalId);
    }

    public void createGoal(Goal goal) {
        goalRepository.save(goal);
    }

    /* public void updateGoal(int goalId, Goal goal) {
        Goal goalToUpdate = goalRepository.getReferenceById(goalId);
        goalToUpdate.replaceGoal(goal);
    } */
}
