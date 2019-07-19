package com.oocl.grow.service;

import com.oocl.grow.model.Goal;
import com.oocl.grow.model.WeekPlan;

import java.util.List;

public interface WeekPlanService {
    WeekPlan getWeekPlanById(String week_plan_id);
    List<WeekPlan> getAllWeekPlansByGoalId(String goal_id);
    Integer deleteWeekPlanByWeekPlanId(String weekPlanId);
    WeekPlan saveWeekPlan(WeekPlan weekPlan);
}
