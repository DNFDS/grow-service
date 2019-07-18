package com.oocl.grow.service;

import com.oocl.grow.model.Goal;

import java.util.List;

public interface GoalService {
    Goal getGoalById(String goal_id);
    List<Goal> getAllGoalsByUserId(String user_id);
    Goal saveGoal(Goal goal);
}
