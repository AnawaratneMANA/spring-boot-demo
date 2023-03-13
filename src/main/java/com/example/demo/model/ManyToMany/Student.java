package com.example.demo.model.ManyToMany;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "student_name")
    private String studentName;

    public Student(){

    }

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.ALL
            })
    @JoinTable(name = "student_courses",
            joinColumns = { @JoinColumn(name = "student_id", referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "course_id", referencedColumnName = "id") })
    private Set<Course> likedCourses = new HashSet<>();

    public void addCourse(Course course){
        this.likedCourses.add(course);
        course.getLikes().add(this);
    }
    public void removeCourse(long courseId) {
        Course course = this.likedCourses.stream().filter(t -> t.getId() == courseId).findFirst().orElse(null);
        if (course != null) {
            this.likedCourses.remove(course);
            course.getLikes().remove(this);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Set<Course> getLikedCourses() {
        return likedCourses;
    }

    public void setLikedCourses(Set<Course> likedCourses) {
        this.likedCourses = likedCourses;
    }
}
