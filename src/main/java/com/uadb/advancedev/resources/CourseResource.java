package com.uadb.advancedev.resources;

import com.uadb.advancedev.dto.CourseDto;
import com.uadb.advancedev.entities.Course;
import com.uadb.advancedev.services.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@AllArgsConstructor
public class CourseResource {

    private final CourseService courseService;


    @GetMapping("/courses")
    public ResponseEntity<List<CourseDto>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }


    @GetMapping("/courses/{courseId}")
    public ResponseEntity<CourseDto> getCourseById(@PathVariable long courseId) {
        Optional<CourseDto> courseDto = courseService.getCourseById(courseId);
        return courseDto
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping("/courses")
    public ResponseEntity<?> save(@RequestBody CourseDto courseDto) {
        courseService.save(courseDto);
        return ResponseEntity.status(201).build();
    }


    @PutMapping("/courses/{courseId}")
    public ResponseEntity<?> update(@PathVariable long courseId,
                                    @RequestBody CourseDto courseDto) {
        Optional<Course> optionalCourse = courseService.update(courseId, courseDto);
        return optionalCourse
                .map(course -> ResponseEntity.ok().build())
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }


    @DeleteMapping("/courses/{courseId}")
    public ResponseEntity<?> delete(@PathVariable long courseId) {
        courseService.delete(courseId);
        return ResponseEntity.status(204).build();
    }


    @PostMapping("/courses/{courseId}/students/{studentId}")
    public void addStudentToCourse(@PathVariable Long courseId,
                                   @PathVariable Long studentId){
        courseService.addStudentToCourse(courseId, studentId);
    }


    @DeleteMapping("/courses/{courseId}/students/{studentId}")
    public void removeStudentFromCourse(@PathVariable Long courseId,
                                        @PathVariable Long studentId){
        courseService.removeStudentFromCourse(courseId, studentId);
    }


}
