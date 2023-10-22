package com.uadb.advancedev.mappers;

import com.uadb.advancedev.dto.EvaluationDto;
import com.uadb.advancedev.entities.Evaluation;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EvaluationMapper {

    private final ModelMapper modelMapper;


    public EvaluationDto toDto(Evaluation evaluation) {
        return modelMapper.map(evaluation, EvaluationDto.class);
    }

    public List<EvaluationDto> toDto(List<Evaluation> evaluationList){
        return evaluationList.stream()
                .map(this::toDto)
                .toList();
    }


    public Evaluation toEntity(EvaluationDto evaluationDto) {
        return modelMapper.map(evaluationDto, Evaluation.class);
    }


    public List<Evaluation> toEntity(List<EvaluationDto> evaluationDtoList){
        return evaluationDtoList.stream()
                .map(this::toEntity)
                .toList();
    }


    public void update(EvaluationDto evaluationDto, Evaluation evaluation) {
        modelMapper.map(evaluationDto, evaluation);
    }
}
