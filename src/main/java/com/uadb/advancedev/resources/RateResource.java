package com.uadb.advancedev.resources;

import com.uadb.advancedev.entities.Rate;
import com.uadb.advancedev.entities.enums.RateValue;
import com.uadb.advancedev.services.RateService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/evaluations")
    public ResponseEntity<List<Rate>> getAllRatings() {
        List<Rate> ratings = rateService.getAllRatings();
        return ResponseEntity.ok(ratings);
    }
}
