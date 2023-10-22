package com.uadb.advancedev.services;

import com.uadb.advancedev.dto.StudentDto;
import com.uadb.advancedev.entities.Student;
import com.uadb.advancedev.providers.TestObjectProvider;
import com.uadb.advancedev.repositories.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class StudentServiceTest {

    private final static String studentName = "Serigne";

    @Autowired
    private StudentService studentService;

    @MockBean
    private StudentRepository studentRepositoryMock;

    @BeforeEach
    void setUp() {
    }

    @Test
    void save() {

        StudentDto studentDto = TestObjectProvider.getStudentDto("Serigne");
        when(studentRepositoryMock.save(any(Student.class))).thenReturn(null);

        studentService.save(studentDto);

        verify(studentRepositoryMock).save(any(Student.class));

    }


    @Test
    void getAllStudents() {

        when(studentRepositoryMock.findAll()).thenReturn(TestObjectProvider.getStudentList());

        List<StudentDto> allStudents = studentService.getAllStudents();

        assertEquals(1, allStudents.size());
        assertEquals("Fatou", allStudents.get(0).getName());
        assertEquals(1L, allStudents.get(0).getId());
    }

    @Test
    void getStudent_whenStudentExist() {
        long studentId = 1L;
        when(studentRepositoryMock.findById(studentId))
                .thenReturn(Optional.of(TestObjectProvider.getStudent(studentId, studentName)));

        StudentDto studentDto = studentService.getStudentById(studentId).get();

        assertEquals(studentId, studentDto.getId());
        assertEquals(studentName, studentDto.getName());
    }

    @Test
    void getStudent_whenStudentDoesNotExist() {
        long studentId = 1L;
        when(studentRepositoryMock.findById(studentId))
                .thenReturn(Optional.of(TestObjectProvider.getStudent(1, studentName)));

        StudentDto studentDto = studentService.getStudentById(studentId).get();

        assertNotNull(studentDto);
    }

}