package com.oocl.grow.repository;

import com.oocl.grow.model.Employee;
import com.oocl.grow.model.Goal;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class GoalRepositoryIntegrationTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private GoalRepository goalRepository;

    @Before
    public void setUp() {
        Goal goal_test = Goal.builder().goalId("200001").build();
        goal_test.setUserId("100001");
        entityManager.persist(goal_test);
        entityManager.flush();
    }

    @Test
    public void return_Goal_when_findOneByGoalId() {
        // when
        Goal found = goalRepository.findOneByGoalId("200001");
        // then
        assertThat(found.getGoalId()).isEqualTo("200001");
    }

    @Test
    public void return_Goals_when_getAllGoalsByUserId() {
        // when
        List<Goal> found = goalRepository.findAllByUserId("100001");
        // then
        assertThat(found.get(0).getGoalId()).isEqualTo("200001");
    }
}