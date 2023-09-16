package com.example.model.request;



import com.example.model.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentRequest {
    private String firstName;
    private String lastName;
    private String email;

    private LocalDateTime birthDate;

    private Integer courseId;

    public Student toStudentEntity(){
        return new Student(null, firstName,lastName,email,birthDate,courseId);
    }
}
