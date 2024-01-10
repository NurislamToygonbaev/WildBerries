package database;

import enums.Rol;
import models.Users;

import java.util.ArrayList;
import java.util.List;

public class DataBase {
    private final List<Users> users = new ArrayList<>();
    public List<Users> getAll (){
        return users;
    }
    public boolean save (Users user){
        return users.add(user);
    }
    public boolean remove (Users user){
        return users.remove(user);
    }
}
