package api;

import java.lang.invoke.SwitchPoint;
import java.lang.reflect.Array;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.net.httpserver.HttpServer;
import controller.Logic;
import controller.Security;
import model.Config;
import model.Game;
import model.Gamer;
import model.Score;
import model.User;
import org.codehaus.jettison.json.JSONException;
//TODO: Can't parse with this import. Maybe because the parser and object needs to be from same lib
//import org.codehaus.jettison.json.JSONObject;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import sun.rmi.runtime.Log;

import java.io.FileReader;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.Locale;
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

            int[] result = Logic.userLogin(user.getUsername(), user.getPassword());

            switch (result[0]) {
                case 0:
                    return Response
                            .status(400)
                            .entity("{\"message\":\"User doesn't exist\"}")
                            .header("Access-Control-Allow-Headers", "*")
                            .build();

                case 1:
                    return Response
                            .status(400)
                            .entity("{\"message\":\"Wrong password\"}")
                            .header("Access-Control-Allow-Headers", "*")
                            .build();

                case 2:
                    return Response
                            .status(200)
                            .entity("{\"message\":\"Login successful\", \"userid\":" + result[1] + "}")
                            .header("Access-Control-Allow-Headers", "*")
                            .build();

                default:
                    return Response
                            .status(400)
                            .entity("{\"message\":\"Something went wrong\"}")
                            .header("Access-Control-Allow-Headers", "*")
                            .build();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(400).entity("{\"message\":\"Bad request\"}").build();
        }

    }

    @GET //"GET-request"
    @Path("/users/") //USER-path - identifice det inden for metoden
    @Produces("application/json")
    public Response getAllUsers() {

        ArrayList<User> users = Logic.getUsers();

        return Response
                .status(200)
                .entity(new Gson().toJson(users))
                .header("Access-Control-Allow-Origin", "*")
                .build();

        //return new Gson().toJson(users);
    }

    @POST //DELETE-request fjernelse af data (bruger): Slet bruger
    @Path("/users/delete/")
    @Produces("application/json")
    public Response deleteUser(String userId) {

        User user = new Gson().fromJson(userId, User.class);
        int deleteUser = Logic.deleteUser(user.getId());

        if (deleteUser == 1) {
            return Response
                    .status(200)
                    .entity("{\"message\":\"User was deleted\"}")
                    .header("Access-Control-Allow-Headers", "*")
                    .build();
        } else {
            return Response
                    .status(400)
                    .entity("{\"message\":\"Failed. User was not deleted\"}")
                    .header("Access-Control-Allow-Headers", "*")
                    .build();
        }

    }

    @POST //POST-request: Ny data der skal til serveren; En ny bruger oprettes
    @Path("/user/")
    @Produces("application/json")
    public Response createUser(String data) {
        User user = null;



        boolean createdUser = Logic.createUser(user);

        if (createdUser) {
            return Response
                    .status(200)
                    .entity("{\"message\":\"User was created\"}")
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "PUT, GET, POST")
                    .header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept")
                    .build();
        } else {
            return Response.status(400).entity("{\"message\":\"Failed. User was not created\"}").build();
        }
    }

    @GET //"GET-request"
    @Path("/user/{userId}")
    @Produces("application/json")
    // JSON: {"userId": [userid]}
    public Response getUser(@PathParam("userId") int userId) {

        User user = Logic.getUser(userId);
        //udprint/hent/identificer af data omkring spillere

        return Response
                .status(200)
                .entity(new Gson().toJson(user))
                .header("Access-Control-Allow-Origin", "*")
                .build();
    }

