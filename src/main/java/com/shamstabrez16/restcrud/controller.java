package com.shamstabrez16.restcrud;

import com.shamstabrez16.restcrud.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class controller {

    private final StudentService service;
        @Autowired
    public controller(StudentService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Student>> getAllStudents(){
        return ResponseEntity.ok(service.getAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable long id){
        return ResponseEntity.ok(service.getStudentById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteStudentById(@PathVariable long id){
        return ResponseEntity.ok(service.deleteStudentById(id));
    }

    @PutMapping()
    public ResponseEntity<Student> updateStudent(@RequestBody Student student){
        return ResponseEntity.ok(service.updateStudent(student));
    }

    @PostMapping
    public ResponseEntity<Student> saveStudent(@RequestBody Student student){
        return ResponseEntity.ok(service.saveStudent(student));
    }
}
