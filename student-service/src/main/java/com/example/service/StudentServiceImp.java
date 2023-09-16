package com.example.service;


import com.example.dto.CourseDTO;
import com.example.model.dto.StudentDto;
import com.example.model.entity.Student;
import com.example.model.request.StudentRequest;
import com.example.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;


@Service


public class StudentServiceImp implements StudentService{

    private final StudentRepository studentRepository;


    public StudentServiceImp(StudentRepository studentRepository, WebClient.Builder webClientBuilder) {
        this.studentRepository = studentRepository;
    }

    WebClient webClient = WebClient.builder().baseUrl("http://localhost:8081/api/v1/courses").build();


    @Override
public StudentDto addStudent(StudentRequest studentRequest) {
    Integer courseId = studentRequest.getCourseId();

    CourseDTO courseDto = webClient
            .get()
            .uri("/{id}", courseId)
            .retrieve()
            .bodyToMono(CourseDTO.class)
            .block();

    Student student = new Student(
            null,
            studentRequest.getFirstName(),
            studentRequest.getLastName(),
            studentRequest.getEmail(),
            studentRequest.getBirthDate(),
            studentRequest.getCourseId()
    );

    return studentRepository.save(student).toStudentDto(courseDto);
}

    @Override
    public List<StudentDto> getAllStudent() {
        List<Student> students = studentRepository.findAll();

        return students.stream()
                .map(student -> {
                    CourseDTO courseDTO = webClient
                            .get()
                            .uri("/{id}",student.getCourseId())
                            .retrieve()
                            .bodyToMono(CourseDTO.class)
                            .block();
                    return new StudentDto(
                            student.getId(),
                            student.getFirstName(),
                            student.getLastName(),
                            student.getEmail(),
                            student.getBirthDate(),
                            courseDTO
                    );

                }).collect(Collectors.toList());
    }

    @Override
    public StudentDto getStudentById(Integer id) {
        Student student = studentRepository.findById(id).get();
        CourseDTO courseDTO = webClient
                .get()
                .uri("/{id}", student.getCourseId())
                .retrieve()
                .bodyToMono(CourseDTO.class)
                .block();

        return new StudentDto(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getBirthDate(),
                courseDTO
                );
    }

    @Override
    public StudentDto updateStudentById(Integer id, StudentRequest studentRequest) {
        Student student = studentRepository.findById(id).get();
        if(student == null){
            throw new RuntimeException("Student ID : "+student.getId() +  " not found");
        }

        CourseDTO courseDTO = webClient
                .get()
                .uri("/{id}", studentRequest.getCourseId())
                .retrieve()
                .bodyToMono(CourseDTO.class)
                .block();


        student.setFirstName(studentRequest.getFirstName());
        student.setLastName(studentRequest.getLastName());
        student.setEmail(studentRequest.getEmail());
        student.setBirthDate(studentRequest.getBirthDate());
        student.setCourseId(studentRequest.getCourseId());

        return studentRepository.save(student).toStudentDto(courseDTO);
    }

    @Override
    public String deleteStudentById(Integer id) {
        Student student = studentRepository.findById(id).get();
        if(student != null){
            studentRepository.deleteById(id);
            return "Successfully Delete";
        }else {
            throw new RuntimeException("Student ID : "+student.getId() +  " not found");
        }
    }
}
