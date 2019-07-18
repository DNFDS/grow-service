package com.oocl.grow.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Tables;

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
public class Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Size(max = 8)
    private String goal_id;

    @Size(max = 40)
    private String goal_title;

    @Size(max = 100)
    private String goal_description;

    private Date goal_start_data;

    private Date goal_deadline_data;

    private Date goal_completed_data;

    private int state;
}
