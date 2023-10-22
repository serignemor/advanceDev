package com.uadb.advancedev.services;

import com.uadb.advancedev.dto.StudentDto;
import com.uadb.advancedev.entities.Student;
import com.uadb.advancedev.mappers.StudentMapper;
import com.uadb.advancedev.repositories.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;


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


    public void save(StudentDto studentDTO) {
        studentRepository.save(studentMapper.toEntity(studentDTO));
    }


    public Optional<StudentDto> update(long idStudent, StudentDto studentDTO) {
        Optional<Student> studentOpt = studentRepository.findById(idStudent);
        if (studentOpt.isPresent()) {
            Student student = studentOpt.get();
            studentMapper.update(studentDTO, student);
            studentRepository.save(student);
            return Optional.of(studentDTO);
        }

        return Optional.empty();
    }


    public void delete(long idStudent) {
        studentRepository.deleteById(idStudent);
    }


}
