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


    @PostMapping("/students")
    public ResponseEntity<Void> save(@RequestBody StudentDto studentDTO) {

        studentService.save(studentDTO);

        return ResponseEntity.status(201).build();
    }

    @GetMapping("/students")
    public ResponseEntity<List<StudentDto>> getStudents() {

        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/students/{studentId}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable long studentId) {
        Optional<StudentDto> studentByIdOpt = studentService.getStudentById(studentId);

        return studentByIdOpt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity
                        .notFound()
                        .build()
                );
    }
    @PostMapping("/student/{studentId}/courses/{courseId}")
    public ResponseEntity<String> joinCourse(@PathVariable long studentId, @PathVariable long courseId) {
        studentService.joinCourse(studentId, courseId);
        return ResponseEntity.ok("L'étudiant a rejoint le cours avec succès.");
    }
}
