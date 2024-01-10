package dao;

import models.Users;

import java.util.List;

public interface UserDao {
    String save (Users user);
    Users getById (Long id);
    String update (Users user);
    String delete (Long id);
    List<Users> getAll();
}
