package com.oocl.grow.service;

import com.oocl.grow.model.Goal;
import com.oocl.grow.repository.GoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class GoalServiceImpl implements GoalService {
    @Autowired
    private GoalRepository goalRepository;

    @Override
    public Goal getGoalById(String goal_id) {
        return goalRepository.findOneByGoalId(goal_id);
    }

    @Override
    public List<Goal> getAllGoalsByUserId(String user_id) {
        return goalRepository.findAllByUserId(user_id);
    }

    @Override
    public Goal saveGoal(Goal goal){return goalRepository.save(goal);}

    @Override
    public Integer deleteGoalByGoalId(String goal_id){return goalRepository.deleteGoalByGoalId(goal_id);}
}
