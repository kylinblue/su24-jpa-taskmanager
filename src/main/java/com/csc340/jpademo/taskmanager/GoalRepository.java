package com.csc340.jpademo.taskmanager;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoalRepository
        extends JpaRepository<Goal, Integer>  {
    @Query(value = "select * from Goal where user_id = ?1", nativeQuery = true)
    List<Goal> findByUserId(int userId);
}
