package com.uadb.advancedev.resources;

import com.uadb.advancedev.dto.ProfessorDto;
import com.uadb.advancedev.entities.Professor;
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


    @GetMapping("/professors")
    public ResponseEntity<List<ProfessorDto>> getProfessors() {
        return ResponseEntity.ok(professorService.getAllProfessors());
    }


    @GetMapping("/professors/{professorId}")
    public ResponseEntity<ProfessorDto> getProfessorById(@PathVariable long professorId) {
        Optional<ProfessorDto> professorByIdOpt = professorService.getProfessorById(professorId);
        return professorByIdOpt
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build()
                );
    }


    @PostMapping("/professors")
    public ResponseEntity<?> save(@RequestBody ProfessorDto professorDTO) {
        professorService.save(professorDTO);
        return ResponseEntity.status(201).build();
    }


    @PutMapping("/professors/{professorId}")
    public ResponseEntity<?> update(@PathVariable long professorId,
                                    @RequestBody ProfessorDto professorDTO) {
        Optional<Professor> optionalProfessor = professorService.update(professorId, professorDTO);
        return optionalProfessor
                .map(professor -> ResponseEntity.ok().build())
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }


    @DeleteMapping("/professors/{professorId}")
    public ResponseEntity<?> delete(@PathVariable long professorId) {
        professorService.delete(professorId);
        return ResponseEntity.status(204).build();
    }
}