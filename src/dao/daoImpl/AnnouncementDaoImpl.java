package dao.daoImpl;

import dao.AnnouncementDao;
import database.DataBase;
import exception.MyException;
import models.Announcement;
import models.Users;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AnnouncementDaoImpl implements AnnouncementDao {
    private final DataBase dataBase;

    public AnnouncementDaoImpl(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public String save(Announcement announcement) {
        for (Users users:dataBase.getAll()){
            users.getAnnouncements().add(announcement);
            return "successfully saved";
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
    public String update(Announcement announcement, Long id) {
        for (Users users : dataBase.getAll()) {
            for (Announcement usersAnnouncement : users.getAnnouncements()) {
                if (usersAnnouncement.getId().equals(id)){
                    usersAnnouncement.setName(announcement.getName());
                    usersAnnouncement.setDescription(announcement.getDescription());
                    usersAnnouncement.setPrice(announcement.getPrice());
                    return "successfully updated";
                }
            }
        }
        throw new MyException("error");
    }

    @Override
    public String delete(Long id) {
        for (Users users:dataBase.getAll()){
            for (Announcement announcement:users.getAnnouncements()){
                if (announcement.getId().equals(id)){
                    users.getAnnouncements().remove(announcement);
                    return "successfully updated";
                }
            }
        }
        throw new MyException("announcement with id: "+id+ " not found!");
    }

    @Override
    public List<Announcement> findAnnouncementByName(String name) {
        List<Announcement> announcements = new ArrayList<>();
        for (Users users:dataBase.getAll()){
            for (Announcement announcement:users.getAnnouncements()){
                if (announcement.getName().equals(name)){
                    announcements.add(announcement);
                }
            }
        }
        return announcements;
    }

    @Override
    public List<Announcement> getAllAnnouncement() {
        List<Announcement> announcements = new ArrayList<>();
        for (Users users : dataBase.getAll()) {
            announcements.addAll(users.getAnnouncements());
        }
        return announcements;
    }
}
