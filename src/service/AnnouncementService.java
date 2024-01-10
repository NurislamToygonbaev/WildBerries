package service;

import models.Announcement;

public interface AnnouncementService {
    boolean save (Announcement announcement);
    Announcement getById (Long id);
    boolean update (Announcement announcement);
    boolean delete (Long id);
}
