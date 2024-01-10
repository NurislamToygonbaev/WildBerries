import dao.daoImpl.AnnouncementDaoImpl;
import dao.daoImpl.FavoriteDaoImpl;
import dao.daoImpl.UserDaoImpl;
import database.DataBase;
import enums.Rol;
import generator.MyGeneratorId;
import models.Announcement;
import models.Users;
import service.AnnouncementService;
import service.FavoriteService;
import service.UserService;
import service.impl.AnnouncementServiceImpl;
import service.impl.FavoriteServiceImpl;
import service.impl.UserServiceImpl;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Scanner scanForStr = new Scanner(System.in);
        Scanner scanForLong = new Scanner(System.in);

        DataBase dataBase = new DataBase();
        dataBase.getAll().add(new Users("Adminushka", "Admin@gmail.com", "admin123", Rol.ADMIN));
        UserService userService = new UserServiceImpl(new UserDaoImpl(dataBase));
        AnnouncementService announcementService = new AnnouncementServiceImpl(new AnnouncementDaoImpl(dataBase));
        FavoriteService favoriteService = new FavoriteServiceImpl(new FavoriteDaoImpl(dataBase));
        Users currentUser = null;
        OuterLoop:
        while (true) {
            try {
                menu();
                switch (scanner.nextInt()) {
                    case 0 -> {
                        break OuterLoop;
                    }
                    case 1 -> {
                        Users users = new Users();
                        Users register = register(users, dataBase);
                        System.out.println(userService.add(register));
                    }
                    case 2 -> {
                        System.out.print("enter email: ");
                        String log = scanForStr.nextLine();
                        System.out.print("enter password: ");
                        String pass = scanForStr.nextLine();
                        currentUser = userService.logIn(log, pass);
                        if (currentUser != null) {
                            if (currentUser.getRole().equals(Rol.USER)) {
                                System.out.println("~ Welcome to WildBerries: ~ "+ currentUser.getFirstName());
                                innerLoopUser:
                                while (true) {
                                    menuUser();
                                    switch (scanner.nextInt()) {
                                        case 0 -> {break innerLoopUser;}
                                        case 1 -> System.out.println(announcementService.getAllAnnouncement());
                                        case 2 -> {
                                            System.out.print("enter name of announcement: ");
                                            String name = scanForStr.nextLine();
                                            System.out.println(announcementService.findAnnouncementByName(name));
                                        }
                                        case 3 -> {
                                            System.out.print("enter id of announcement: ");
                                            Long id = scanForLong.nextLong();
                                            System.out.println(announcementService.getById(id));
                                        }
                                        case 4 -> {
                                        }
                                        case 5 -> {
                                            Users users = new Users();
                                            Users newUser = updateUser(users);
                                            currentUser.setFirstName(newUser.getFirstName());
                                            currentUser.setPhoneNumber(newUser.getPhoneNumber());
                                            currentUser.setPassword(newUser.getPassword());
                                            System.out.println(userService.update(currentUser));
                                        }
                                        default -> System.out.println("enter correct choice");
                                    }
                                }
                            } else if (currentUser.getRole().equals(Rol.VENDOR)) {
                                System.out.println("~ Welcome to WildBerries: ~ "+ currentUser.getFirstName());
                                innerLoopVendor:
                                while (true) {
                                    menuVendor();
                                    switch (scanner.nextInt()) {
                                        case 0 -> {
                                            break innerLoopVendor;
                                        }
                                        case 1 -> {
                                            Announcement announcement = new Announcement();
                                            Announcement newAnnouncement = createAnnouncement(announcement);
                                            newAnnouncement.setOwner(currentUser.getFirstName());
                                            System.out.println(currentUser.addAnnouncement(newAnnouncement));
                                        }
                                        case 2 -> System.out.println(currentUser.getAnnouncements());
                                        case 3 -> {
                                            System.out.print("enter id: ");
                                            Long id = scanForLong.nextLong();
                                            Announcement announcement = new Announcement();
                                            Announcement newAnnouncement = updateAnnouncement(dataBase, id, announcement);
                                            System.out.println(announcementService.update(newAnnouncement, id));
                                        }
                                        case 4 -> {
                                            System.out.print("enter id: ");
                                            Long id = scanForLong.nextLong();
                                            System.out.println(announcementService.delete(id));
                                        }
                                        case 5 -> {
                                            Users users = new Users();
                                            Users newUser = updateUser(users);
                                            currentUser.setFirstName(newUser.getFirstName());
                                            currentUser.setPassword(newUser.getPassword());
                                            currentUser.setPhoneNumber(newUser.getPhoneNumber());
                                            System.out.println(userService.update(currentUser));
                                        }
                                        default -> System.out.println("Incorrect choice");
                                    }
                                }
                            } else if (currentUser.getRole().equals(Rol.ADMIN)) {
                                System.out.println("~ Welcome to WildBerries: ~ "+ currentUser.getFirstName());
                                innerLoopAdmin:
                                while (true){
                                    menuAdmin();
                                    switch (scanner.nextInt()){
                                        case 0 -> {break innerLoopAdmin;}
                                        case 1 -> System.out.println(userService.getAll());
                                        case 2 -> {
                                            System.out.print("Adminushka write id: ");
                                            Long id = scanForLong.nextLong();
                                            System.out.println(userService.getById(id));
                                        }
                                        case 3 -> {
                                            System.out.print("Adminushka write id: ");
                                            Long id = scanForLong.nextLong();
                                            System.out.println(userService.delete(id));
                                        }
                                        case 4 -> System.out.println(announcementService.getAllAnnouncement());
                                        case 5 -> {
                                            System.out.print("Adminushka write id: ");
                                            Long id = scanForLong.nextLong();
                                            System.out.println(announcementService.delete(id));
                                        }
                                        default -> System.out.println("Incorrect choice");
                                    }
                                }

                            }
                        }
                    }
                    default -> System.out.println("enter correct choice");
                }
            } catch (InputMismatchException e) {
                System.out.println("Incorrect, enter valid Integer");
                scanner.next();
            }
        }
    }

    private static void menu() {
        System.out.println("""
                0.  Exit
                1.  Registration
                2.  LogIn
                """);
        System.out.print("Command: ");
    }

    private static void menuUser() {
        System.out.println("""
                0.  Exit
                1.  get All announcement
                2.  get announcement by name
                3.  get announcement by id
                4.  my favorites
                5.  update my profile
                """);
        System.out.print("Command: ");
    }

    private static void menuVendor() {
        System.out.println("""
                0.  Exit
                1.  Create announcement
                2.  get all my announcement
                3.  update my announcement by id
                4.  delete announcement by id
                5.  update my profile
                """);
        System.out.print("Command: ");
    }

    private static void menuAdmin(){
        System.out.println("""
                0.  Exit
                1.  get all users
                2.  find user by id
                3.  delete user by id
                4.  get all announcement
                5.  delete announcement
                """);
        System.out.print("Command: ");
    }

    private static Users register(Users users, DataBase dataBase) {
        Scanner scanner = new Scanner(System.in);
        users.setId(MyGeneratorId.getIdUser());
        while (true) {
            System.out.print("enter user name: ");
            String name = scanner.nextLine();
            if (!name.isEmpty()) {
                users.setFirstName(name);
                break;
            } else System.out.println("Incorrect name");
        }
        while (true) {
            System.out.print("enter email: ");
            String email = scanner.nextLine();
            if (email.endsWith("@gmail.com") && email.length() > 11 && checkUniq(email, dataBase)) {
                users.setEmail(email);
                break;
            } else System.out.println("Incorrect email, use domain @gmail.com");
        }
        while (true) {
            System.out.print("enter password: ");
            String pass = scanner.nextLine();
            if (pass.length() > 4) {
                users.setPassword(pass);
                break;
            } else System.out.println("write more 4 symbol");
        }
        while (true){
            System.out.print("enter phone number: ");
            String number = scanner.nextLine();
            if (number.length() == 13 && number.startsWith("+996")){
                users.setPhoneNumber(number);
                break;
            } else System.out.println("write correct number");
        }
        while (true) {
            System.out.print("enter role (user|vendor) : ");
            String role = scanner.nextLine();
            if (role.equalsIgnoreCase("user")) {
                users.setRole(Rol.USER);
                break;
            } else if (role.equalsIgnoreCase("vendor")) {
                users.setRole(Rol.VENDOR);
                break;
            } else System.out.println("write only user or vendor ");
        }
        return users;
    }

    public static Users updateUser(Users user) {
        Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.print("enter new name: ");
                String name = scanner.nextLine();
                if (!name.isEmpty()) {
                    user.setFirstName(name);
                    break;
                } else System.out.println("Incorrect name");
            }
            while (true) {
                System.out.print("enter new password: ");
                String pass = scanner.nextLine();
                if (pass.length() > 4) {
                    user.setPassword(pass);
                    break;
                } else System.out.println("write more 4 symbol");
            }
        while (true){
            System.out.print("enter new phone number: ");
            String number = scanner.nextLine();
            if (number.length() == 13 && number.startsWith("+996")){
                user.setPhoneNumber(number);
                break;
            } else System.out.println("write correct number");
        }
        return user;
    }

    private static boolean checkUniq(String email, DataBase dataBase) {
        for (Users users : dataBase.getAll()) {
            if (users.getEmail().equalsIgnoreCase(email)) {
                return false;
            }
        }
        return true;
    }

    private static Announcement createAnnouncement(Announcement announcement) {
        Scanner scanner = new Scanner(System.in);
        Scanner scanForNum = new Scanner(System.in);
        announcement.setId(MyGeneratorId.getIdAnnouncement());
        while (true) {
            System.out.print("enter announcement name: ");
            String name = scanner.nextLine();
            if (check(name)) {
                announcement.setName(name);
                break;
            } else System.out.println("write correct");
        }
        while (true) {
            System.out.print("enter announcement description: ");
            String desc = scanner.nextLine();
            if (check(desc)) {
                announcement.setDescription(desc);
                break;
            } else System.out.println("write correct");
        }
        while (true) {
            System.out.print("enter announcement price: ");
            double price = scanForNum.nextDouble();
            if (price > 0) {
                announcement.setPrice(price);
                break;
            }
        }
        return announcement;
    }

    private static Announcement updateAnnouncement(DataBase dataBase, Long id, Announcement announcement) {
        Scanner scanner = new Scanner(System.in);
        Scanner scanForNum = new Scanner(System.in);
        for (Users users : dataBase.getAll()) {
            for (Announcement usersAnnouncement : users.getAnnouncements()) {
                if (usersAnnouncement.getId().equals(id)) {
                    while (true) {
                        System.out.print("enter new announcement name: ");
                        String name = scanner.nextLine();
                        if (check(name)) {
                            announcement.setName(name);
                            break;
                        } else System.out.println("write correct");
                    }
                    while (true) {
                        System.out.print("enter new announcement description: ");
                        String desc = scanner.nextLine();
                        if (check(desc)) {
                            announcement.setDescription(desc);
                            break;
                        } else System.out.println("write correct");
                    }
                    while (true) {
                        System.out.print("enter new announcement price: ");
                        double price = scanForNum.nextDouble();
                        if (price > 0) {
                            announcement.setPrice(price);
                            break;
                        }
                    }
                }
            }
        }
        return announcement;
    }

    private static boolean check(String str) {
        return str.length() >= 4;
    }
}