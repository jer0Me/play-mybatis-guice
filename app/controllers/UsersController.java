package controllers;

import akka.NotUsed;
import akka.stream.javadsl.Source;
import akka.util.ByteString;
import org.mybatis.guice.transactional.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.UsersService;

import javax.inject.Inject;

public class UsersController extends Controller {

    @Inject
    private UsersService usersService;

    @Transactional
    public Result getUsers() {

        Source<ByteString, NotUsed> users = Source.from(

                usersService.getUsers()

        ).fold(ByteString.fromString(""),

                (userByteStringJsonConcatenation, nextUser) -> userByteStringJsonConcatenation.isEmpty() ?

                        //First user json
                        ByteString.fromString(

                                Json.toJson(nextUser).toString()

                        ) :

                        userByteStringJsonConcatenation.concat(

                                //Every concatenation has to be separated by comma
                                ByteString.fromString(", " + Json.toJson(nextUser).toString())
                        )

        ).intersperse(

                //Close the concatenation with parentheses to indicate an array of json
                ByteString.fromString("["), ByteString.fromString(""), ByteString.fromString("]")
        );

        return ok().chunked(users);
    }

}
