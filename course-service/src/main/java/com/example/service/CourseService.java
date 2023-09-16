package com.example.service;


import com.example.dto.CourseDTO;
import com.example.model.request.CourseRequest;

import java.util.List;

public interface CourseService {
    CourseDTO createCourse(CourseRequest courseRequest);

    List<CourseDTO> getAllCourses();

    CourseDTO findCourseById(Integer id);

    void deleteCourseById(Integer id);

    CourseDTO updateCourseById(Integer id, CourseRequest courseRequest);
}
