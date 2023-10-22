package com.uadb.advancedev.mappers;

import com.uadb.advancedev.dto.CourseDto;
import com.uadb.advancedev.entities.Course;
import com.uadb.advancedev.providers.TestObjectProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CourseMapperTest {

    @Autowired
    private CourseMapper courseMapper;

    @Test
    void toDto() {
        Course course = TestObjectProvider.getCourse(1, "JUNIT");
        CourseDto courseDto = courseMapper.toDto(course);

        assertEquals(1, courseDto.getId());
        assertEquals("JUNIT", courseDto.getName());

    }

    @Test
    void toEntity() {
        CourseDto courseDto = TestObjectProvider.getCourseDto("JUNIT");
        Course course = courseMapper.toEntity(courseDto);

        assertEquals("JUNIT", course.getName());
    }
}