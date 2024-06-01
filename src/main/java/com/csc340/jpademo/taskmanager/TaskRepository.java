package com.csc340.jpademo.taskmanager;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository
        extends JpaRepository<Task, Integer> {
    @Query(value = "select * from Task where goal_id =?1", nativeQuery = true)
    List<Task> queryByGoalId(int goalId);
}
