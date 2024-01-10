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

        DataBase dataBase = new DataBase();
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
                        System.out.println(register(users, dataBase));
                        System.out.println(userService.add(users));
                    }
                    case 2 -> {
                        System.out.print("enter email: ");
                        String log = scanForStr.nextLine();
                        System.out.print("enter password: ");
                        String pass = scanForStr.nextLine();
                        currentUser = userService.logIn(log, pass);
                        if (currentUser != null){
                            if (currentUser.getRole().equals(Rol.USER)){
                                innerLoopUser:
                                while (true){
                                    menuUser();
                                    switch (scanner.nextInt()){
                                        case 0 -> {break innerLoopUser;}
                                        case 1 -> {}
                                        case 2 -> { }
                                        case 3 -> {  }
                                        case 4 -> {   }
                                        default -> System.out.println("enter correct choice");
                                    }
                                }
                            } else if (currentUser.getRole().equals(Rol.VENDOR)) {
                                innerLoopVendor:
                                while (true){
                                    menuVendor();
                                    switch (scanner.nextInt()){
                                        case 0 -> {break innerLoopVendor;}
                                        case 1 -> {
                                            Announcement announcement = new Announcement();
                                            System.out.println(createAnnouncement(announcement));
                                            System.out.println(announcementService.save(announcement));
                                        }
                                        case 2 -> { }
                                        case 3 -> {  }
                                        case 4 -> {   }
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
    private static void menuUser(){
        System.out.println("""
                0.  Exit
                1.  get All announcement
                2.  get announcement by name
                3.  get announcement by id
                4.  my favorites
                5.  update my profile
                """);
    }
    private static void menuVendor(){
        System.out.println("""
                0.  Exit
                1.  Create announcement
                2.  get all my announcement
                3.  update my announcement by id
                4.  delete announcement by id
                5.  update my profile
                """);
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

    private static boolean checkUniq(String email, DataBase dataBase){
        for (Users users : dataBase.getAll()) {
            if (users.getEmail().equalsIgnoreCase(email)){
                return false;
            }
        }
        return true;
    }

    private static Announcement createAnnouncement(Announcement announcement){
        Scanner scanner = new Scanner(System.in);
        Scanner scanForNum = new Scanner(System.in);
        announcement.setId(MyGeneratorId.getIdAnnouncement());
        while (true){
            System.out.print("enter announcement name: ");
            String name = scanner.nextLine();
            if (check(name)){
                announcement.setName(name);
                break;
            } else System.out.println("write correct");
        }
        while (true){
            System.out.print("enter announcement description: ");
            String desc = scanner.nextLine();
            if (check(desc)){
                announcement.setDescription(desc);
                break;
            } else System.out.println("write correct");
        }
        while (true){
            System.out.print("enter announcement price: ");
            double price = scanForNum.nextDouble();
            if (price > 0){
                announcement.setPrice(price);
                break;
            }
        }
        return announcement;
    }
    private static boolean check(String str){
        return str.length() >= 4;
    }
}