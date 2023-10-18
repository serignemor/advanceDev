package com.uadb.advancedev.resources;

import com.uadb.advancedev.dto.ProfessorDto;
import com.uadb.advancedev.services.ProfessorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class ProfessorResource {

    private final ProfessorService professorService;

    @PostMapping("/professors")
    public ResponseEntity<Void> save(@RequestBody ProfessorDto professorDTO) {
        professorService.save(professorDTO);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/professors")
    public ResponseEntity<List<ProfessorDto>> getProfessors() {
        return ResponseEntity.ok(professorService.getAllProfessors());
    }

    @GetMapping("/professors/{professorId}")
    public ResponseEntity<ProfessorDto> getProfessorById(@PathVariable long professorId) {
        Optional<ProfessorDto> professorByIdOpt = professorService.getProfessorById(professorId);
        return professorByIdOpt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity
                        .notFound()
                        .build()
                );
    }
}