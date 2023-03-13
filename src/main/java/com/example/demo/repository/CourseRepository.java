package com.example.demo.repository;

import com.example.demo.model.ManyToMany.Course;
import com.example.demo.model.ManyToMany.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>{
    @Query(value = "SELECT * FROM course c, student_courses st WHERE c.id = st.course_id AND st.student_id = ?1", nativeQuery = true)
    List<Course> findCoursesByStudent(Long id);

    Course findCoursesByCourseName(String name);

    List<Course> findByLikes(Student student);
}
