package com.csc340.jpademo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("/all")
    public String getAllStudents(Model model) {
        model.addAttribute("studentList", studentService.getAllStudents());
        return "student-list";
    }

    @GetMapping("/{id}")
    public String getStudentById(@PathVariable int id, Model model) {
        model.addAttribute("student", studentService.getStudentById(id));
        return "student-detail";
    }

    @PostMapping("/create")
    public String addNewStudent(Student student) {
        studentService.addNewStudent(student);
        return "redirect:/students/all";
    }

    @PostMapping("/update")
    public String updateStudent(Student student) {
        studentService.addNewStudent(student);
        return "redirect:/students/" + student.getId();
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable int id, Model model) {
        model.addAttribute("student", studentService.getStudentById(id));
        return "student-update";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudentById(@PathVariable int id) {
        studentService.deleteStudentById(id);
        return "redirect:/students/all";
    }

}
