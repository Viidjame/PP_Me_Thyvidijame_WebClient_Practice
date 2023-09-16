package com.example.model.request;

import com.example.model.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseRequest {
    private String courseName;

    private String courseCode;


    private String description;

    private String instructor;

    public Course toCourseEntity(){
        return new Course(
                null,
                this.courseName,
                this.courseCode,
                this.description,
                this.instructor
        );
    }
}
