package com.uadb.advancedev.services;

import com.uadb.advancedev.dto.CourseDto;
import com.uadb.advancedev.dto.StudentDto;
import com.uadb.advancedev.entities.Student;
import com.uadb.advancedev.mappers.CourseMapper;
import com.uadb.advancedev.repositories.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public void save(CourseDto courseDto) {
        courseRepository.save(courseMapper.toEntity(courseDto));
    }


    public List<CourseDto> getAllCourse() {
        return courseMapper.toDto(courseRepository.findAll());
    }



}
