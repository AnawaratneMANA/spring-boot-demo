package com.example.demo.service;

import com.example.demo.model.ManyToMany.Course;
import com.example.demo.model.ManyToMany.Student;
import com.example.demo.response.MappingResponse;

public interface MappingService {
    MappingResponse ifExistCourse(Course course);
    MappingResponse ifExistStudent(Student student);
}
