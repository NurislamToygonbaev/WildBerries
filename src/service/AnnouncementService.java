package service;

import models.Announcement;

import java.util.List;

public interface AnnouncementService {
    String save (Announcement announcement);
    Announcement getById (Long id);
    String update (Announcement announcement, Long id);
    String delete (Long id);
    List<Announcement> findAnnouncementByName(String name);
    List<Announcement> getAllAnnouncement();
}
