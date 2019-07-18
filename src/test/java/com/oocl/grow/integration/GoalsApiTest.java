package com.oocl.grow.integration;

import com.oocl.grow.GrowApplication;
import com.oocl.grow.model.Goal;
import com.oocl.grow.repository.GoalRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = GrowApplication.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class GoalsApiTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private GoalRepository repository;

    @Test
    public void givenGoals_whenGetGoals_thenStatus200()
            throws Exception {

        createTestGoal("100001");

        mvc.perform(get("/goal/get_all_goals")
                .param("goal_id","100001")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].goalId", is("100001")));
    }

    private void createTestGoal(String goal_id) {
        Goal goal = Goal.builder().goalId(goal_id).build();
        repository.saveAndFlush(goal);
    }
}
