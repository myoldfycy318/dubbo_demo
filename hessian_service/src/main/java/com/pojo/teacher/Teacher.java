package com.pojo.teacher;

import com.pojo.student.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangshanmin on 2016/3/16.
 */
public class Teacher {

     private Integer id;

    private String name;

    private String gender;

    private String researchArea;

    private String title;

    /**一对多**/
    private List<Student> students = new ArrayList<Student>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getResearchArea() {
        return researchArea;
    }

    public void setResearchArea(String researchArea) {
        this.researchArea = researchArea;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
