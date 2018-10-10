package forum.service;

import forum.domain.UserLoginLog;


public interface LoginLogService {
    /**
     * 获取所有的登录日志
     */
    void listAllUserLoginLog();

    /**
     * 添加登录日志
     */
    void addUserLoginLog(UserLoginLog userLoginLog);
}
