package com.oocl.grow.repository;

import com.oocl.grow.model.WeekPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeekPlanRepository  extends JpaRepository<WeekPlan, String> {
    List<WeekPlan> findAllByGoalId(String goal_id);
    WeekPlan findByWeekPlanId(String week_id);
    Integer deleteWeekPlanByWeekPlanId(String week_plan_id);

}
