package com.oocl.grow.controller;

import com.oocl.grow.model.Goal;
import com.oocl.grow.service.GoalService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(GoalController.class)
public class GoalControllerIntegrationTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private GoalService service;

    @Test
    public void givenGoals_whenGetGoals_thenReturnJSONArray() throws Exception {
        Goal goal_test = Goal.builder().goalId("200001").build();
        goal_test.setUserId("100001");
        List<Goal> goals = asList(goal_test);

        given(service.getAllGoalsByUserId(goal_test.getUserId())).willReturn(goals);

        mvc.perform(get("/goal/get_all_goals").contentType(MediaType.APPLICATION_JSON)
                .param("user_id","100001"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].goalId", is(goal_test.getGoalId())));
    }

    @Test
    public void givenGoals_whenGetGoalByGoalId_thenReturnJSONArray() throws Exception {
        Goal goal_test = Goal.builder().goalId("200001").build();

        given(service.getGoalById(goal_test.getGoalId())).willReturn(goal_test);

        mvc.perform(get("/goal/get_goal_detail_by_id").contentType(MediaType.APPLICATION_JSON)
                .param("goal_id","200001"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.goalId", is(goal_test.getGoalId())));
    }
}
