package com.oocl.grow.repository;

import com.oocl.grow.model.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoalRepository extends JpaRepository<Goal, String> {
    Goal findOneByGoal_id(String id);
    List<Goal> findAll();
}
