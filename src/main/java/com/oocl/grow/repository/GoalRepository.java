package com.oocl.grow.repository;

import com.oocl.grow.model.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoalRepository extends JpaRepository<Goal, String> {
    Goal findOneByGoalId(String goal_id);
    List<Goal> findAllByUserId(String user_id);
    Boolean deleteGoalByGoalId(String goal_id);
}
