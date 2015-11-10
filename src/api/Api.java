package api;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import controller.Logic;
import controller.Security;
import database.DatabaseWrapper;
import model.Game;
import model.Score;
import model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

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
            user.setPassword(Security.hashing(user.getPassword()));

            HashMap <String, Integer> hashMap = Logic.authenticateUser(user.getUsername(), user.getPassword());


            if (hashMap.get("usertype") == 0) {
                hashMap.put("code", 0);
            }

            switch (hashMap.get("code")) {
                case 0:
                    return Response
                            .status(400)
                            .entity("{\"message\":\"Wrong username or password\"}")
                            .header("Access-Control-Allow-Headers", "*")
                            .build();

                case 1:
                    return Response
                            .status(400)
                            .entity("{\"message\":\"Wrong username or password\"}")
                            .header("Access-Control-Allow-Headers", "*")
                            .build();

                case 2:
                    return Response
                            .status(200)
                            .entity("{\"message\":\"Login successful\", \"userid\":" + hashMap.get("userid") + "}")
                            .header("Access-Control-Allow-Headers", "*")
                            .build();
                default:
                    return Response
                            .status(500)
                            .entity("{\"message\":\"Unknown error. Please contact Henrik Thorn at: henrik@itkartellet.dk\"}")
                            .header("Access-Control-Allow-Headers", "*")
                            .build();
            }

        } catch (JsonSyntaxException | NullPointerException e) {
            e.printStackTrace();
            return Response
                    .status(400)
                    .entity("{\"message\":\"Error in JSON\"}")
                    .header("Access-Control-Allow-Headers", "*")
                    .build();
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
    }

    /*
    @DELETE //DELETE-request fjernelse af data (bruger): Slet bruger
    @Path("/users/{userid}")
    @Produces("application/json")
    public Response deleteUser(@PathParam("userid") int userId) {

        int deleteUser = Logic.deleteUser(userId);

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
*/

    @POST //POST-request: Ny data der skal til serveren; En ny bruger oprettes
    @Path("/users/")
    @Produces("application/json")
    public Response createUser(String data) {

        User user;

        try {
            user = new Gson().fromJson(data, User.class);

            user.setType(1);

            boolean createdUser = Logic.createUser(user);

            if (createdUser) {

                return Response
                        .status(200)
                        .entity("{\"message\":\"User was created\"}")
                        .header("Access-Control-Allow-Headers", "*")
                        .build();
            } else {
                return Response.status(400).entity("{\"message\":\"Username or email already exists\"}").build();
            }
        } catch (JsonSyntaxException | NullPointerException e) {
            e.printStackTrace();
            return Response
                    .status(400)
                    .entity("{\"message\":\"Error in JSON\"}")
                    .header("Access-Control-Allow-Headers", "*")
                    .build();
        }
    }

    @GET //"GET-request"
    @Path("/users/{userId}")
    @Produces("application/json")
    // JSON: {"userId": [userid]}
    public Response getUser(@PathParam("userId") int userId) {

        User user = Logic.getUser(userId);
        //udprint/hent/identificer af data omkring spillere
        if (user != null) {
            return Response
                    .status(200)
                    .entity(new Gson().toJson(user))
                    .header("Access-Control-Allow-Origin", "*")
                    .build();
        } else {
            return Response
                    .status(400)
                    .entity("{\"message\":\"User was not found\"}")
                    .header("Access-Control-Allow-Origin", "*")
                    .build();
        }
    }

    @POST //POST-request: Nyt data; nyt spil oprettes
    @Path("/games/")
    @Produces("application/json")
    public Response createGame(String json) {

        try {
            Game game = Logic.createGame(new Gson().fromJson(json, Game.class));

            if (game != null) {
                return Response
                        .status(201)
                        .entity(new Gson().toJson(game))
                        .header("Access-Control-Allow-Headers", "*")
                        .build();
            } else {
                return Response
                        .status(400)
                        .entity("{\"message\":\"something went wrong\"}")
                        .header("Access-Control-Allow-Headers", "*")
                        .build();
            }
        } catch (JsonSyntaxException | NullPointerException e) {
            e.printStackTrace();
            return Response
                    .status(400)
                    .entity("{\"message\":\"Error in JSON\"}")
                    .header("Access-Control-Allow-Headers", "*")
                    .build();
        }
    }

    @PUT
    @Path("/games/join/")
    @Produces("application/json")
    public Response joinGame(String json) {

        try {
            Game game = new Gson().fromJson(json, Game.class);

            if (Logic.joinGame(game)) {
                return Response
                        .status(201)
                        .entity("{\"message\":\"Game was joined\"}")
                        .header("Access-Control-Allow-Origin", "*")
                        .build();
            } else {
                return Response
                        .status(400)
                        .entity("{\"message\":\"Game closed\"}")
                        .header("Access-Control-Allow-Headers", "*")
                        .build();
            }
        } catch (JsonSyntaxException | NullPointerException e) {
            e.printStackTrace();
            return Response
                    .status(400)
                    .entity("{\"message\":\"Error in JSON\"}")
                    .header("Access-Control-Allow-Headers", "*")
                    .build();
        }
    }


    @PUT
    @Path("/games/start/")
    @Produces("application/json")
    public Response startGame(String json) {

        try {
            Game game = Logic.startGame(new Gson().fromJson(json, Game.class));

            if (game != null) {
                return Response
                        .status(201)
                        .entity(new Gson().toJson(game))
                        .header("Access-Control-Allow-Origin", "*")
                        .build();
            } else {
                return Response
                        .status(400)
                        .entity("something went wrong")
                        .build();
            }
        } catch (JsonSyntaxException | NullPointerException e) {
            e.printStackTrace();
            return Response
                    .status(400)
                    .entity("{\"message\":\"Error in JSON\"}")
                    .header("Access-Control-Allow-Headers", "*")
                    .build();
        }

    }

    @DELETE //DELETE-request fjernelse af data(spillet slettes)
    @Path("/games/{gameid}")
    @Produces("appication/json")
    public Response deleteGame(@PathParam("gameid") int gameId) {

        int deleteGame = Logic.deleteGame(gameId);

        if (deleteGame == 1) {
            return Response
                    .status(200)
                    .entity("{\"message\":\"Game was deleted\"}")
                    .header("Access-Control-Allow-Headers", "*")
                    .build();
        } else {
            return Response
                    .status(400)
                    .entity("{\"message\":\"Failed. Game was not deleted\"}")
                    .header("Access-Control-Allow-Headers", "*")
                    .build();
        }
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
    public Response getGamesByUserID(@PathParam("userid") int userId) {

        ArrayList<Game> games = Logic.getGames(DatabaseWrapper.GAMES_BY_ID, userId);

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
    public Response getGamesByStatusAndUserID(@PathParam("status") String status, @PathParam("userid") int userId) {

        ArrayList<Game> games = null;
        switch (status) {
            case "pending":
                games = Logic.getGames(DatabaseWrapper.PENDING_BY_ID, userId);
                break;
            case "open":
                games = Logic.getGames(DatabaseWrapper.OPEN_BY_ID, userId);
                break;
            case "finished":
                games = Logic.getGames(DatabaseWrapper.COMPLETED_BY_ID, userId);
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
    @Path("/games/opponent/{userid}/")
    @Produces("application/json")
    public Response getGamesInvitedByID(@PathParam("userid") int userId) {

        ArrayList<Game> games = Logic.getGames(DatabaseWrapper.PENDING_INVITED_BY_ID, userId);

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
    public Response getGamesHostedByID(@PathParam("userid") int userId) {

        ArrayList<Game> games = Logic.getGames(DatabaseWrapper.PENDING_HOSTED_BY_ID, userId);

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


        ArrayList<Game> games = Logic.getGames(DatabaseWrapper.OPEN_GAMES, 0);

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
}