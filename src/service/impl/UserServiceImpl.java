package service.impl;

import dao.daoImpl.UserDaoImpl;
import service.UserService;

public class UserServiceImpl implements UserService {

    private final UserDaoImpl userDao;

    public UserServiceImpl(UserDaoImpl userDao) {
        this.userDao = userDao;
    }
}
