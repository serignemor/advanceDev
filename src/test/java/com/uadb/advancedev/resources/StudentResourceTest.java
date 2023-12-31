package com.uadb.advancedev.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uadb.advancedev.entities.Student;
import com.uadb.advancedev.entities.enums.RateValue;
import com.uadb.advancedev.providers.TestObjectProvider;
import com.uadb.advancedev.repositories.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class StudentResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private StudentRepository studentRepositoryMock;


    @Test
    void getStudents() throws Exception {
        mockMvc.perform(get("/students")
        ).andExpect(status().isOk());
    }

    @Test
    void getStudentById() throws Exception {
        when(studentRepositoryMock.findById(1L)).thenReturn(Optional.of(TestObjectProvider.getStudent(1L, "Serigne")));

        mockMvc.perform(get("/students/1")
        ).andExpect(status().isOk());
    }

    @Test
    void save() throws Exception {
        mockMvc.perform(post("/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                        objectMapper.writeValueAsString(
                                TestObjectProvider.getStudentDto("Serigne")
                        )
                )
        ).andExpect(status().isCreated());
    }

    @Test
    void update() throws Exception {
        when(studentRepositoryMock.findById(1L)).thenReturn(Optional.of(TestObjectProvider.getStudent(1L, "Serigne")));
        when(studentRepositoryMock.save(any(Student.class))).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.put("/students/1")
                .contentType("application/json")
                .content(
                        objectMapper.writeValueAsString(
                                TestObjectProvider.getRateDto(RateValue.NEUTRAL)
                        )
                )
        ).andExpect(status().isOk());
    }

    @Test
    void delete() throws Exception {
        when(studentRepositoryMock.findById(1L)).thenReturn(Optional.of(TestObjectProvider.getStudent(1L, "Serigne")));

        mockMvc.perform(MockMvcRequestBuilders.delete("/students/1"))
                .andExpect(status().isNoContent());

    }
}