package com.uok.backend.course.registration;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CourseRegistrationRepository extends JpaRepository<CourseRegistration, CourseRegistrationId> {

    List<CourseRegistration> findAllByUserEmail(String userEmail);
    CourseRegistration findByCourseIdAndUserEmail(String courseId, String userEmail);
    List<CourseRegistration> findByCourseId(String courseId);

    @Modifying
    @Transactional
    @Query(
            value =
                    "INSERT INTO course_registration (user_email, course_id) VALUES (:userEmail, :courseId)",
            nativeQuery = true)
    void addUserToCourse(String userEmail, String courseId);



}
