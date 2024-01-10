package dao;

import models.Users;

public interface UserDao {
    boolean save (Users user);
    Users getById (Long id);
    boolean update (Users user);
    boolean delete (Long id);
}
