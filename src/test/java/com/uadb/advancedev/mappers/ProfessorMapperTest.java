package com.uadb.advancedev.mappers;

import com.uadb.advancedev.dto.ProfessorDto;
import com.uadb.advancedev.entities.Professor;
import com.uadb.advancedev.providers.TestObjectProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class ProfessorMapperTest {

    @Autowired
    private ProfessorMapper professorMapper;


    @Test
    void toDto() {
        Professor professor = TestObjectProvider.getProfessor(1, "Prof");
        ProfessorDto professorDto = professorMapper.toDto(professor);

        assertEquals(professor.getId(), professorDto.getId());
        assertEquals(professor.getName(), professorDto.getName());

    }

    @Test
    void toEntity() {
        ProfessorDto professorDto = TestObjectProvider.getProfessorDto("Prof");
        Professor professor = professorMapper.toEntity(professorDto);

        assertEquals(professorDto.getName(), professor.getName());
    }
}