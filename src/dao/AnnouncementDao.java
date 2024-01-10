package dao;

import models.Announcement;

public interface AnnouncementDao {
    boolean save (Announcement announcement);
    Announcement getById (Long id);
    boolean update (Announcement announcement);
    boolean delete (Long id);
    Announcement findByAnonsimentName (String name);
}
