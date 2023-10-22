package com.uadb.advancedev.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uadb.advancedev.entities.Course;
import com.uadb.advancedev.entities.Student;
import com.uadb.advancedev.providers.TestObjectProvider;
import com.uadb.advancedev.repositories.CourseRepository;
import com.uadb.advancedev.repositories.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CourseResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CourseRepository courseRepositoryMock;

    @MockBean
    private StudentRepository studentRepositoryMock;

    @Test
    void getAllCourses() throws Exception {
        mockMvc.perform(get("/courses")
        ).andExpect(status().isOk());
    }

    @Test
    void getCourseById() throws Exception {
        when(courseRepositoryMock.findById(1L)).thenReturn(Optional.of(TestObjectProvider.getCourse(1L, "JUNIT")));

        mockMvc.perform(get("/courses/1")
        ).andExpect(status().isOk());
    }

    @Test
    void save() throws Exception {
        when(courseRepositoryMock.save(any(Course.class))).thenReturn(null);

        mockMvc.perform(post("/courses")
                .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                .content(
                        objectMapper.writeValueAsString(
                                TestObjectProvider.getCourse(1L, "JUNIT")
                        )
                )
        ).andExpect(status().isCreated());
    }

    @Test
    void update() throws Exception {
        when(courseRepositoryMock.findById(1L)).thenReturn(Optional.of(TestObjectProvider.getCourse(1L, "JUNIT")));
        when(courseRepositoryMock.save(any(Course.class))).thenReturn(null);

        mockMvc.perform(put("/courses/1")
                .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                .content(
                        objectMapper.writeValueAsString(
                                TestObjectProvider.getCourse(1L, "JUNIT")
                        )
                )
        ).andExpect(status().isOk());
    }

    @Test
    void delete() throws Exception {
        when(courseRepositoryMock.findById(1L)).thenReturn(Optional.of(TestObjectProvider.getCourse(1L, "JUNIT")));

        mockMvc.perform(MockMvcRequestBuilders.delete("/courses/1")
        ).andExpect(status().isNoContent());
    }

    @Test
    void addStudentToCourse() throws Exception {
        when(courseRepositoryMock.findById(1L)).thenReturn(Optional.of(TestObjectProvider.getCourse(1L, "JUNIT")));
        when(courseRepositoryMock.save(any(Course.class))).thenReturn(null);
        when(studentRepositoryMock.findById(1L)).thenReturn(Optional.of(TestObjectProvider.getStudent(1L, "Fatou")));
        when(studentRepositoryMock.save(any(Student.class))).thenReturn(null);

        mockMvc.perform(post("/courses/1/students/1")
        ).andExpect(status().isOk());
    }

    @Test
    void removeStudentFromCourse() throws Exception {
        when(courseRepositoryMock.findById(1L)).thenReturn(Optional.of(TestObjectProvider.getCourse(1L, "JUNIT")));
        when(courseRepositoryMock.save(any(Course.class))).thenReturn(null);
        when(studentRepositoryMock.findById(1L)).thenReturn(Optional.of(TestObjectProvider.getStudent(1L, "Fatou")));
        when(studentRepositoryMock.save(any(Student.class))).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.delete("/courses/1/students/1")
        ).andExpect(status().isNoContent());
    }
}