package com.uok.backend.course;

import org.hibernate.annotations.SQLInsert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CourseRegistrationRepository extends JpaRepository<CourseRegistration, Integer> {

    List<CourseRegistration> findAllByUserEmail(String userEmail);

    //FIXME
    // this should be changed to a jpa method if possible
    @Modifying
    @Transactional
    @Query(
            value =
                    "INSERT INTO course_registration (user_email, course_id) VALUES (:userEmail, :courseId)",
            nativeQuery = true)
    void addUserToCourse(String userEmail, String courseId);



}
