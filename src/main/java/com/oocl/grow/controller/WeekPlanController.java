package com.oocl.grow.controller;

import com.oocl.grow.model.WeekPlan;
import com.oocl.grow.service.WeekPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/week_plan")
public class WeekPlanController {
    @Autowired
    private WeekPlanService weekPlanService;

    @GetMapping("/get_all_week_plans")
    public List<WeekPlan> getAllWeekPlansByGoalId(HttpServletRequest request) {
        String goal_id = request.getParameter("goal_id");
        return weekPlanService.getAllWeekPlansByGoalId(goal_id);
    }

    @GetMapping("/get_week_plan_detail_by_id")
    public WeekPlan getWeekPlanDetailById(HttpServletRequest request) {
        String week_plan_id = request.getParameter("week_plan_id");
        return weekPlanService.getWeekPlanById(week_plan_id);
    }

    @GetMapping ("/delete_week_plan_by_id")
    public Integer deleteWeekPlanById(HttpServletRequest request){
        String week_plan_id = request.getParameter("week_plan_id");
        return weekPlanService.deleteWeekPlanByWeekPlanId(week_plan_id);
    }

    @PostMapping("/save_week_plan")
    public WeekPlan saveWeekPlan(){
        WeekPlan weekPlan = WeekPlan.builder().weekPlanId("300002").goalId("200001").weekPlanTitle("bulid success").build();
        return weekPlanService.saveWeekPlan(weekPlan);
    }

    @PostMapping("/update_week_plan")
    public WeekPlan updateWeekPlan(@RequestBody WeekPlan weekPlan){
        return weekPlanService.saveWeekPlan(weekPlan);
    }
}
