package dao.daoImpl;

import dao.AnnouncementDao;
import database.DataBase;
import models.Announcement;

public class AnnouncementDaoImpl implements AnnouncementDao {
    private final DataBase dataBase;

    public AnnouncementDaoImpl(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public boolean save(Announcement announcement) {
        return false;
    }

    @Override
    public Announcement getById(Long id) {
        return null;
    }

    @Override
    public boolean update(Announcement announcement) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
