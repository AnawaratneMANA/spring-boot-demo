package com.example.demo.service.impl;

import com.example.demo.model.ManyToMany.Course;
import com.example.demo.model.ManyToMany.Student;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.response.MappingResponse;
import com.example.demo.service.MappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MappingServiceImpl implements MappingService {
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    StudentRepository  studentRepository;
    @Override
    public MappingResponse ifExistCourse(Course course) {
        MappingResponse mappingResponse = new MappingResponse();
        Course courseObject = null;
        courseObject = courseRepository.findCoursesByCourseName(course.getCourseName());
        if(courseObject != null){
            mappingResponse.setFlag(true);
            mappingResponse.setObject(courseObject);
            return mappingResponse;
        } else {
            mappingResponse.setFlag(false);
            mappingResponse.setObject(course);
            return mappingResponse;
        }
    }

    @Override
    public MappingResponse ifExistStudent(Student student) {
        MappingResponse mappingResponse = new MappingResponse();
        Student studentObject = null;
        studentObject = studentRepository.findStudentByStudentName(student.getStudentName());
        if(studentObject != null){
            mappingResponse.setFlag(true);
            mappingResponse.setObject(studentObject);
            return mappingResponse;
        } else {
            mappingResponse.setFlag(false);
            mappingResponse.setObject(student);
            return mappingResponse;
        }
    }
}
