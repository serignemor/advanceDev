package com.uadb.advancedev.dto;

import com.uadb.advancedev.entities.enums.RateValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link com.uadb.advancedev.entities.Rate}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RateDto implements Serializable {
    private long id;
    private RateValue rating;
    private CourseDto course;
    private ProfessorDto professor;
    private StudentDto student;
}