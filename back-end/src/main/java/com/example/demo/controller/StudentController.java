package com.example.demo.controller;

import com.example.demo.Entity.Student;
import com.example.demo.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class StudentController {
@Autowired
StudentMapper studentMapper;

@GetMapping("/list")
public List<Student> list(){
    List<Student> students = studentMapper.selectList(null);
    return students;
}
@GetMapping("/get")
    public Student get(int id){
    Student student = studentMapper.selectById(id);
        return student;
}
@PostMapping("/add")
    public void add(@RequestBody Student student){
    studentMapper.insert(student);
    }
    @PatchMapping("/update")
    public void update(@RequestBody Student student){
    studentMapper.updateById(student);
    }
    @DeleteMapping("/delete")
    public void delete(@RequestParam int id){
    studentMapper.deleteById(id);
    }



}
