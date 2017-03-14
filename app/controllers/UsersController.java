package controllers;

import akka.NotUsed;
import akka.stream.javadsl.Source;
import akka.util.ByteString;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.UsersService;

import javax.inject.Inject;

public class UsersController extends Controller {

    @Inject
    UsersService usersService;

    public Result getUsers() {

        Source<ByteString, NotUsed> users = Source.from(

                usersService.getUsers()

        ).fold(ByteString.fromString(""),

                (currentUserJson, nextUserJson) -> currentUserJson.isEmpty() ?

                        ByteString.fromString(Json.toJson(nextUserJson).toString()) :

                        currentUserJson.concat(

                                ByteString.fromString(", " + Json.toJson(nextUserJson).toString())
                        )

        ).intersperse(

                ByteString.fromString("["), ByteString.fromString(""), ByteString.fromString("]")
        );

        return ok().chunked(users);
    }

}
