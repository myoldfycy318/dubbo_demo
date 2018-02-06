package com.pojo.profile;

import com.pojo.student.Student;

/**
 * Created by zhangshanmin on 2016/3/16.
 */
public class Profile {

    private Integer id;

    private String phoneNum;

    private String eMail ;

    /** profile 与 student  一对一**/
    private Student student;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
