package com.oocl.grow.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
    public void givenUser_id_whenGetGoals_thenStatus200()
            throws Exception {

        createTestGoal("200001");

        mvc.perform(get("/goal/get_all_goals")
                .param("user_id","100001")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].goalId", is("200001")));
    }

    @Test
    public void givenGoal_id_whenGetGoal_thenStatus200()
            throws Exception {

        createTestGoal("200001");

        mvc.perform(get("/goal/get_goal_detail_by_id")
                .param("goal_id","200001")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.goalId", is("200001")));
    }

    @Test
    public void givenGoal_whenCreateGoal_thenStatus200()
            throws Exception {
        Goal goal_test = Goal.builder().goalId("200002").userId("100001").goalTitle("docker mysql").build();
        //设置值
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String requestJson = ow.writeValueAsString(goal_test);
        mvc.perform(post("/goal/save_goal")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.goalId", is("200002")));
    }

    @Test
    public void givenGoal_whenUpdateGoal_thenStatus200()
            throws Exception {
        createTestGoal("200001");
        Goal goal_test = Goal.builder().goalId("200002").userId("100001").goalTitle("docker mysql").build();
        //设置值
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String requestJson = ow.writeValueAsString(goal_test);
        mvc.perform(post("/goal/update_goal")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.goalId", is("200002")));
    }

    @Test
    public void givenGoal_id_whenDeleteGoal_thenStatus200()
            throws Exception {

        createTestGoal("200001");

        mvc.perform(get("/goal/delete_goal_by_id")
                .param("goal_id","200001")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", is(1)));
    }

    private void createTestGoal(String goal_id) {
        Goal goal = Goal.builder().goalId(goal_id).build();
        goal.setUserId("100001");
        repository.saveAndFlush(goal);
    }
}
