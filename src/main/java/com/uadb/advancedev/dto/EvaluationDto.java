package com.uadb.advancedev.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link com.uadb.advancedev.entities.Evaluation}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EvaluationDto implements Serializable {
    private long id;
    private int rating;
    private ProfessorDto professor;
    private CourseDto course;
    private StudentDto student;


}