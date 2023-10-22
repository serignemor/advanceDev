package com.uadb.advancedev.mappers;

import com.uadb.advancedev.dto.CourseDto;
import com.uadb.advancedev.dto.StudentDto;
import com.uadb.advancedev.entities.Student;
import com.uadb.advancedev.providers.TestObjectProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class StudentMapperTest {

    @Autowired
    private StudentMapper studentMapper;

    @Test
    void toDto() {

        String name = "Serigne";
        StudentDto studentDto = studentMapper.toDto(
                TestObjectProvider.getStudent(1, name)
        );

        assertEquals(1, studentDto.getId());
        assertEquals(name, studentDto.getName());
        assertEquals(1, studentDto.getCourseSet().size());

        Set<CourseDto> courseDTOSet = studentDto.getCourseSet();
        CourseDto courseDTO = courseDTOSet.stream().toList().get(0);

        assertEquals("JUNIT", courseDTO.getName());
    }


    @Test
    void toEntity() {

        String name = "Serigne";
        StudentDto studentDto = TestObjectProvider.getStudentDto(name);
        Student student = studentMapper.toEntity(studentDto);

        assertEquals(name, student.getName());
        assertEquals(1, student.getCourseSet().size());

        Set<CourseDto> courseDTOSet = studentDto.getCourseSet();
        CourseDto courseDTO = courseDTOSet.stream().toList().get(0);

        assertEquals("JUNIT", courseDTO.getName());
    }

}


