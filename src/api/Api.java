package api;

import com.google.gson.Gson;
import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.net.httpserver.HttpServer;
import controller.Logic;
import model.Game;
import model.Score;
import model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

@Path("/api")
public class Api {

    @GET //"GET-Request" gør at vi kan forspørge en specifik data
    @Produces("application/json")
    public String getClichedMessage() {
        // Return some cliched textual content
        return "Hello World!";
    }

    @POST //"POST-request" er ny data vi kan indtaste for at logge ind.
    @Path("/login/")
    @Produces("application/json")
    public Response login(String data) {

        try {

            User user = new Gson().fromJson(data, User.class);

            int result = Logic.userLogin(user.getUserName(), user.getPassword());

            switch (result) {
                case 0:
                    return Response.status(400).entity("{\"User\"doens't exist\"}").build();
                    //break;
                case 1:
                    return Response.status(400).entity("{\"Wrong\"password\"}").build();
                    //break;
                case 2:
                    return Response.status(200).entity("{\"Login\"successful\"}").build();
                   //break;
                default:
                    return Response.status(400).entity("{\"Something\"went\"wrong\"}").build();
            }

        } catch (Exception e) {
            return Response.status(400).entity("{\"Bad\"request\"true\"}").build();
        }

    }

    @GET //"GET-request"
    @Path("/user/") //USER-path - identifice det inden for metoden
    @Produces("application/json")
    public String getAllUsers() {

        ArrayList<User> users = Logic.getUsers();


        //TODO; Hent brugere fra DB
        return new Gson().toJson(users);
    }

    @DELETE //DELETE-request fjernelse af data (bruger): Slet bruger
    @Path("/user/")
    @Produces("application/json")
    public String deleteUser(int userId) {

        boolean deleteUser = Logic.deleteUser(userId);
        return new Gson().toJson(deleteUser);
    }

    @POST //POST-request: Ny data der skal til serveren; En ny bruger oprettes
    @Path("/user/")
    @Produces("application/json")
    public String createUser(String data) {
        //TODO: Needs to be fixed.
        User user = null;

        boolean createdUser = Logic.createUser(user);

        if (createdUser) {

        } else {

        }

        return "";
        //return new Gson().toJson(createUser);
    }

    @GET //"GET-request"
    @Path("/user/{userId}")
    @Produces("application/json")
    // JSON: {"userId": [userid]}
    public String getUser(@PathParam("userId") int userId) {

        User user = Logic.getUser(userId);
        //udprint/hent/identificer af data omkring spillere

        return new Gson().toJson(user);
    }

    @GET //"GET-request"
    @Path("/games")
    @Produces("application/json")
    public String getGames() {

        ArrayList<model.Game> games = Logic.getGames();
        return new Gson().toJson(games);

    }

    @POST //POST-request: Nyt data; nyt spil oprettes
    @Path("/game/")
    @Produces("application/json")
    public String createGame(String json) {


        //TODO: Parse json and get userId and gameName.


        //User host = Logic.getUser(userId);
        //Game createGame = Logic.createGame(gameName, host);
        //return new Gson().toJson(createGame);
        return "";
    }

    @GET //GET-request: Opstart af nyt spil
    @Path("/startgame/{gameid}")
    @Produces("application/json")
    public String startGame(@PathParam("gameid") int gameId) {

        Map startGame = Logic.startGame(gameId);
        return new Gson().toJson(startGame);

    }

    @DELETE //DELETE-request fjernelse af data(spillet slettes)
    @Path("/game/{gameid}")
    @Produces("appication/json")
    public String deleteGame(@PathParam("gameid") int gameId) {

        boolean deleteGame = Logic.deleteUser(gameId);
        return new Gson().toJson(deleteGame);
    }

    @GET //"GET-request"
    @Path("/result/{gameid}")
    @Produces("application/json")
    public String getGame(@PathParam("gameid") int gameid) {

        Game game = Logic.getGame(gameid);
        return new Gson().toJson(game);

    }

    @GET //"GET-request"
    @Path("/highscore/")
    @Produces("application/json")
    public String getHighScore(String data) {

        //TODO: Get method from logic to return highscores.

        ArrayList<Score> Score = Logic.getHighscores();
        return new Gson().toJson(Score);

    }

    @GET //"GET-request"
    @Path("/score/{userid}")
    @Produces("application/json")
    public String getScore(@PathParam("userid") int userid) {

        Score score = Logic.getHighscore(userid);
        return new Gson().toJson(score);

    }

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServerFactory.create("http://localhost:9998/");
        server.start();


        System.out.println("Server running");
        System.out.println("Visit: http://localhost:9998/api");
        System.out.println("Hit return to stop...");
        System.in.read();
        System.out.println("Stopping server");
        server.stop(0);
        System.out.println("Server stopped");

        System.out.println();
    }

}

