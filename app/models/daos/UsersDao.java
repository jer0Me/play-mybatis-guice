package models.daos;

import models.dtos.UserDto;

import java.util.List;

public interface UsersDao {

    public List<UserDto> getUsers();

}
