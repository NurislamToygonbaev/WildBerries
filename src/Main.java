import dao.daoImpl.AnnouncementDaoImpl;
import dao.daoImpl.FavoriteDaoImpl;
import dao.daoImpl.UserDaoImpl;
import database.DataBase;
import enums.Rol;
import generator.MyGeneratorId;
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
                        System.out.println(register(users));
                        System.out.println(userService.add(users));
                    }
                    case 2 -> {
                    }
                    default -> System.out.println("enter correct choice");
                }
            } catch (InputMismatchException e) {
                System.out.println("Incorrect, enter valid Integer");
            }
        }
    }

    private static void menu() {
        System.out.println("""
                0.  Exit
                1.  Registration
                2.  LogIn
                """);
    }

    private static Users register(Users users) {
        Scanner scanner = new Scanner(System.in);
        users.setId(MyGeneratorId.getIdUser());
        while (true){
            System.out.print("enter user name: ");
            String name = scanner.nextLine();
            if (!name.isEmpty()) {
                users.setFirstName(name);
                break;
            }
        }
        while (true){
            System.out.print("enter email: ");
            String email = scanner.nextLine();
            if (email.endsWith("@gmail.com") && email.length() > 11){
                users.setEmail(email);
                break;
            }
        }
        while (true){
            System.out.print("enter password: ");
            String pass = scanner.nextLine();
            if (pass.length() > 4){
                users.setPassword(pass);
                break;
            }
        }
        while (true){
            System.out.print("enter role (user|vendor) : ");
            String role = scanner.nextLine();
            if (role.equalsIgnoreCase("user")){
                users.setRole(Rol.USER);
                break;
            } else if (role.equalsIgnoreCase("vendor")) {
                users.setRole(Rol.VENDOR);
                break;
            }
        }
        return users;
    }
}