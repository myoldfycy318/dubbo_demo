package com.pojo.student;

import com.alibaba.fastjson.JSONObject;
import com.pojo.profile.Profile;
import com.pojo.teacher.Teacher;

/**
 * Created by zhangshanmin on 2016/3/16.
 */
public class Student {

    private Integer id;

    private String name;

    private String gender;

    private String major;

    private String grade;

    /**多对一：多个学生对应一个Teacher **/
    private Teacher teacher;

    /** profile 与 student 一对一**/
    private Profile profile;

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

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
