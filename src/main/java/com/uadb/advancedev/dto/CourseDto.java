package com.uadb.advancedev.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link com.uadb.advancedev.entities.Course}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDto implements Serializable {
    private long id;
    private String name;
    private ProfessorDto professor;
}