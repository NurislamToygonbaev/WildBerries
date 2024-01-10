package service;

import models.Users;

public interface UserService {
    String add(Users users);

    Users logIn(String log, String pass);
}
