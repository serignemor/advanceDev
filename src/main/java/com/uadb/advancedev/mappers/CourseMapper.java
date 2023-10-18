package com.uadb.advancedev.mappers;

import com.uadb.advancedev.dto.CourseDto;
import com.uadb.advancedev.entities.Course;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CourseMapper {

    private final ModelMapper modelMapper;

    public CourseDto toDto(Course course) {
        return modelMapper.map(course, CourseDto.class);
    }

    public List<CourseDto> toDto(List<Course> courseList){
        return courseList.stream()
                .map(this::toDto)
                .toList();
    }

    public Course toEntity(CourseDto courseDto) {
        return modelMapper.map(courseDto, Course.class);
    }

    public List<Course> toEntity(List<CourseDto> courseDtoList){
        return courseDtoList.stream()
                .map(this::toEntity)
                .toList();
    }
}
