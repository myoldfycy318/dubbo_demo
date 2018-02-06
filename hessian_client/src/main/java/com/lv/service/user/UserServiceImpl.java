package com.lv.service.user;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lv.dao.mapper.UserMapper;
import com.lv.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhangshanmin on 2016/2/14.
 */
@Service("userService")
public class UserServiceImpl implements IUserService {

    @Resource
    private UserMapper userMapper;

    @Override
    @Transactional
    public void addUser(User user) {
        userMapper.addUser(user);
        throw new RuntimeException();
    }

    @Override
    public List<User> getUsers() {
        return userMapper.getUsers();
    }


    @Override
    public List<User> getUsersByPage(int pageNumber, int pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
        Page<User> userPage = (Page<User>) userMapper.getUsers();
        return userPage;
    }

    @Override
    public Page<User> queryUsersByPage(int pageNumber, int pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
        Page<User> userPage = (Page<User>) userMapper.getUsers();
        return userPage;
    }


}
