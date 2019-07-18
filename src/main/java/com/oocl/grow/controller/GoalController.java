package com.oocl.grow.controller;

import com.oocl.grow.model.Employee;
import com.oocl.grow.model.Goal;
import com.oocl.grow.service.EmployeeService;
import com.oocl.grow.service.GoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/goal")
public class GoalController {
    @Autowired
    private GoalService goalService;

    @GetMapping("/get_all_goals")
    public List<Goal> getAllGoals(HttpServletRequest request) {
        String user_id = request.getParameter("user_id");
        return goalService.getAllGoalsByUser_id(user_id);
    }

    @GetMapping("/get_goal_detail_by_id")
    public Goal getGoalDetailById(HttpServletRequest request) {
        String goal_id = request.getParameter("goal_id");
        return goalService.getGoalById(goal_id);
    }
}
