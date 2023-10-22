package com.uadb.advancedev.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uadb.advancedev.entities.Evaluation;
import com.uadb.advancedev.providers.TestObjectProvider;
import com.uadb.advancedev.repositories.EvaluationRepository;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class EvaluationResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private EvaluationRepository evaluationRepositoryMock;


    @Test
    void getAllEvaluations() throws Exception {
        mockMvc.perform(get("/evaluations")
        ).andExpect(status().isOk());
    }

    @Test
    void getEvaluationById() throws Exception {
        when(evaluationRepositoryMock.findById(1L)).thenReturn(Optional.of(TestObjectProvider.getEvaluation(1L, 14)));

        mockMvc.perform(get("/evaluations/1")
        ).andExpect(status().isOk());
    }

    @Test
    void save() throws Exception {
        when(evaluationRepositoryMock.save(any(Evaluation.class))).thenReturn(null);

        mockMvc.perform(post("/evaluations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                        objectMapper.writeValueAsString(
                                TestObjectProvider.getEvaluation(1L, 14)
                        )
                )
        ).andExpect(status().isCreated());
    }

    @Test
    void update() throws Exception {
        when(evaluationRepositoryMock.findById(1L)).thenReturn(Optional.of(TestObjectProvider.getEvaluation(1L, 14)));
        when(evaluationRepositoryMock.save(any(Evaluation.class))).thenReturn(null);

        mockMvc.perform(put("/evaluations/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                        objectMapper.writeValueAsString(
                                TestObjectProvider.getEvaluation(1L, 14)
                        )
                )
        ).andExpect(status().isOk());
    }

    @Test
    void delete() throws Exception {
        when(evaluationRepositoryMock.findById(1L)).thenReturn(Optional.of(TestObjectProvider.getEvaluation(1L, 14)));

        mockMvc.perform(MockMvcRequestBuilders.delete("/evaluations/1")
        ).andExpect(status().isNoContent());
    }
}