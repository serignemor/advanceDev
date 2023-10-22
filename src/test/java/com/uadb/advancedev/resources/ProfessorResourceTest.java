package com.uadb.advancedev.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uadb.advancedev.providers.TestObjectProvider;
import com.uadb.advancedev.repositories.ProfessorRepository;
import com.uadb.advancedev.services.ProfessorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProfessorResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProfessorService professorService;

    @MockBean
    private ProfessorRepository professorRepositoryMock;

    @Test
    void getProfessors() throws Exception {
        mockMvc.perform(get("/professors")
        ).andExpect(status().isOk());
    }

    @Test
    void getProfessorById() throws Exception {
        when(professorRepositoryMock.findById(1L)).thenReturn(Optional.of(TestObjectProvider.getProfessor(1L, "Prof")));

        mockMvc.perform(get("/professors/1")
        ).andExpect(status().isOk());
    }

    @Test
    void save() {
        when(professorRepositoryMock.save(TestObjectProvider.getProfessor(1L, "Prof"))).thenReturn(null);

        professorService.save(TestObjectProvider.getProfessorDto(1L, "Prof"));
    }

    @Test
    void update() throws Exception {
        when(professorRepositoryMock.findById(1L)).thenReturn(Optional.of(TestObjectProvider.getProfessor(1L, "Prof")));

        mockMvc.perform(get("/professors/1")
                .contentType("application/json")
                .content(
                        objectMapper.writeValueAsString(
                                TestObjectProvider.getProfessorDto("Professor")
                        )
                )
        ).andExpect(status().isOk());
    }

    @Test
    void delete() throws Exception {
        when(professorRepositoryMock.findById(1L)).thenReturn(Optional.of(TestObjectProvider.getProfessor(1L, "Prof")));

        mockMvc.perform(MockMvcRequestBuilders.delete("/professors/1")
        ).andExpect(status().isNoContent());
    }
}