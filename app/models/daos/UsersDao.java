package models.daos;

import models.pojos.TmUser;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.List;

public interface UsersDao {

    public List<TmUser> getUsers();

}
