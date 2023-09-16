package com.example.model.entity;


import com.example.dto.CourseDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Integer id;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "course_code")
    private String courseCode;

    @Column(name = "description")
    private String description;

    @Column(name = "instructor")
    private String instructor;

    public CourseDTO toCourseDto(){
        return new CourseDTO(
                this.id,
                this.courseName,
                this.courseCode,
                this.description,
                this.instructor
        );
    }

}
