package services;

import models.daos.UsersDao;
import models.dtos.UserDto;

import javax.inject.Inject;
import java.util.List;

public class UsersService {

    @Inject
    private UsersDao usersDao;

    public List<UserDto> getUsers() {

        return usersDao.getUsers();

    }

}
