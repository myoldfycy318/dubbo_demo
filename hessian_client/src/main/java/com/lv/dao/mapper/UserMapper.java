/**
 *
 */
package com.lv.dao.mapper;

import com.lv.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value="userMapper")
public interface UserMapper {

    public User findUserByName(@Param("username") String username);

    public String getUsernameById(@Param("id") int id);

    public List<User> getUsers();

    public Long getDatagridTotal(User user);

    public void addUser(User user);

    public void editUser(User user);

}
