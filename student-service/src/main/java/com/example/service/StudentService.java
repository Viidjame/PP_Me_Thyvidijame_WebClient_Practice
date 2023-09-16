package com.example.service;


import com.example.model.dto.StudentDto;
import com.example.model.request.StudentRequest;

import java.util.List;


public interface StudentService {

  StudentDto addStudent(StudentRequest studentRequest);

  List<StudentDto> getAllStudent();

  StudentDto getStudentById(Integer id);

  StudentDto updateStudentById(Integer id, StudentRequest studentRequest);

  String deleteStudentById(Integer id);
}
