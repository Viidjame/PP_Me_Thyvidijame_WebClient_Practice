package com.example.model.entity;


import com.example.dto.CourseDTO;
import com.example.model.dto.StudentDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "birth_date")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime birthDate;

    private Integer courseId;

    public StudentDto toStudentDto(CourseDTO courseDTO){
        return new StudentDto(
                this.id,
                this.firstName,
                this.lastName,
                this.email,
                this.birthDate,
                courseDTO
        );
    }
}
