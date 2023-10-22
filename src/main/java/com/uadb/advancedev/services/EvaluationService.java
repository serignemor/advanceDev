package com.uadb.advancedev.services;

import com.uadb.advancedev.dto.EvaluationDto;
import com.uadb.advancedev.entities.Evaluation;
import com.uadb.advancedev.mappers.EvaluationMapper;
import com.uadb.advancedev.repositories.EvaluationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EvaluationService {

    private final EvaluationRepository evaluationRepository;
    private final EvaluationMapper evaluationMapper;


    public List<EvaluationDto> getAllEvaluations() {
        return evaluationMapper.toDto( evaluationRepository.findAll() );
    }


    public Optional<EvaluationDto> getEvaluationById(long idEvaluation) {
        Optional<Evaluation> evaluationOpt = evaluationRepository.findById(idEvaluation);
        if (evaluationOpt.isPresent()) {
            Evaluation evaluation = evaluationOpt.get();
            return Optional.of(
                    evaluationMapper.toDto(evaluation)
            );
        }

        return Optional.empty();
    }


    public void save(EvaluationDto evaluationDto) {
        evaluationRepository.save(evaluationMapper.toEntity(evaluationDto));
    }


    public Optional<EvaluationDto> update(long idEvaluation, EvaluationDto evaluationDto) {
        Optional<Evaluation> evaluationOpt = evaluationRepository.findById(idEvaluation);
        if (evaluationOpt.isPresent()) {
            Evaluation evaluation = evaluationOpt.get();
            evaluationMapper.update(evaluationDto, evaluation);
            evaluationRepository.save(evaluation);
            return Optional.of(evaluationDto);
        }

        return Optional.empty();
    }


    public void delete(long idEvaluation) {
        evaluationRepository.deleteById(idEvaluation);
    }
}
