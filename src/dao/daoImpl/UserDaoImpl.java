package dao.daoImpl;

import dao.UserDao;
import database.DataBase;
import exception.MyException;
import models.Users;

import java.util.List;

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
        for (Users users : dataBase.getAll()) {
            if (users.getId().equals(id)){
                return users;
            }
        }
        throw new MyException("The user under the id: "+id+" was not found.");
    }

    @Override
    public boolean update(Users user, Long id) {
        for (Users users : dataBase.getAll()) {
            if (users.getId().equals(id)){
                users.setFirstName(user.getFirstName());
                users.setEmail(user.getEmail());
                users.setPassword(user.getPassword());
                return true;
            }
        }
        throw new MyException("User not fount.");
    }

    @Override
    public boolean delete(Long id) {
        for (Users users : dataBase.getAll()) {
            if (users.getId().equals(id)){
                return dataBase.remove(users);
            }
        }
        throw new MyException("The user under the id: "+id+" was not found.");
    }

    @Override
    public List<Users> getAll() {
        return dataBase.getAll();
    }
}
