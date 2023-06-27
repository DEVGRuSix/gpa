package cuz.gpaweb.controller;
import com.google.gson.Gson;
import cuz.gpaweb.mapper.StudentMapper;
import cuz.gpaweb.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentMapper studentMapper;
    private Gson gson = new Gson();


    @GetMapping("/student")
    public String getStudentList(){
        List<Student> studentList=studentMapper.selectList(null);
        return gson.toJson(studentList);
    }

}
