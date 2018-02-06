package com.dao.teacher.dao;


import com.dao.base.BaseIbatisDAO;
import com.pojo.convert.ConvertOrder;
import com.pojo.teacher.Teacher;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository("teacherDAO")
public class TeacherDAO extends BaseIbatisDAO {

    public TeacherDAO() {
        super("teacher_map");  //此处需跟实体xml中的namespace保持一致
    }

	public List<Teacher> selectTeacherById(Teacher teacher) {
    	return super.getList("selectTeacherById", teacher);
    }

}