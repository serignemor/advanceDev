package com.uadb.advancedev.resources;

import com.uadb.advancedev.dto.EvaluationDto;
import com.uadb.advancedev.services.EvaluationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class EvaluationResource {

    private final EvaluationService evaluationService;


    @GetMapping("/evaluations")
    public ResponseEntity<List<EvaluationDto>> getAllEvaluations() {
        return ResponseEntity.ok(evaluationService.getAllEvaluations());
    }


    @GetMapping("/evaluations/{evaluationId}")
    public ResponseEntity<EvaluationDto> getEvaluationById(@PathVariable long evaluationId) {
        Optional<EvaluationDto> evaluationDto = evaluationService.getEvaluationById(evaluationId);
        return evaluationDto
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping("/evaluations")
    public ResponseEntity<Void> save(@RequestBody EvaluationDto evaluationDto) {
        evaluationService.save(evaluationDto);
        return ResponseEntity.status(201).build();
    }


    @PutMapping("/evaluations/{evaluationId}")
    public ResponseEntity<?> update(@PathVariable long evaluationId,
                                    @RequestBody EvaluationDto evaluationDto) {
        Optional<EvaluationDto> optionalEvaluation = evaluationService.update(evaluationId, evaluationDto);
        return optionalEvaluation
                .map(evaluation -> ResponseEntity.ok().build())
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }


    @DeleteMapping("/evaluations/{evaluationId}")
    public ResponseEntity<?> delete(@PathVariable long evaluationId) {
        evaluationService.delete(evaluationId);
        return ResponseEntity.status(204).build();
    }
}
