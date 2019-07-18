package com.oocl.grow.service;

import com.oocl.grow.model.Employee;
import com.oocl.grow.model.Goal;

import java.util.List;

public interface GoalService {
    Goal getGoalById(String name);
    List<Goal> getAllGoalsByUser_id();
}
