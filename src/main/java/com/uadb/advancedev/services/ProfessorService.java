package com.uadb.advancedev.services;

import com.uadb.advancedev.dto.ProfessorDTO;
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


    public void save(ProfessorDTO professorDTO) {
        Professor professor = professorMapper.toEntity(professorDTO);
        professorRepository.save(professor);
    }


    public List<ProfessorDTO> getAllProfessors() {
        return professorRepository.findAll().stream()
                .map(professorMapper::toDTO)
                .toList();
    }


    public Optional<ProfessorDTO> getProfessorById(long idProfessor) {
        Optional<Professor> professorOpt = professorRepository.findById(idProfessor);

        if (professorOpt.isPresent()) {
            Professor professor = professorOpt.get();
            ProfessorDTO professorDTO = professorMapper.toDTO(professor);
            return Optional.of(professorDTO);
        }

        return Optional.empty();
    }
}
