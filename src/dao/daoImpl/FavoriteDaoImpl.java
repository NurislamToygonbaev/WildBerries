package dao.daoImpl;

import dao.FavoriteDao;
import database.DataBase;

public class FavoriteDaoImpl implements FavoriteDao{
    private final DataBase dataBase;

    public FavoriteDaoImpl(DataBase dataBase) {
        this.dataBase = dataBase;
    }
}
