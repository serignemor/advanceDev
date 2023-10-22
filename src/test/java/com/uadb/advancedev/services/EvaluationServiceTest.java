package com.uadb.advancedev.services;

import com.uadb.advancedev.dto.CourseDto;
import com.uadb.advancedev.dto.EvaluationDto;
import com.uadb.advancedev.dto.ProfessorDto;
import com.uadb.advancedev.dto.StudentDto;
import com.uadb.advancedev.entities.Evaluation;
import com.uadb.advancedev.providers.TestObjectProvider;
import com.uadb.advancedev.repositories.EvaluationRepository;
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
class EvaluationServiceTest {

    @Autowired
    private EvaluationService evaluationService;

    @MockBean
    private EvaluationRepository evaluationRepositoryMock;

    @Test
    void getAllEvaluations() {
        when(evaluationRepositoryMock.findAll()).thenReturn(TestObjectProvider.getEvaluationList());

        List<EvaluationDto> allProfessors = evaluationService.getAllEvaluations();
        assertEquals(1, allProfessors.size());

        ProfessorDto professorDto = allProfessors.get(0).getProfessor();
        assertEquals("Prof", professorDto.getName());

        CourseDto courseDto = allProfessors.get(0).getCourse();
        assertEquals("JUNIT", courseDto.getName());

        StudentDto studentDto = allProfessors.get(0).getStudent();
        assertEquals("Fatou", studentDto.getName());
    }

    @Test
    void getEvaluationById() {
        when(evaluationRepositoryMock.findById(1L)).thenReturn(Optional.of(TestObjectProvider.getEvaluation(1L, 13)));

        Optional<EvaluationDto> optionalEvaluationDto = evaluationService.getEvaluationById(1L);
        assertTrue(optionalEvaluationDto.isPresent());

        EvaluationDto evaluationDto = optionalEvaluationDto.get();

        ProfessorDto professorDto = evaluationDto.getProfessor();
        assertEquals("Prof", professorDto.getName());

        CourseDto courseDto = evaluationDto.getCourse();
        assertEquals("JUNIT", courseDto.getName());

        StudentDto studentDto = evaluationDto.getStudent();
        assertEquals("Fatou", studentDto.getName());
    }

    @Test
    void save() {
        when(evaluationRepositoryMock.save(any(Evaluation.class))).thenReturn(null);

        evaluationService.save(TestObjectProvider.getEvaluationDto(13));
        verify(evaluationRepositoryMock).save(any(Evaluation.class));
    }

    @Test
    void update() {
        when(evaluationRepositoryMock.findById(1L)).thenReturn(Optional.of(TestObjectProvider.getEvaluation(1L, 13)));
        when(evaluationRepositoryMock.save(TestObjectProvider.getEvaluation(1L, 14))).thenReturn(null);

        Optional<EvaluationDto> optionalEvaluationDto = evaluationService.update(1L, TestObjectProvider.getEvaluationDto(14));
        assertTrue(optionalEvaluationDto.isPresent());

        EvaluationDto evaluationDto = optionalEvaluationDto.get();
        assertEquals(14, evaluationDto.getRating());
    }

    @Test
    void delete() {
        when(evaluationRepositoryMock.findById(1L)).thenReturn(Optional.of(TestObjectProvider.getEvaluation(1L, 13)));

        evaluationService.delete(1L);
        verify(evaluationRepositoryMock).deleteById(1L);
    }
}