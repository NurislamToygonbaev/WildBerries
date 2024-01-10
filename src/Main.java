import dao.daoImpl.AnnouncementDaoImpl;
import dao.daoImpl.FavoriteDaoImpl;
import dao.daoImpl.UserDaoImpl;
import database.DataBase;
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
        while (true){
            try {
                menu();
                switch (scanner.nextInt()){
                    case 0 -> {break OuterLoop;}
                    case 1 -> {}
                    case 2 -> { }
                    default -> System.out.println("enter correct choice");
                }
            } catch (InputMismatchException e){
                System.out.println("Incorrect, enter valid Integer");
            }
        }
    }
    private static void menu(){
        System.out.println("""
                0.  Exit
                1.  Registration
                2.  LogIn
                """);
    }
}