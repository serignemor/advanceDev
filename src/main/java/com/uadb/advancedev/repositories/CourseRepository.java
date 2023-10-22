package com.uadb.advancedev.repositories;

import com.uadb.advancedev.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CourseRepository  extends JpaRepository<Course, Long> {
}
