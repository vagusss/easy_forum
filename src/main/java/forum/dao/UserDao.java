package forum.dao;

import forum.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserDao {
    /**
     * 通过用户名查找用户
     */
    User findUserByUserName(String username);

    /**
     * 通过用户 id 查找用户
     */
    User findUserByUserId(int id);

    /**
     * 添加用户
     */
    void addUser(User user);

    /**
     * 通过用户名删除用户
     */
    void deleteUserByUserName(String username);

    /**
     * 更新用户信息
     */
    void updateUserByUserName(User user);

    /**
     * 用户名获取用户密码
     */
    String getUserPasswordByUserName(String username);

    /**
     * 获取所有的用户信息
     */
    List<User> getAllUserInfo();
}
