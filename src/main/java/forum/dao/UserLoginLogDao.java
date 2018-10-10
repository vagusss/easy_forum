package forum.dao;

import forum.domain.UserLoginLog;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserLoginLogDao {
    /**
     * 获取所有用户的登录日志
     */
    List<UserLoginLog> listAllUserLoginLog();

    /**
     * 添加登录日志
     */
    void addUserLoginLog(UserLoginLog userLoginLog);
}
