package forum.service.impl;

import forum.dao.UserLoginLogDao;
import forum.domain.UserLoginLog;
import forum.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LoginLogServiceImpl implements LoginLogService {
    private final UserLoginLogDao userLoginLogDao;

    @Autowired
    public LoginLogServiceImpl(UserLoginLogDao userLoginLogDao) {
        this.userLoginLogDao = userLoginLogDao;
    }

    @Override
    public void listAllUserLoginLog() { }

    @Override
    public void addUserLoginLog(UserLoginLog userLoginLog) {
        userLoginLogDao.addUserLoginLog(userLoginLog);
    }
}
