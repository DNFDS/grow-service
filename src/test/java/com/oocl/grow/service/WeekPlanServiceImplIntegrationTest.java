package com.oocl.grow.service;

import com.oocl.grow.model.WeekPlan;
import com.oocl.grow.repository.WeekPlanRepository;
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
public class WeekPlanServiceImplIntegrationTest {

    @Autowired
    private WeekPlanService weekPlanService;

    @MockBean
    private WeekPlanRepository weekPlanRepository;

    @Before
    public void setUp() {
        WeekPlan weekPlan_test = WeekPlan.builder().weekPlanId("300001").build();
        weekPlan_test.setGoalId("200001");
        Mockito.when(weekPlanRepository.findAllByGoalId(weekPlan_test.getGoalId())).thenReturn(singletonList(weekPlan_test));
        Mockito.when(weekPlanRepository.findByWeekPlanId(weekPlan_test.getWeekPlanId())).thenReturn(weekPlan_test);
    }

    @TestConfiguration
    static class WeekPlanServiceImplTestContextConfiguration {
        @Bean
        public WeekPlanService weekPlanService() {
            return new WeekPlanServiceImpl();
        }
    }

    @Test
    public void should_findByWeekPlanId_given_week_plan_id(){
        String week_plan_id = "300001";
        WeekPlan weekPlan = weekPlanService.getWeekPlanById(week_plan_id);
        assertThat(weekPlan.getWeekPlanId()).isEqualTo(week_plan_id);
    }

    @Test
    public void should_getAllWeekPlans() {
        WeekPlan week_plan_test = WeekPlan.builder().weekPlanId("300001").build();
        week_plan_test.setGoalId("200001");
        List<WeekPlan> week_plans = weekPlanService.getAllWeekPlansByGoalId(week_plan_test.getGoalId());
        assertThat(week_plans.size()).isEqualTo(1);
        assertThat(week_plans.get(0).getWeekPlanId()).isEqualTo(week_plan_test.getWeekPlanId());
    }
}