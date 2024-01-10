package dao.daoImpl;

import dao.UserDao;
import models.Users;

public class UserDaoImpl implements UserDao {
    @Override
    public boolean save(Users user) {
        return false;
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
