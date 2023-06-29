package cuz.gpaweb.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.gson.Gson;
import cuz.gpaweb.mapper.CourseMapper;
import cuz.gpaweb.mapper.ScoreMapper;
import cuz.gpaweb.mapper.StudentMapper;
import cuz.gpaweb.model.Course;
import cuz.gpaweb.model.Score;
import cuz.gpaweb.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
public class GpaController {
    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private ScoreMapper scoreMapper;

    private Gson gson = new Gson();
    @GetMapping("/gpa")
    public String getGpaList(){

        List<Student> studentList=studentMapper.selectList(null);
        for(Student s :studentList){
            String sid = s.getId();

            QueryWrapper<Score> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("sid",sid);
            List<Score> scoreList=scoreMapper.selectList(queryWrapper);

            double sumPoint = 0;
            double sumCredit = 0;

            for(Score sc:scoreList){
                String cid=sc.getCid();
                Course c =courseMapper.selectById(cid);
                double credit = c.getCredit();
                double score = sc.getScore();
                double point = score / 10 - 5.0;
                if (score<60){
                    point = 0;
                }
                sumPoint += point * credit;
                sumCredit += credit;
            }
            double gpa = sumPoint / sumCredit;
            s.setGpa(gpa);

            studentMapper.updateById(s);
        }
        return gson.toJson(studentList);
    }

}
