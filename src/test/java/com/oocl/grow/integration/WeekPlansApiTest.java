package com.oocl.grow.integration;

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

    private void createTestWeekPlan(String week_plan_id) {
        WeekPlan week_plan = WeekPlan.builder().weekPlanId(week_plan_id).build();
        repository.saveAndFlush(week_plan);
    }
}
