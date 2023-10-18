package com.uadb.advancedev.mappers;


import com.uadb.advancedev.dto.StudentDTO;
import com.uadb.advancedev.entities.Student;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StudentMapper {

    private final ModelMapper modelMapper;

    public StudentDTO toDTO(Student student) {
        return modelMapper.map(student, StudentDTO.class);
    }

    ;

    public Student toEntity(StudentDTO studentDTO) {
        return modelMapper.map(studentDTO, Student.class);
    }

    ;
}
