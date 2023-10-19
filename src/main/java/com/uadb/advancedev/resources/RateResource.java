package com.uadb.advancedev.resources;

import com.uadb.advancedev.entities.enums.RateValue;
import com.uadb.advancedev.services.RateService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class RateResource {
    private final RateService rateService;

    @PostMapping("/students/{studentId}/professors/{professorId}")
    public ResponseEntity<String> evaluateProfessor(
            @PathVariable long studentId,
            @PathVariable long professorId,
            @RequestParam RateValue rating
    ) {
        rateService.evaluateProfessor(studentId, professorId, rating);
        return ResponseEntity.ok("L'évaluation du professeur a été enregistrée avec succès.");
    }
}
