package com.oocl.grow.service;

import com.oocl.grow.model.WeekPlan;
import com.oocl.grow.repository.WeekPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class WeekPlanServiceImpl implements WeekPlanService {
    @Autowired
    WeekPlanRepository weekPlanRepository;

    @Override
    public WeekPlan getWeekPlanById(String week_plan_id) {
        return weekPlanRepository.findByWeekPlanId(week_plan_id);
    }

    @Override
    public List<WeekPlan> getAllWeekPlansByGoalId(String goal_id) {
        return weekPlanRepository.findAllByGoalId(goal_id);
    }

    @Override
    public Integer deleteWeekPlanByWeekPlanId(String weekPlanId) {
        return weekPlanRepository.deleteWeekPlanByWeekPlanId(weekPlanId);
    }

    @Override
    public WeekPlan saveWeekPlan(WeekPlan weekPlan) {
        return weekPlanRepository.save(weekPlan);
    }
}
