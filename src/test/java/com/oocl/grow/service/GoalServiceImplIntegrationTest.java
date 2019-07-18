package com.oocl.grow.service;

import com.oocl.grow.model.Goal;
import com.oocl.grow.repository.GoalRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
public class GoalServiceImplIntegrationTest {
    @Autowired
    private GoalService goalService;

    @MockBean
    private GoalRepository goalRepository;

    @Before
    public void setUp() {
        Goal goal_text = Goal.builder().goalId("200001").build();
        goal_text.setUserId("1000001");
        Mockito.when(goalRepository.findAllByUserId(goal_text.getUserId())).thenReturn(singletonList(goal_text));
        Mockito.when(goalRepository.findOneByGoalId(goal_text.getGoalId())).thenReturn(goal_text);
    }

    @TestConfiguration
    static class GoalServiceImplTestContextConfiguration {
        @Bean
        public GoalService goalService() {
            return new GoalServiceImpl();
        }
    }

    @Test
    public void should_findByGoalId_given_goal_id() {
        String goal_id = "200001";
        Goal goal = goalService.getGoalById(goal_id);
        assertThat(goal.getGoalId()).isEqualTo(goal_id);
    }

    @Test
    public void should_getAllEmployees() {
        Goal goal_text = Goal.builder().goalId("200001").build();
        goal_text.setUserId("1000001");
        List<Goal> goals = goalService.getAllGoalsByUserId(goal_text.getUserId());
        assertThat(goals.size()).isEqualTo(1);
        assertThat(goals.get(0).getGoalId()).isEqualTo(goal_text.getGoalId());
    }
}
