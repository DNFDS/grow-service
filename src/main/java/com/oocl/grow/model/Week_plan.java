package com.oocl.grow.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Week_plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Size(max = 8)
    private String weekPlanId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Size(max = 8)
    private String goalId;

    @Size(max = 40)
    private String weekPlanTitle;

    @Size(max = 100)
    private String weekPlanDescription;

    private int weekPlanNumber;

    private int weekPlanState;
}
