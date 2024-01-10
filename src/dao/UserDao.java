package dao;

import models.Users;

import java.util.List;

public interface UserDao {
    boolean save (Users user);
    Users getById (Long id);
    boolean update (Users user, Long id);
    boolean delete (Long id);
    List<Users> getAll();
}
