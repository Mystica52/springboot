package com.example.studentDemo.controller;

import com.example.studentDemo.exception.ResourceNotFoundException;
import com.example.studentDemo.model.Student;
import com.example.studentDemo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/students")
    public List<Student>getAllStudent(){
        return studentRepository.findAll();
    }
    //create student rest api
    @PostMapping
    public Student createStudent(@RequestBody Student student){
        return studentRepository.save(student);

    }
    // build get student by id REST api
    @GetMapping("{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable long id ){
        Student student= studentRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("student not exist with id: " + id));
        return ResponseEntity.ok(student);


    }
    //build update student rest api
    @PutMapping("{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable long id, @RequestBody Student studentDetails){
        Student updateStudent=studentRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("student not exist with id: "+ id));

        updateStudent.setName((studentDetails.getName()));
        updateStudent.setEmail((studentDetails.getEmail()));
        updateStudent.setCourse((studentDetails.getCourse()));

        studentRepository.save(updateStudent);

        return ResponseEntity.ok(updateStudent);
    }
    //build delete student REST api
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus>deleteStudent(@PathVariable long id){
        Student student=studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("student nit exist with id: " + id));

        studentRepository.delete(student);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }




}
