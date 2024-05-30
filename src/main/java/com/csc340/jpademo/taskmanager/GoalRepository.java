package com.csc340.jpademo.taskmanager;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoalRepository
        extends JpaRepository<Goal, Integer>  {
    @Query(value = "select g from Goal g where g.getUserId = ?1", nativeQuery = true)
    List<Goal> findByUserId(int userId);
}
