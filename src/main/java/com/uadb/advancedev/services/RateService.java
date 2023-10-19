package com.uadb.advancedev.services;

import com.uadb.advancedev.entities.Professor;
import com.uadb.advancedev.entities.Rate;
import com.uadb.advancedev.entities.Student;
import com.uadb.advancedev.entities.enums.RateValue;
import com.uadb.advancedev.repositories.ProfessorRepository;
import com.uadb.advancedev.repositories.RateRepository;
import com.uadb.advancedev.repositories.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RateService {

    private final RateRepository rateRepository;
    private final StudentRepository studentRepository;
    private final ProfessorRepository professorRepository;

    public void evaluateProfessor(long studentId, long professorId, RateValue rating) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow();
        Professor professor = professorRepository.findById(professorId)
                .orElseThrow();

        Rate rate = new Rate();
        rate.setStudent(student);
        rate.setProfessor(professor);
        rate.setRating(rating);

        rateRepository.save(rate);
    }
}