//    @GET //"GET-request"
//    @Path("/games")
//    @Produces("application/json")
//    public String getGames() {
//
//        ArrayList<model.Game> games = Logic.getGames();
//        return new Gson().toJson(games);
//
//    }

    @POST //POST-request: Nyt data; nyt spil oprettes
    @Path("/game/")
    @Produces("application/json")
    public Response createGame(String json) {

        try {

            Game game = new Gson().fromJson(json, Game.class);
            game.setHostControls(game.getHost().getControls());

            Logic.createGame(game);

            return Response
                    .status(201)
                    .entity("{\"message\":\"Game was created\"}")
                    .header("Access-Control-Allow-Origin", "*")
                    .build();
            //TODO: changed JSONObject so it imports from org.json.simple.JSONObject instead of the codehaus lib
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Response.status(500).entity("something went wrong").build();

    }

    @GET //GET-request: Afslutter spillet
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

        int deleteGame = Logic.deleteUser(gameId);
        return new Gson().toJson(deleteGame);
    }

    @GET //"GET-request"
    @Path("/game/{gameid}")
    @Produces("application/json")
    public String getGame(@PathParam("gameid") int gameid) {

        Game game = Logic.getGame(gameid);
        return new Gson().toJson(game);
    }

    @GET //"GET-request"
    @Path("/scores/")
    @Produces("application/json")
    public String getHighscore(String data) {

        return new Gson().toJson(Logic.getHighscore());

    }

    /*
    Getting games by userid
     */
    @GET //"GET-request"
    @Path("/games/{userid}/")
    @Produces("application/json")
    public Response getGamesByUserID(@PathParam("userid") int userid) {

        ArrayList<Game> games = Logic.getGamesByID(userid);

        return Response
                .status(201)
                .entity(new Gson().toJson(games))
                .header("Access-Control-Allow-Origin", "*")
                .build();
    }

    /*
    Getting games by game status and user id
     */
    @GET //"GET-request"
    @Path("/games/{status}/{userid}")
    @Produces("application/json")
   public Response getGamesByStatusAndUserID(@PathParam("status") String status,@PathParam("userid") int userid) {

        ArrayList<Game> games = new ArrayList<>();

        switch (status){
            case "pending":
                games = Logic.getPendingGamesByID(userid);
                break;
            case "open":
                games = Logic.getOpenGamesByID(userid);
                break;
            case "finished":
                games = Logic.getCompletedGamesByID(userid);
                break;
        }

        return Response
                .status(201)
                .entity(new Gson().toJson(games))
                .header("Access-Control-Allow-Origin", "*")
                .build();
    }

    //Gets all games where the user is invited
    @GET
    @Path("/games/guest/{userid}/")
    @Produces("application/json")
    public Response getGamesInvitedByID(@PathParam("userid") int userid){

        ArrayList<Game> games = Logic.getGamesInvitedByID(userid);

        return Response
                .status(201)
                .entity(new Gson().toJson(games))
                .header("Access-Control-Allow-Origin", "*")
                .build();
    }

    //Gets all games hosted by the user
    @GET
    @Path("/games/host/{userid}/")
    @Produces("application/json")
    public Response getGamesHostedByID(@PathParam("userid") int userid){

        ArrayList<Game> games = Logic.getGamesHostedByID(userid);

        return Response
                .status(201)
                .entity(new Gson().toJson(games))
                .header("Access-Control-Allow-Origin", "*")
                .build();
    }


    /*
    Getting a list of all open games
     */
    @GET //"GET-request"
    @Path("/games/open/")
    @Produces("application/json")
    public Response getOpenGames() {

        ArrayList<Game> games = Logic.getOpenGames();

        return Response
                .status(201)
                .entity(new Gson().toJson(games))
                .header("Access-Control-Allow-Origin", "*")
                .build();
    }


    /*
    Getting all scores by user id
    Used for showing all finished games and scores for the user
     */
    @GET //"GET-request"
    @Path("/scores/{userid}")
    @Produces("application/json")
    public Response getScoresByUserID(@PathParam("userid") int userid) {

        ArrayList<Score> score = Logic.getScoresByUserID(userid);

        return Response
                .status(201)
                .entity(new Gson().toJson(score))
                .header("Access-Control-Allow-Origin", "*")
                .build();
    }

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServerFactory.create("http://localhost:9998/");

        Config.init();
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
