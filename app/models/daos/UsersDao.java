package models.daos;

import models.pojos.TmUser;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.List;

public interface UsersDao {

    @Select("SELECT * FROM ex01_schema.tm_user")
    public List<TmUser> getUsers();

}
