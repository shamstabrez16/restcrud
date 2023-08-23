package com.shamstabrez16.restcrud;

import com.shamstabrez16.restcrud.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(long id) {
        return studentRepository.findOne(id);
    }

    public Boolean deleteStudentById(long id) {
        return studentRepository.delete(id);
    }

    public Student updateStudent(Student student) {
        return studentRepository.update(student);
    }

    public Student saveStudent(Student student) {
        return studentRepository.saveAndReturnId(student);
    }
}
