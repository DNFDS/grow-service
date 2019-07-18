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
    private String goalId;

    @Size(max = 8)
    private String userId;

    @Size(max = 40)
    private String goalTitle;

    @Size(max = 100)
    private String goalDescription;

    private Date goalStartData;

    private Date goalDeadlineData;

    private Date goalCompletedData;

    private int goalState;
}
