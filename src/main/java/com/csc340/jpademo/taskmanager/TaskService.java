package com.csc340.jpademo.taskmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    public Object getAllTasks(){
        return taskRepository.findAll();
    }

    public Task getTaskByTaskId(int taskId) {
        return taskRepository.findById(taskId).orElse(null);
    }

    public Object getTaskByGoalId(int goalId) {
        return taskRepository.findByGoalId(goalId);
    }

    public void deleteTaskById(int taskId) {
        taskRepository.deleteById(taskId);
    }

    public void createTask(Task task) {
        taskRepository.save(task);
    }

    public void updateTask(int taskId, Task task) {
        Task taskToUpdate = taskRepository.getReferenceById(taskId);
        taskToUpdate.replaceTask(task);
    }

}
