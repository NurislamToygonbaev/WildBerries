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
    public String save(Users user) {
        dataBase.save(user);
        return "successfully saved";
    }

    @Override
    public Users getById(Long id) {
        for (Users users : dataBase.getAll()) {
            if (users.getId().equals(id)) {
                return users;
            }
        }
        throw new MyException("The user under the id: " + id + " was not found.");
    }

    @Override
    public String update(Users user) {
        for (Users currentUser : dataBase.getAll()) {
            if (currentUser.getEmail().equalsIgnoreCase(user.getEmail())){
                currentUser.setFirstName(user.getFirstName());
                currentUser.setPassword(user.getPassword());
                return "successfully updated";
            }
        }
        throw new MyException("User not found.");
    }

    @Override
    public String delete(Long id) {
        for (Users users : dataBase.getAll()) {
            if (users.getId().equals(id)) {
                dataBase.getAll().remove(users);
                return "successfully deleted";
            }
        }
        throw new MyException("The user under the id: " + id + " was not found.");
    }

    @Override
    public List<Users> getAll() {
        return dataBase.getAll();
    }
}
