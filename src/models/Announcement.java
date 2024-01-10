package models;

public class Announcement {
    private Long id;
    private String name;
    private String description;
    private double price;
    private Users owner;
    private Favorite favorites;

    public Announcement(){}
    public Announcement(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Users getOwner() {
        return owner;
    }

    public void setOwner(Users owner) {
        this.owner = owner;
    }

    public Favorite getFavorites() {
        return favorites;
    }

    public void setFavorites(Favorite favorites) {
        this.favorites = favorites;
    }

    @Override
    public String toString() {
        return "Announcement: " +
                "id = " + id +
                ", name = " + name  +
                ", description = " + description  +
                ", price = " + price +
                ", owner = " + owner +
                ", favorites = " + favorites + "\n";
    }
}
