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
    private String week_plan_id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Size(max = 8)
    private String goal_id;

    @Size(max = 40)
    private String week_plan_title;

    @Size(max = 100)
    private String week_plan_description;

    private int week_plan_number;

    private int week_plan_state;
}
