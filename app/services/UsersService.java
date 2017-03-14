package services;

import daos.UsersDao;
import models.pojos.TmUser;

import javax.inject.Inject;
import java.util.List;

public class UsersService {

    @Inject
    private UsersDao usersDao;

    public List<TmUser> getUsers() {

        return usersDao.getUsers();

    }

}
