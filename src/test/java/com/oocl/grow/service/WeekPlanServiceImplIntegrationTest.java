package com.oocl.grow.service;

import com.oocl.grow.model.Goal;
import com.oocl.grow.model.WeekPlan;
import com.oocl.grow.repository.GoalRepository;
import com.oocl.grow.repository.WeekPlanRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
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
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
class WeekPlanServiceImplIntegrationTest {

    @Autowired
    private WeekPlanService weekPlanService;

    @MockBean
    private WeekPlanRepository weekPlanRepository;

    @Before
    public void setUp() {
        WeekPlan weekPlan_test = WeekPlan.builder().goalId("200001").build();
        weekPlan_test.setGoalId("1000001");
        Mockito.when(weekPlanRepository.findAllByGoalId(weekPlan_test.getGoalId())).thenReturn(singletonList(weekPlan_test));
        Mockito.when(weekPlanRepository.findByWeekPlanId(weekPlan_test.getGoalId())).thenReturn(weekPlan_test);
    }

    @TestConfiguration
    static class WeekPlanServiceImplTestContextConfiguration {
        @Bean
        public WeekPlanService goalService() {
            return new WeekPlanServiceImpl();
        }
    }

    @org.junit.Test
    public void should_findByWeekPlanId_given_week_plan_id(){
        String week_plan_id = "200001";
        WeekPlan weekPlan = weekPlanService.getWeekPlanById(week_plan_id);
        assertThat(weekPlan.getWeekPlanId()).isEqualTo(week_plan_id);
    }

    @org.junit.Test
    public void should_getAllWeekPlans() {
        WeekPlan week_plan_test = WeekPlan.builder().weekPlanId("200001").build();
        week_plan_test.setGoalId("1000001");
        List<WeekPlan> week_plans = weekPlanService.getAllWeekPlansByGoalId(week_plan_test.getGoalId());
        assertThat(week_plans.size()).isEqualTo(1);
        assertThat(week_plans.get(0).getWeekPlanId()).isEqualTo(week_plan_test.getWeekPlanId());
    }
}