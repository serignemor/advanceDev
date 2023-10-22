package com.uadb.advancedev.providers;

import com.uadb.advancedev.dto.*;
import com.uadb.advancedev.entities.*;
import com.uadb.advancedev.entities.enums.RateValue;

import java.util.List;
import java.util.Set;

public class TestObjectProvider {

    public static Student getStudent(long id, String name) {
        Student student = new Student();
        student.setName(name);
        student.setId(id);
        Course course = getCourse(1, "JUNIT");
        student.setCourseSet(Set.of(course));
        return student;
    }


    public static List<Student> getStudentList() {
        return List.of(getStudent(1, "Fatou"));
    }


    public static StudentDto getStudentDto(String name) {
        StudentDto studentDto = new StudentDto();
        studentDto.setName(name);
        CourseDto courseDto = getCourseDto("JUNIT");
        studentDto.setCourseSet(Set.of(courseDto));
        return studentDto;
    }


    public static Course getCourse(long id, String name) {
        Course course = new Course();
        course.setId(id);
        course.setName(name);
        return course;
    }

    public static CourseDto getCourseDto(String name) {
        CourseDto courseDto = new CourseDto();
        courseDto.setName(name);
        return courseDto;
    }


    public static List<Course> getCourseList() {
        return List.of(getCourse(1, "JUNIT"));
    }


    public static Professor getProfessor(long id, String name) {
        Professor professor = new Professor();
        professor.setId(id);
        professor.setName(name);
        return professor;
    }


    public static Professor getProfessor(String name) {
        Professor professor = new Professor();
        professor.setName(name);
        return professor;
    }


    public static ProfessorDto getProfessorDto(String name) {
        ProfessorDto professorDto = new ProfessorDto();
        professorDto.setName(name);
        return professorDto;
    }


    public static ProfessorDto getProfessorDto(long id, String name) {
        ProfessorDto professorDto = new ProfessorDto();
        professorDto.setId(id);
        professorDto.setName(name);
        return professorDto;
    }


    public static Evaluation getEvaluation(long id, int rating) {
        Evaluation evaluation = new Evaluation();
        evaluation.setId(id);
        evaluation.setRating(rating);
        evaluation.setProfessor(getProfessor(1, "Prof"));
        evaluation.setCourse(getCourse(1, "JUNIT"));
        evaluation.setStudent(getStudent(1, "Fatou"));
        return evaluation;
    }


    public static EvaluationDto getEvaluationDto(int rating) {
        EvaluationDto evaluationDto = new EvaluationDto();
        evaluationDto.setRating(rating);
        evaluationDto.setProfessor(getProfessorDto("Prof"));
        evaluationDto.setCourse(getCourseDto("JUNIT"));
        evaluationDto.setStudent(getStudentDto("Fatou"));
        return evaluationDto;
    }


    public static Rate getRate(long id, RateValue rateValue) {
        Rate rate = new Rate();
        rate.setId(id);
        rate.setRating(rateValue);
        rate.setProfessor(getProfessor(1, "Prof"));
        rate.setCourse(getCourse(1, "JUNIT"));
        rate.setStudent(getStudent(1, "Fatou"));
        return rate;
    }


    public static RateDto getRateDto(RateValue rating) {
        RateDto rateDto = new RateDto();
        rateDto.setRating(rating);
        rateDto.setProfessor(getProfessorDto("Prof"));
        rateDto.setCourse(getCourseDto("JUNIT"));
        rateDto.setStudent(getStudentDto("Fatou"));
        return rateDto;
    }


    public static List<Evaluation> getEvaluationList() {
        return List.of(getEvaluation(1, 5));
    }

    public static List<Professor> getProfessorList() {
        return List.of(getProfessor(1, "Prof"));
    }
}
