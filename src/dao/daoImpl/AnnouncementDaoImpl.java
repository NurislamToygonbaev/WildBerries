package dao.daoImpl;

import dao.AnnouncementDao;
import database.DataBase;
import exception.MyException;
import models.Announcement;
import models.Users;

import java.util.Optional;

public class AnnouncementDaoImpl implements AnnouncementDao {
    private final DataBase dataBase;

    public AnnouncementDaoImpl(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public boolean save(Announcement announcement) {
        for (Users users:dataBase.getAll()){
           return users.getAnnouncements().add(announcement);
        }
        throw new MyException("error!");
    }

    @Override
    public Announcement getById(Long id){
        for (Users users:dataBase.getAll()){
            for (Announcement announcement:users.getAnnouncements()){
                if (announcement.getId().equals(id)){
                    return announcement;
                }
            }

        }
        throw new MyException("announcement woth id: "+id+ " not found!");
    }

    @Override
    public boolean update(Announcement announcement) {
        for (Users users : dataBase.getAll()) {
            for (Announcement usersAnnouncement : users.getAnnouncements()) {
                usersAnnouncement.setName(announcement.getName());
                usersAnnouncement.setDescription(announcement.getDescription());
                usersAnnouncement.setPrice(announcement.getPrice());
                return true;
            }
        }
        throw new MyException("error");
    }

    @Override
    public boolean delete(Long id) {
        for (Users users:dataBase.getAll()){
            for (Announcement announcement:users.getAnnouncements()){
                if (announcement.getId().equals(id)){
                   return users.getAnnouncements().remove(announcement);
                }
            }
        }
        throw new MyException("announcement woth id: "+id+ " not found!");
    }

    @Override
    public Announcement findByAnonsimentName(String name) {
        for (Users users:dataBase.getAll()){
            for (Announcement announcement:users.getAnnouncements()){
                if (announcement.getName().equals(name)){
                    return announcement;
                }
            }
        }
        throw new MyException("announcement with name: "+name+ " not found!");
    }
}
