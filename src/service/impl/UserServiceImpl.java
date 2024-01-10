package service.impl;

import dao.daoImpl.UserDaoImpl;
import models.Users;
import service.UserService;

public class UserServiceImpl implements UserService {

    private final UserDaoImpl userDao;

    public UserServiceImpl(UserDaoImpl userDao) {
        this.userDao = userDao;
    }

    @Override
    public String add(Users users) {
        userDao.save(users);
        return "Successfully saved";
    }
}
