package service.impl;

import dao.FavoriteDao;
import dao.daoImpl.FavoriteDaoImpl;
import service.FavoriteService;

public class FavoriteServiceImpl implements FavoriteService {
    private final FavoriteDaoImpl favoriteDao;

    public FavoriteServiceImpl(FavoriteDaoImpl favoriteDao) {
        this.favoriteDao = favoriteDao;
    }
}
