package com.uadb.advancedev.resources;

import com.uadb.advancedev.dto.CourseDto;
import com.uadb.advancedev.dto.StudentDto;
import com.uadb.advancedev.services.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class CourseResource {
    private final CourseService courseService;

    @PostMapping("/course")
    public ResponseEntity<Void> save(@RequestBody CourseDto courseDto) {

        courseService.save(courseDto);

        return ResponseEntity.status(201).build();
    }

    @GetMapping("/course")
    public ResponseEntity<List<CourseDto>> getCourses() {

        return ResponseEntity.ok(courseService.getAllCourse());
    }
}
