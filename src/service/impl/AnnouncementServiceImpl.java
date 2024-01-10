package service.impl;

import dao.AnnouncementDao;
import exception.MyException;
import models.Announcement;
import service.AnnouncementService;

import java.util.List;
import java.util.logging.Logger;

public class AnnouncementServiceImpl implements AnnouncementService {
    private final AnnouncementDao announcementDao;

    public AnnouncementServiceImpl(AnnouncementDao announcementDao) {
        this.announcementDao = announcementDao;
    }

    @Override
    public String save(Announcement announcement) {
        try {
            return announcementDao.save(announcement);
        } catch (MyException e) {
            return e.getMessage();
        }
    }

    @Override
    public Announcement getById(Long id) {
        try {
            return announcementDao.getById(id);
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String update(Announcement announcement, Long id) {
        try {
            return announcementDao.update(announcement, id);
        } catch (MyException e) {
            return e.getMessage();
        }
    }

    @Override
    public String delete(Long id) {
        try {
            return announcementDao.delete(id);
        } catch (MyException e) {
            return e.getMessage();
        }
    }

    @Override
    public List<Announcement> findAnnouncementByName(String name) {
        return announcementDao.findAnnouncementByName(name);
    }

    @Override
    public List<Announcement> getAllAnnouncement() {
        return announcementDao.getAllAnnouncement();
    }
}
