package com.uadb.advancedev.resources;

import com.uadb.advancedev.dto.StudentDto;
import com.uadb.advancedev.services.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class StudentResource {

    private final StudentService studentService;


    @GetMapping("/students")
    public ResponseEntity<List<StudentDto>> getStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }


    @GetMapping("/students/{studentId}")
    public ResponseEntity<?> getStudentById(@PathVariable long studentId) {
        Optional<StudentDto> studentByIdOpt = studentService.getStudentById(studentId);
        return studentByIdOpt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity
                        .notFound()
                        .build()
                );
    }


    @PostMapping("/students")
    public ResponseEntity<?> save(@RequestBody StudentDto studentDTO) {
        studentService.save(studentDTO);
        return ResponseEntity.status(201).build();
    }


    @PutMapping("/students/{studentId}")
    public ResponseEntity<?> update(@PathVariable long studentId,
                                    @RequestBody StudentDto studentDTO) {
        Optional<StudentDto> optionalStudent = studentService.update(studentId, studentDTO);
        return optionalStudent
                .map(student -> ResponseEntity.ok().build())
                .orElseGet(() -> ResponseEntity
                        .notFound()
                        .build()
                );
    }


    @DeleteMapping("/students/{studentId}")
    public ResponseEntity<?> delete(@PathVariable long studentId) {
        studentService.delete(studentId);
        return ResponseEntity.status(204).build();
    }
}
