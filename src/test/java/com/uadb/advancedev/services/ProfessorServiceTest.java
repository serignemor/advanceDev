package com.uadb.advancedev.services;

import com.uadb.advancedev.dto.ProfessorDto;
import com.uadb.advancedev.entities.Professor;
import com.uadb.advancedev.providers.TestObjectProvider;
import com.uadb.advancedev.repositories.ProfessorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProfessorServiceTest {

    @Autowired
    private ProfessorService professorService;

    @MockBean
    private ProfessorRepository professorRepositoryMock;

    @Test
    void getAllProfessors() {
        when(professorRepositoryMock.findAll()).thenReturn(TestObjectProvider.getProfessorList());

        List<ProfessorDto> allProfessors = professorService.getAllProfessors();
        assertEquals(1, allProfessors.size());
        assertEquals("Prof", allProfessors.get(0).getName());
    }

    @Test
    void getProfessorById() {
        when(professorRepositoryMock.findById(1L)).thenReturn(Optional.of(TestObjectProvider.getProfessor(1L, "Prof")));

        Optional<ProfessorDto> professorDto = professorService.getProfessorById(1L);
        assertTrue(professorDto.isPresent());
        assertEquals("Prof", professorDto.get().getName());
    }

    @Test
    void save() {
        when(professorRepositoryMock.save(TestObjectProvider.getProfessor(1L, "Prof"))).thenReturn(null);

        professorService.save(TestObjectProvider.getProfessorDto(1L, "Prof"));
        verify(professorRepositoryMock).save(TestObjectProvider.getProfessor(1L, "Prof"));
    }

    @Test
    void update() {
        when(professorRepositoryMock.findById(1L)).thenReturn(Optional.of(TestObjectProvider.getProfessor(1L, "Prof")));

        Optional<Professor> updatedProfessor =  professorService.update(1, TestObjectProvider.getProfessorDto("Professor"));
        assertTrue(updatedProfessor.isPresent());
        assertEquals("Professor", updatedProfessor.get().getName());
    }

    @Test
    void delete() {
        professorService.delete(1L);
        verify(professorRepositoryMock).deleteById(1L);
    }
}