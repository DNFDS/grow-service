package com.oocl.grow.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.oocl.grow.GrowApplication;
import com.oocl.grow.model.WeekPlan;
import com.oocl.grow.repository.WeekPlanRepository;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = GrowApplication.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class WeekPlansApiTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private WeekPlanRepository repository;

    @Test
    public void givenWeekPlans_whenGetWeekPlans_thenStatus200()
            throws Exception {

        createTestWeekPlan("300001");

        mvc.perform(get("/week_plan/get_all_week_plans")
                .param("week_plan_id","300001")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].weekPlanId", is("300001")));
    }

    @Test
    public void givenWeekPlan_id_whenGetWeekPlan_thenStatus200()
            throws Exception {

        createTestWeekPlan("300001");

        mvc.perform(get("/week_plan/get_week_plan_detail_by_id")
                .param("week_plan_id","300001")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.weekPlanId", is("300001")));
    }

    @Test
    public void givenWeekPlanId_when_deleteWeekPlanById_thenStatus200() throws Exception{
        createTestWeekPlan("300001");

        mvc.perform(get("/week_plan/delete_week_plan_by_id")
                .param("week_plan_id","300001")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$",is(1)));

    }

@Test
public void givenGoal_whenCreateGoal_thenStatus200()
        throws Exception {
    WeekPlan week_plan_test = WeekPlan.builder().weekPlanId("300002").goalId("200001").weekPlanTitle("bulid success").build();
    //设置值
    ObjectMapper mapper = new ObjectMapper();
    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
    java.lang.String requestJson = ow.writeValueAsString(week_plan_test);
    mvc.perform(post("/week_plan/save_week_plan")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestJson))
            .andExpect(status().isOk())
            .andExpect(content()
                    .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.weekPlanId", is("300002")));
}

    @Test
    public void givenGoal_whenUpdateGoal_thenStatus200()
            throws Exception {
        createTestWeekPlan("300001");
        WeekPlan week_plan_test = WeekPlan.builder().weekPlanId("300002").goalId("200001").weekPlanTitle("bulid success").build();
        //设置值
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String requestJson = ow.writeValueAsString(week_plan_test);
        mvc.perform(post("/week_plan/update_week_plan")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.weekPlanId", is("300002")));
    }

    private void createTestWeekPlan(String week_plan_id) {
        WeekPlan week_plan = WeekPlan.builder().weekPlanId(week_plan_id).build();
        repository.saveAndFlush(week_plan);
    }
}
