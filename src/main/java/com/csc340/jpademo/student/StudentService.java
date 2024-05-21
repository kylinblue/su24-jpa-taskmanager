package com.csc340.jpademo.student;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public Object getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(int id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Object getHonorsStudents(double gpa){
        return studentRepository.getHonorsStudents(gpa);
    }

    public void deleteStudentById(int id) {
        studentRepository.deleteById(id);
    }

    public void addNewStudent(Student student) {
        studentRepository.save(student);
    }
}

