package service.impl;

import dao.AnnouncementDao;
import service.AnnouncementService;

public class AnnouncementServiceImpl implements AnnouncementService {
    private final AnnouncementDao announcementDao;

    public AnnouncementServiceImpl(AnnouncementDao announcementDao) {
        this.announcementDao = announcementDao;
    }
}
