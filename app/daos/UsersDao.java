package daos;

import models.pojos.TmUser;
import org.apache.ibatis.session.ResultHandler;

import java.util.HashMap;
import java.util.List;

public interface UsersDao  {

    public List<TmUser> getUsers();

    public List<TmUser> getUsers(UserResultHandler userResultHandler);

    public List<HashMap<String, String>> getUsersMap();

}
