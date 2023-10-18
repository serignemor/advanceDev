package com.uadb.advancedev.services;

import com.uadb.advancedev.dto.ProfessorDto;
import com.uadb.advancedev.entities.Professor;
import com.uadb.advancedev.mappers.ProfessorMapper;
import com.uadb.advancedev.repositories.ProfessorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProfessorService {

    private final ProfessorRepository professorRepository;

    private final ProfessorMapper professorMapper;


    public void save(ProfessorDto professorDTO) {
        Professor professor = professorMapper.toEntity(professorDTO);
        professorRepository.save(professor);
    }


    public List<ProfessorDto> getAllProfessors() {
        return professorMapper.toDto(professorRepository.findAll());
    }


    public Optional<ProfessorDto> getProfessorById(long idProfessor) {
        Optional<Professor> professorOpt = professorRepository.findById(idProfessor);

        if (professorOpt.isPresent()) {
            Professor professor = professorOpt.get();
            ProfessorDto professorDTO = professorMapper.toDto(professor);
            return Optional.of(professorDTO);
        }

        return Optional.empty();
    }
}
