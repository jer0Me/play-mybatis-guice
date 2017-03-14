package services;


import daos.UserResultHandler;
import daos.UsersDao;
import models.pojos.TmUser;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.inject.Inject;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class UsersService {

    @Inject
    SqlSessionFactory sqlSessionFactory;
    @Inject
    private UsersDao usersDao;

    public List<TmUser> getUsers() {

        return usersDao.getUsers();
    }

    public List<HashMap<String, String>> getUsersMap() {

        return usersDao.getUsersMap();
    }


    public File getUsersFromFile() {

        FileOutputStream fop = null;
        File file;

        try {

            file = new File("users");
            fop = new FileOutputStream(file);

            file.createNewFile();

            UserResultHandler userResultHandler = new UserResultHandler(fop);

            SqlSession sqlSession = sqlSessionFactory.openSession();
            sqlSession.select("getUsers", userResultHandler);


            sqlSession.close();


            fop.flush();
            fop.close();

            return file;

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }
}
