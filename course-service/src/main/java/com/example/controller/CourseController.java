package com.example.controller;

import com.example.dto.CourseDTO;
import com.example.model.request.CourseRequest;
import com.example.service.CourseServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/courses")
public class CourseController {
    private final CourseServiceImp courseServiceImp;

    @PostMapping("")
    public ResponseEntity<CourseDTO> createCourse(@RequestBody CourseRequest courseRequest){
        CourseDTO courseDto = courseServiceImp.createCourse(courseRequest);
        return ResponseEntity.ok(courseDto);
    }

    @GetMapping("")
    public ResponseEntity<List<CourseDTO>> getAllCourses(){
        List<CourseDTO> courseDtoList = courseServiceImp.getAllCourses();
        return ResponseEntity.ok(courseDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> searchCourseById(@PathVariable Integer id){
        CourseDTO courseDto = courseServiceImp.findCourseById(id);
        return ResponseEntity.ok(courseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourseById(@PathVariable Integer id){
        courseServiceImp.deleteCourseById(id);
        return ResponseEntity.ok("You have successfully delete course by id");
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> updateCourseById(@PathVariable Integer id, @RequestBody CourseRequest courseRequest){
        CourseDTO courseDto = courseServiceImp.updateCourseById(id,courseRequest);
        return ResponseEntity.ok(courseDto);
    }
}
