package com.uadb.advancedev.mappers;

import com.uadb.advancedev.dto.ProfessorDTO;
import com.uadb.advancedev.entities.Professor;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProfessorMapper {

    private final ModelMapper modelMapper;

    public ProfessorDTO toDTO(Professor professor) {
        return modelMapper.map(professor, ProfessorDTO.class);
    }

    ;

    public Professor toEntity(ProfessorDTO professorDTO) {
        return modelMapper.map(professorDTO, Professor.class);
    }

    ;
}
