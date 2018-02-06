package com.lv.service.user;

import com.github.pagehelper.Page;
import com.lv.model.User;

import java.util.List;

/**
 * Created by zhangshanmin on 2016/2/14.
 */
public interface IUserService {

    public void addUser(User user);

    public List<User> getUsers();

    public List<User> getUsersByPage(int pageNumber,int pageSize);

    public Page<User> queryUsersByPage(int pageNumber,int pageSize);


}

