package models;

import enums.Rol;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.List;

public class Users {
    private Long id;
    private String firstName;
    private String email;
    private String password;
    private String phoneNumber;
    private Rol role;
    private List<Announcement> announcements = new ArrayList<>();

    public Users(){

    }
    public Users(String firstName, String email, String password, Rol role) {
        this.firstName = firstName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Rol getRole() {
        return role;
    }

    public void setRole(Rol role) {
        this.role = role;
    }

    public List<Announcement> getAnnouncements() {
        return announcements;
    }

    public void setAnnouncements(List<Announcement> announcements) {
        this.announcements = announcements;
    }

    @Override
    public String toString() {
        return "Users: " +
                "id = " + id +
                ", first name = " + firstName +
                ", email = " + email +
                ", password = " + password +
                ", phone number = " + phoneNumber +
                ", role = " + role +
                " = " + announcements + "\n";
    }

    public String addAnnouncement(Announcement newAnnouncement) {
        announcements.add(newAnnouncement);
        return "success";
    }
}
