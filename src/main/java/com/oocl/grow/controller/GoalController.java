package com.oocl.grow.controller;

import com.oocl.grow.model.Employee;
import com.oocl.grow.model.Goal;
import com.oocl.grow.service.EmployeeService;
import com.oocl.grow.service.GoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/goal")
public class GoalController {
    @Autowired
    private GoalService goalService;

    @GetMapping("/get_all_goals")
    public List<Goal> getAllGoalsByUserId(HttpServletRequest request) {
        String user_id = request.getParameter("user_id");
        return goalService.getAllGoalsByUserId(user_id);
    }

    @GetMapping("/get_goal_detail_by_id")
    public Goal getGoalById(HttpServletRequest request) {
        String goal_id = request.getParameter("goal_id");
        return goalService.getGoalById(goal_id);
    }

    @PostMapping("/save_goal")
    public Goal saveGoal(@RequestBody Goal goal) {
        //Goal docker_mysql = Goal.builder().goalId("200002").userId("100001").goalTitle("docker mysql").build();
        return goalService.saveGoal(goal);
    }

    @GetMapping("/delete_goal_by_id")
    public Integer deleteGoalByGoalId(HttpServletRequest request) {
        String goal_id = request.getParameter("goal_id");
        return goalService.deleteGoalByGoalId(goal_id);
    }
    @PostMapping("/update_goal")
    public Goal deleteGoalByGoalId(@RequestBody Goal goal) {
        return goalService.saveGoal(goal);
    }
}
