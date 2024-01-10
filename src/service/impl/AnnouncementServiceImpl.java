package service.impl;

import dao.AnnouncementDao;
import models.Announcement;
import service.AnnouncementService;

public class AnnouncementServiceImpl implements AnnouncementService {
    private final AnnouncementDao announcementDao;

    public AnnouncementServiceImpl(AnnouncementDao announcementDao) {
        this.announcementDao = announcementDao;
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
