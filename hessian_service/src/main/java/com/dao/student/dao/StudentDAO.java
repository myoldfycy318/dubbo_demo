package com.dao.student.dao;


import com.dao.base.BaseIbatisDAO;
import com.pojo.student.Student;
import com.pojo.teacher.Teacher;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("studentDAO")
public class StudentDAO extends BaseIbatisDAO {

    public StudentDAO() {
        super("student_map");  //此处需跟实体xml中的namespace保持一致
    }

	public Student selectStuById(Student student) {
    	return super.getOne("selectStuById",student);
    }

    public Student selectStuAndProfile(Student student) {
    	return super.getOne("selectStuAndProfile",student);
    }

}