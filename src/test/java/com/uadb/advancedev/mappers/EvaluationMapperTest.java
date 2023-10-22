package com.uadb.advancedev.mappers;

import com.uadb.advancedev.dto.EvaluationDto;
import com.uadb.advancedev.entities.Evaluation;
import com.uadb.advancedev.providers.TestObjectProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class EvaluationMapperTest {

    @Autowired
    private EvaluationMapper evaluationMapper;

    @Test
    void toDto() {
        Evaluation evaluation = TestObjectProvider.getEvaluation(1, 10);
        EvaluationDto evaluationDto = evaluationMapper.toDto(evaluation);

        assertEquals(evaluation.getId(), evaluationDto.getId());
        assertEquals(evaluation.getRating(), evaluationDto.getRating() );
        assertEquals(
                evaluation.getCourse().getName(),
                evaluationDto.getCourse().getName()
        );
        assertEquals(
                evaluation.getStudent().getName(),
                evaluationDto.getStudent().getName()
        );
        assertEquals(
                evaluation.getProfessor().getName(),
                evaluationDto.getProfessor().getName()

        );
    }

    @Test
    void toEntity() {
        EvaluationDto evaluationDto = TestObjectProvider.getEvaluationDto( 10);
        Evaluation evaluation = evaluationMapper.toEntity(evaluationDto);

        assertEquals(evaluationDto.getId(), evaluation.getId());
        assertEquals(evaluationDto.getRating(), evaluation.getRating() );
        assertEquals(
                evaluationDto.getCourse().getName(),
                evaluation.getCourse().getName()
        );
        assertEquals(
                evaluationDto.getStudent().getName(),
                evaluation.getStudent().getName()
        );
        assertEquals(
                evaluationDto.getProfessor().getName(),
                evaluation.getProfessor().getName()

        );
    }
}