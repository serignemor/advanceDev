package com.uadb.advancedev.services;

import com.uadb.advancedev.dto.CourseDto;
import com.uadb.advancedev.entities.Course;
import com.uadb.advancedev.entities.Student;
import com.uadb.advancedev.mappers.CourseMapper;
import com.uadb.advancedev.repositories.CourseRepository;
import com.uadb.advancedev.repositories.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    private final StudentRepository studentRepository;


    public List<CourseDto> getAllCourses() {
        return courseMapper.toDto( courseRepository.findAll() );
    }


    public Optional<CourseDto> getCourseById(long idCourse) {
        Optional<Course> courseOpt = courseRepository.findById(idCourse);

        if (courseOpt.isPresent()) {
            Course course = courseOpt.get();
            CourseDto courseDTO = courseMapper.toDto(course);
            return Optional.of(courseDTO);
        }

        return Optional.empty();
    }


    public void save(CourseDto courseDto) {
        Course course = courseMapper.toEntity(courseDto);
        courseRepository.save(course);
    }


    public Optional<Course> update(long idCourse, CourseDto courseDTO) {
        Optional<Course> courseOpt = courseRepository.findById(idCourse);
        if (courseOpt.isPresent()) {
            Course course = courseOpt.get();
            courseMapper.update(courseDTO, course);
            courseRepository.save(course);
            return Optional.of(course);
        }

        return Optional.empty();
    }


    public void delete(long idCourse) {
        courseRepository.deleteById(idCourse);
    }


    public void addStudentToCourse(Long courseId, Long studentId){
        Optional<Course> courseOpt = courseRepository.findById(courseId);
        Optional<Student> studentOpt = studentRepository.findById(studentId);

        if(courseOpt.isEmpty() || studentOpt.isEmpty()){
            return;
        }

        Course course = courseOpt.get();
        Student student = studentOpt.get();

        if(course.getStudentSet().contains(student)){
            return;
        }

        course.getStudentSet().add(student);
        student.getCourseSet().add(course);
        courseRepository.save(course);
        studentRepository.save(student);

    }


    public void removeStudentFromCourse(Long courseId, Long studentId){
        Optional<Course> courseOpt = courseRepository.findById(courseId);
        Optional<Student> studentOpt = studentRepository.findById(studentId);

        if(courseOpt.isEmpty() || studentOpt.isEmpty()){
            return;
        }

        Course course = courseOpt.get();
        Student student = studentOpt.get();

        if(!course.getStudentSet().contains(student)){
            return;
        }

        course.getStudentSet().remove(student);
        student.getCourseSet().remove(course);
        courseRepository.save(course);
        studentRepository.save(student);

    }
}
