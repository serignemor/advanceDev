package com.uadb.advancedev.mappers;

import com.uadb.advancedev.dto.ProfessorDto;
import com.uadb.advancedev.entities.Professor;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProfessorMapper {

    private final ModelMapper modelMapper;


    public ProfessorDto toDto(Professor professor) {
        return modelMapper.map(professor, ProfessorDto.class);
    }


    public List<ProfessorDto> toDto(List<Professor> professorList){
        return professorList.stream()
                .map(this::toDto)
                .toList();
    }


    public Professor toEntity(ProfessorDto professorDto) {
        return modelMapper.map(professorDto, Professor.class);
    }


    public List<Professor> toEntity(List<ProfessorDto> professorDtoList){
        return professorDtoList.stream()
                .map(this::toEntity)
                .toList();
    }


    public void update(ProfessorDto professorDto, Professor professor) {
        modelMapper.map(professorDto, professor);
    }

}
