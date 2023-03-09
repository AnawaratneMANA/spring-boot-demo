package com.example.demo.controllers;

import com.example.demo.exception.AgentException;
import com.example.demo.model.ManyToMany.Course;
import com.example.demo.model.ManyToMany.Student;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.response.MappingResponse;
import com.example.demo.service.MappingService;
import io.swagger.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static com.example.demo.config.Constants.*;
import static com.example.demo.config.Constants.HttpCodesMessages.HTTP_200_CODE;
import static com.example.demo.config.Constants.ProductProperties.PRODUCT;
import static com.example.demo.config.Constants.StudentCourseProperties.STU_COURSE;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping( value = API + VERSION + STU_COURSE, produces = APPLICATION_JSON_VALUE)
public class StudentCourseController {
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    CourseRepository courseRepository;
    @Autowired
    MappingService mappingService;

    @PostMapping("/student")
    public ResponseEntity<Student> createStudent(@RequestBody Student  student) {
        MappingResponse response = mappingService.ifExistStudent(student);
        Student _student;
        _student = studentRepository.save((Student) response.getObject());
        return new ResponseEntity<>(_student, HttpStatus.CREATED);
    }
    @PutMapping("/student/course/{studentId}")
    public ResponseEntity<Student> addCourseToStudent(@RequestBody Course course, @PathVariable Long studentId){
        // Validate the course if existed.
        MappingResponse response = mappingService.ifExistCourse(course);
        Optional<Student> student = studentRepository.findById(studentId);
        if(student.isPresent()){
            Student studentObject = student.get();
            if(studentObject != null){
                studentObject.getLikedCourses().add((Course) response.getObject());
                return new ResponseEntity<>(studentRepository.save(studentObject), HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<>(new Student(), HttpStatus.BAD_REQUEST);
        }
        return null;
    }
    @PostMapping("/course")
    public ResponseEntity<Course> addCourses(@RequestBody Course course){
        MappingResponse response = mappingService.ifExistCourse(course);
        Course courseObject = courseRepository.save((Course) response.getObject());
        return new ResponseEntity<>(courseObject, HttpStatus.OK);
    }

    @GetMapping("/student/{studentId}/course")
    public ResponseEntity<List<Course>> getCoursesForAStudent(@PathVariable(value = "studentId") Long studentId) throws Exception {
        if (!studentRepository.existsById(studentId)) {
            throw new Exception("Not found Student with id = " + studentId);
        }
        List<Course> courses = courseRepository.findCoursesByStudent(studentId);
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }
    @GetMapping("/student")
    public ResponseEntity<List<Student>> getStudents(){
        List<Student> students = studentRepository.findAll();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/course")
    public ResponseEntity<List<Course>> getCourses(){
        List<Course> courses = courseRepository.findAll();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }





}
