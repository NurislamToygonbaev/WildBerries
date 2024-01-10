package dao;

import models.Announcement;

import java.util.List;

public interface AnnouncementDao {
    boolean save (Announcement announcement);
    Announcement getById (Long id);
    boolean update (Announcement announcement, Long id);
    boolean delete (Long id);
    List<Announcement> findAnnouncementByName (String name);
}
