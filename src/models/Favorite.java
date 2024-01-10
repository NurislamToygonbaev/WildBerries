package models;

public class Favorite {
    private Long id;
    private Users users;
    private Announcement announcements;

    public Favorite(Users users, Announcement announcements) {
        this.users = users;
        this.announcements = announcements;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Announcement getAnnouncements() {
        return announcements;
    }

    public void setAnnouncements(Announcement announcements) {
        this.announcements = announcements;
    }

    @Override
    public String toString() {
        return "Favorite: " +
                "id               =  " + id +
                "users            =  " + users +
                "announcements    =  " + announcements +"\n";
    }
}
