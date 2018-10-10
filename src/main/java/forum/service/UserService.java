package forum.service;

import forum.domain.User;

import java.util.List;


public interface UserService {
    /**
     * 添加用户
     */
    void addUser(User user);

    /**
     * 更新用户信息
     */
    void updateUserByUserName(User user);

    /**
     * 通过用户名查找用户
     */
    User getUserByUserName(String userName);

    /**
     * 通过用户名删除用户
     */
    void deleteUserByUserName(String userName);

    /**
     * 获取指定用户的密码
     */
    String getPassword(String userName);

    /**
     * 获取所有用户
     */
    List<User> getAllUser();
}
