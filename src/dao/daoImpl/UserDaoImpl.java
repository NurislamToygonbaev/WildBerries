package dao.daoImpl;

import dao.UserDao;
import database.DataBase;
import models.Users;

public class UserDaoImpl implements UserDao {
    private final DataBase dataBase;

    public UserDaoImpl(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public boolean save(Users user) {
        return dataBase.save(user);
    }

    @Override
    public Users getById(Long id) {
        return null;
    }

    @Override
    public boolean update(Users user) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
