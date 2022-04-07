package com.uok.backend.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRegistrationRepository extends JpaRepository<CourseRegistration, Integer> {
    // this should be changed to a jpa method if possible
    @Query(value = "SELECT * FROM course_registration where user_email = :userEmail", nativeQuery = true)
    List<CourseRegistration> findByUserEmail(String userEmail);
}
