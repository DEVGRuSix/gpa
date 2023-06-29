package cuz.gpaweb.controller;

import com.google.gson.Gson;
import cuz.gpaweb.mapper.StudentMapper;
import cuz.gpaweb.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class StudentController {

    @Autowired
    private StudentMapper studentMapper;
    private Gson gson = new Gson();


    @GetMapping("/student")
    public String getStudentList(){
        List<Student> studentList=studentMapper.selectList(null);
        return gson.toJson(studentList);
    }
    @PostMapping("/addstudent")
    public void AddStudent(@RequestBody Student student) {
        studentMapper.insert(student);
    }

    @DeleteMapping("/delstudent")
    public void DeleteStudent(@RequestBody Student student) {
        studentMapper.deleteById(student);
    }

    @PutMapping("/updatestudent")
    public void UpdateStudent(@RequestBody Student student) {
        studentMapper.updateById(student);
    }
}
