package service.impl;

import dao.daoImpl.UserDaoImpl;
import exception.MyException;
import models.Users;
import service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserDaoImpl userDao;

    public UserServiceImpl(UserDaoImpl userDao) {
        this.userDao = userDao;
    }

    @Override
    public String add(Users users) {
       return userDao.save(users);
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

    @Override
    public Users getById(Long id) {
        try {
            return userDao.getById(id);
        } catch (MyException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String update(Users user) {
        try {
            return userDao.update(user);
        } catch (MyException e){
            return e.getMessage();
        }
    }

    @Override
    public String delete(Long id) {
        try {
            return userDao.delete(id);
        } catch (MyException e){
            return e.getMessage();
        }
    }

    @Override
    public List<Users> getAll() {
        return userDao.getAll();
    }
}
