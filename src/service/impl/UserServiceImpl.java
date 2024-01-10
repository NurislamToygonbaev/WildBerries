package service.impl;

import dao.daoImpl.UserDaoImpl;
import exception.MyException;
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

    @Override
    public Users logIn(String log, String pass) {
        try {
            for (Users users : userDao.getAll()) {
                if (users.getEmail().equalsIgnoreCase(log) && users.getPassword().equals(pass)){
                    return users;
                }
            }
        } catch (MyException e){
            System.out.println("Invalid email or password");
        }
        return null;
    }
}
