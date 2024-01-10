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
                        System.out.println(register(users, dataBase));
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
        System.out.print("Command: ");
    }

    private static Users register(Users users, DataBase dataBase) {
        Scanner scanner = new Scanner(System.in);
        users.setId(MyGeneratorId.getIdUser());
        while (true){
            System.out.print("enter user name: ");
            String name = scanner.nextLine();
            if (!name.isEmpty()) {
                users.setFirstName(name);
                break;
            } else System.out.println("Incorrect name");
        }
        while (true){
            System.out.print("enter email: ");
            String email = scanner.nextLine();
            if (email.endsWith("@gmail.com") && email.length() > 11 && checkUniq(email, dataBase)){
                users.setEmail(email);
                break;
            } else System.out.println("Incorrect email, use domain @gmail.com");
        }
        while (true){
            System.out.print("enter password: ");
            String pass = scanner.nextLine();
            if (pass.length() > 4){
                users.setPassword(pass);
                break;
            } else System.out.println("write more 4 symbol");
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
}