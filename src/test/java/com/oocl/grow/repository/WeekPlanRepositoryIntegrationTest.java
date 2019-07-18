package com.oocl.grow.repository;

import com.oocl.grow.model.Employee;
import com.oocl.grow.model.WeekPlan;
import com.oocl.grow.service.WeekPlanService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class WeekPlanRepositoryIntegrationTest {
    @Autowired
    private  TestEntityManager entityManager;;

    @Autowired
    private WeekPlanRepository weekPlanRepository;

    @Before
    public void setUp(){
        WeekPlan week_plan_test = WeekPlan.builder().weekPlanId("300001").build();
        week_plan_test.setGoalId("200001");
        entityManager.persist(week_plan_test);
        entityManager.flush();
    }

    @Test
    public void return_WeekPlan_when_findByWeekPlanId() {
        // when
        WeekPlan found = weekPlanRepository.findByWeekPlanId("300001");
        assertThat(found.getWeekPlanId()).isEqualTo("300001");
    }

    @Test
    public void return_WeekPlans_when_getAllWeekPlansByGoalId(){
        List<WeekPlan> found = weekPlanRepository.findAllByGoalId("200001");
        assertThat(found.get(0).getWeekPlanId()).isEqualTo("300001");
    }
}