package com.uadb.advancedev.services;

import com.uadb.advancedev.dto.StudentDTO;
import com.uadb.advancedev.entities.Student;
import com.uadb.advancedev.mappers.ProfessorMapper;
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

    private final ProfessorMapper professorMapper;

    private final StudentMapper studentMapper;


    public void save(StudentDTO studentDTO) {
        Student student = studentMapper.toEntity(studentDTO);
        studentRepository.save(student);
    }


    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(studentMapper::toDTO)
                .toList();
    }


    public Optional<StudentDTO> getStudentById(long idStudent) {
        Optional<Student> studentOpt = studentRepository.findById(idStudent);

        if (studentOpt.isPresent()) {
            Student student = studentOpt.get();
            StudentDTO studentDTO = studentMapper.toDTO(student);
            return Optional.of(studentDTO);
        }

        return Optional.empty();
    }
}
