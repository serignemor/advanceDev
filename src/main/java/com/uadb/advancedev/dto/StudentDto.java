package com.uadb.advancedev.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentDto {
    private long id;
    private String name;
    private Set<CourseDto> courseSet;
}