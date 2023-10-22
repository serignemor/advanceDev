package com.uadb.advancedev.mappers;


import com.uadb.advancedev.dto.StudentDto;
import com.uadb.advancedev.entities.Student;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentMapper {

    private final ModelMapper modelMapper;


    public StudentDto toDto(Student student) {
        return modelMapper.map(student, StudentDto.class);
    }


    public List<StudentDto> toDto(List<Student> studentList){
        return studentList.stream()
                .map(this::toDto)
                .toList();
    }


    public Student toEntity(StudentDto studentDto) {
        return modelMapper.map(studentDto, Student.class);
    }


    public List<Student> toEntity(List<StudentDto> studentDtoList){
        return studentDtoList.stream()
                .map(this::toEntity)
                .toList();
    }


    public void update(StudentDto studentDto, Student student) {
        modelMapper.map(studentDto, student);
    }

}
