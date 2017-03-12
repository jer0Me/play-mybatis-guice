package controllers;

import akka.NotUsed;
import akka.stream.javadsl.Source;
import akka.util.ByteString;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.UsersService;
import utils.JsonCollector;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class UsersController extends Controller {

    @Inject
    UsersService usersService;


    public Result getUsers() {

        Source<ByteString, NotUsed> users = Source.from(

                usersService.getUsers()

        ).map(

                tmUser -> ByteString.fromString(

                        Json.toJson(tmUser).toString()

                )
        );

        return ok().chunked(users);
    }

    /**public Result getUsersMap() {
        List<String> list = new ArrayList<>();

        Source<ByteString, NotUsed> users = Source.from(

                usersService.getUsersMap()

        ).map(

                userMap ->

                        "{" + "\"id\":" + "\"" + userMap.get("id") + "\"" + "," +
                                "\"name\":" + "\"" + userMap.get("name") + "\"" + "," +
                                "\"age\":" + "\"" + userMap.get("age") + "\"" + "," +
                                "\"address\":" + "\"" + userMap.get("address") + "\"" +
                         "}"

        );

        return ok().chunked(users);
    }**/

    public CompletionStage<Result> getUsersFromFile() {

        return CompletableFuture.supplyAsync(() ->

                usersService.getUsersFromFile()

        ).thenApply(

                file -> ok(file).as("application/json")
        );
    }
}
