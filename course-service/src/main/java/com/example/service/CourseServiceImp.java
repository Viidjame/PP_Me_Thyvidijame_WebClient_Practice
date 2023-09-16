package com.example.service;



import com.example.dto.CourseDTO;
import com.example.model.entity.Course;
import com.example.model.request.CourseRequest;
import com.example.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CourseServiceImp implements CourseService{
    private final CourseRepository courseRepository;

    @Override
    public CourseDTO createCourse(CourseRequest courseRequest) {
        return courseRepository.save(courseRequest.toCourseEntity()).toCourseDto();
    }

    @Override
    public List<CourseDTO> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream().map(Course::toCourseDto).toList();
    }

    @Override
    public CourseDTO findCourseById(Integer id) {
        return courseRepository.findById(id).get().toCourseDto();
    }

    @Override
    public void deleteCourseById(Integer id) {
        courseRepository.deleteById(id);
    }

    @Override
    public CourseDTO updateCourseById(Integer id, CourseRequest courseRequest) {
        Course course = courseRepository.findById(id).get();
        Course updateCourse;
        if(course != null){
            course.setCourseCode(courseRequest.getCourseCode());
            course.setCourseName(courseRequest.getCourseName());
            course.setDescription(courseRequest.getDescription());
            course.setInstructor(courseRequest.getInstructor());
            updateCourse = courseRepository.save(course);
        }else{
            throw new RuntimeException("CourseId not found");
        }
        return updateCourse.toCourseDto();
    }
}
