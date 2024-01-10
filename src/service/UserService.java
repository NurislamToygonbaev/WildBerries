package service;

import models.Users;

import java.util.List;

public interface UserService {
    String add(Users users);

    Users logIn(String log, String pass);
    Users getById (Long id);
    String update (Users user);
    String delete (Long id);
    List<Users> getAll();
}
