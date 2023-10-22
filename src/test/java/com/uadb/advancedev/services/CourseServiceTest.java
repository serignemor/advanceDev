package com.uadb.advancedev.services;

import com.uadb.advancedev.dto.CourseDto;
import com.uadb.advancedev.entities.Course;
import com.uadb.advancedev.entities.Student;
import com.uadb.advancedev.providers.TestObjectProvider;
import com.uadb.advancedev.repositories.CourseRepository;
import com.uadb.advancedev.repositories.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class CourseServiceTest {

    @Autowired
    private CourseService courseService;

    @MockBean
    private CourseRepository courseRepositoryMock;

    @MockBean
    private StudentRepository studentRepositoryMock;

    @Test
    void getAllCourses() {
        when(courseRepositoryMock.findAll()).thenReturn( TestObjectProvider.getCourseList() );

        List<CourseDto> allCourses = courseService.getAllCourses();
        assertEquals(1, allCourses.size());
        assertEquals("JUNIT", allCourses.get(0).getName());
    }

    @Test
    void getCourseById() {
        when(courseRepositoryMock.findById(1L)).thenReturn(Optional.of(TestObjectProvider.getCourse(1L, "JUNIT")));

        Optional<CourseDto> courseDto = courseService.getCourseById(1L);
        assertTrue(courseDto.isPresent());
        assertEquals("JUNIT", courseDto.get().getName());
    }

    @Test
    void save() {
        when(courseRepositoryMock.save(TestObjectProvider.getCourse(1L, "JUNIT"))).thenReturn(null);

        courseService.save(TestObjectProvider.getCourseDto("JUNIT"));
        verify(courseRepositoryMock).save(any(Course.class));
    }

    @Test
    void update() {
        when(courseRepositoryMock.findById(1L)).thenReturn(Optional.of(TestObjectProvider.getCourse(1L, "JUNIT")));
        when(courseRepositoryMock.save(TestObjectProvider.getCourse(1L, "JUNIT 2"))).thenReturn(null);

        Optional<Course> course = courseService.update(1L, TestObjectProvider.getCourseDto("JUNIT 2"));
        assertTrue(course.isPresent());
        assertEquals("JUNIT 2", course.get().getName());
    }

    @Test
    void delete() {
        when(courseRepositoryMock.findById(1L)).thenReturn(Optional.of(TestObjectProvider.getCourse(1L, "JUNIT")));

        courseService.delete(1L);
        verify(courseRepositoryMock).deleteById(1L);
    }

    @Test
    void addStudentToCourse() {
        when(courseRepositoryMock.findById(1L)).thenReturn(Optional.of(TestObjectProvider.getCourse(1L, "JUNIT")));
        when(courseRepositoryMock.save(TestObjectProvider.getCourse(1L, "JUNIT"))).thenReturn(null);
        when(studentRepositoryMock.findById(1L)).thenReturn(Optional.of(TestObjectProvider.getStudent(1L, "Serigne")));
        when(studentRepositoryMock.save(TestObjectProvider.getStudent(1L, "Serigne"))).thenReturn(null);

        courseService.addStudentToCourse(1L, 1L);
        verify(courseRepositoryMock).save(any(Course.class));
        verify(studentRepositoryMock).save(any(Student.class));
    }

    @Test
    void removeStudentFromCourse() {
        when(courseRepositoryMock.findById(1L)).thenReturn(Optional.of(TestObjectProvider.getCourse(1L, "JUNIT")));
        when(courseRepositoryMock.save(TestObjectProvider.getCourse(1L, "JUNIT"))).thenReturn(null);
        when(studentRepositoryMock.findById(1L)).thenReturn(Optional.of(TestObjectProvider.getStudent(1L, "Serigne")));
        when(studentRepositoryMock.save(TestObjectProvider.getStudent(1L, "Serigne"))).thenReturn(null);

        courseService.removeStudentFromCourse(1L, 1L);
        verify(courseRepositoryMock).save(TestObjectProvider.getCourse(1L, "JUNIT"));
        verify(studentRepositoryMock).save(TestObjectProvider.getStudent(1L, "Serigne"));
    }
}