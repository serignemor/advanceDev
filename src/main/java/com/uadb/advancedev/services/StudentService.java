package com.uadb.advancedev.services;

import com.uadb.advancedev.dto.StudentDto;
import com.uadb.advancedev.entities.Student;
import com.uadb.advancedev.entities.Course;
import com.uadb.advancedev.mappers.StudentMapper;
import com.uadb.advancedev.repositories.CourseRepository;
import com.uadb.advancedev.repositories.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    private final StudentMapper studentMapper;


    public void save(StudentDto studentDTO) {
        studentRepository.save(studentMapper.toEntity(studentDTO));
    }


    public List<StudentDto> getAllStudents() {
        return studentMapper.toDto(studentRepository.findAll());
    }


    public Optional<StudentDto> getStudentById(long idStudent) {
        Optional<Student> studentOpt = studentRepository.findById(idStudent);

        if (studentOpt.isPresent()) {
            Student student = studentOpt.get();
            StudentDto studentDTO = studentMapper.toDto(student);
            return Optional.of(studentDTO);
        }

        return Optional.empty();
    }
    public void joinCourse(long studentId, long courseId) {
        Student student = studentRepository.findById(studentId).orElseThrow();
        Course course = courseRepository.findById(courseId).orElseThrow();

        student.getCourseSet().add(course);
        course.getStudentSet().add(student);

        studentRepository.save(student);
        courseRepository.save(course);
    }

}
