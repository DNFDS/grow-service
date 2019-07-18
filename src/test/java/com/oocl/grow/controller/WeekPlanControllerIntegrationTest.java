package com.oocl.grow.controller;

import com.oocl.grow.model.Goal;
import com.oocl.grow.model.WeekPlan;
import com.oocl.grow.service.GoalService;
import com.oocl.grow.service.WeekPlanService;
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
public class WeekPlanControllerIntegrationTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private WeekPlanService service;

    @Test
    public void givenWeekPlans_whenGetWeekPlans_thenReturnJSONArray() throws Exception {
        WeekPlan week_plan_test = WeekPlan.builder().goalId("200001").build();
        week_plan_test.setGoalId("100001");
        List<WeekPlan> weekPlans = asList(week_plan_test);

        given(service.getAllWeekPlansByGoalId(week_plan_test.getGoalId())).willReturn(weekPlans);

        mvc.perform(get("/week_plan/get_all_week_plans").contentType(MediaType.APPLICATION_JSON)
                .param("goalId","100001"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].weekPlanId", is(week_plan_test.getWeekPlanId())));
    }

    @Test
    public void givenWeek_plan_whenGetWeek_planByWeekPlanId_thenReturnJSONArray() throws Exception {
        WeekPlan week_plan_test = WeekPlan.builder().goalId("200001").build();

        given(service.getWeekPlanById(week_plan_test.getGoalId())).willReturn(week_plan_test);

        mvc.perform(get("/week_plan/get_week_plan_detail_by_id").contentType(MediaType.APPLICATION_JSON)
                .param("weekPlanId","200001"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.weekPlanId", is(week_plan_test.getWeekPlanId())));
    }
}
