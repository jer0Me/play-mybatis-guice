package daos;

import models.pojos.TmUser;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;

import java.io.FileOutputStream;
import java.io.IOException;

import play.libs.Json;

public class UserResultHandler implements ResultHandler {

    private final FileOutputStream fop;

    public UserResultHandler(FileOutputStream fop) {
        this.fop = fop;
    }


    @Override
    public void handleResult(ResultContext resultContext) {

        TmUser tmUser = (TmUser)resultContext.getResultObject();

        try {
            fop.write(Json.toJson(tmUser).toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
