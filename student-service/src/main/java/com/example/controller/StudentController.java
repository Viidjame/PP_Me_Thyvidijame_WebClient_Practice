package com.example.controller;

import com.example.model.dto.StudentDto;
import com.example.model.request.StudentRequest;
import com.example.response.ApiResponse;
import com.example.service.StudentServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/students")
public class StudentController {
    private final StudentServiceImp studentServiceImp;

    @PostMapping("")
    public ResponseEntity<ApiResponse<StudentDto>> addNewStudent(@RequestBody StudentRequest studentRequest){
        ApiResponse<StudentDto> apiResponse = ApiResponse.<StudentDto>builder()
                .message("You have successfully created new student")
                .status(HttpStatus.CREATED.value())
                .createAt(LocalDateTime.now())
                .payload(studentServiceImp.addStudent(studentRequest))
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("")
    public ResponseEntity<ApiResponse<List<StudentDto>>> getAllStudent(){
        ApiResponse<List<StudentDto>> apiResponse = ApiResponse.<List<StudentDto>>builder()
                .message("You have successfully fetched all students")
                .status(HttpStatus.OK.value())
                .createAt(LocalDateTime.now())
                .payload(studentServiceImp.getAllStudent())
                .build();
        return ResponseEntity.ok(apiResponse);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<StudentDto>> getStudentById(@PathVariable Integer id){
        ApiResponse<StudentDto> apiResponse = ApiResponse.<StudentDto>builder()
                .message("You have successfully found student by id")
                .status(HttpStatus.OK.value())
                .createAt(LocalDateTime.now())
                .payload(studentServiceImp.getStudentById(id))
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<StudentDto>> updateStudentById(@PathVariable Integer id, @RequestBody StudentRequest studentRequest){
        ApiResponse<StudentDto> apiResponse = ApiResponse.<StudentDto>builder()
                .message("You have successfully updated student by id")
                .status(HttpStatus.OK.value())
                .createAt(LocalDateTime.now())
                .payload(studentServiceImp.updateStudentById(id,studentRequest))
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteStudentById(@PathVariable Integer id){
        studentServiceImp.deleteStudentById(id);
        ApiResponse<String> apiResponse = ApiResponse.<String>builder()
                .message("You have successfully deleted student by id ")
                .status(HttpStatus.OK.value())
                .createAt(LocalDateTime.now())
                .payload(null)
                .build();
        return ResponseEntity.ok(apiResponse);
    }
}
