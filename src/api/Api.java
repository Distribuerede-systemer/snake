package api;

import com.google.gson.Gson;
import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.net.httpserver.HttpServer;
import controller.Logic;
import model.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

//TODO: Can't parse with this import. Maybe because the parser and object needs to be from same lib
//import org.codehaus.jettison.json.JSONObject;

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
                    return Response.status(400).entity("{\"message\":\"User doesn't exist\"}").build();

                case 1:
                    return Response.status(400).entity("{\"message\":\"Wrong password\"}").build();

                case 2:
                    return Response.status(200).entity("{\"message\":\"Login successful\"}").build();

                default:
                    return Response.status(400).entity("{\"message\":\"Something went wrong\"}").build();
            }

        } catch (Exception e) {
            return Response.status(400).entity("{\"message\":\"Bad request\"}").build();
        }

    }

    @GET //"GET-request"
    @Path("/user/") //USER-path - identifice det inden for metoden
    @Produces("application/json")
    public Response getAllUsers() {

        ArrayList<User> users = Logic.getUsers();

        return Response
                .status(200)
                .entity(new Gson().toJson(users))
                .header("Access-Control-Allow-Origin", "*")
                .build();
    }

    @DELETE //DELETE-request fjernelse af data (bruger): Slet bruger
    @Path("/user/")
    @Produces("application/json")
    public Response deleteUser(int userId) {

        boolean deleteUser = Logic.deleteUser(userId);

        if (deleteUser) {
            return Response.status(200).entity("{\"message\":\"User was deleted\"}").build();
        } else {
            return Response.status(400).entity("{\"message\":\"Failed. User was not deleted\"}").build();
        }

    }

    @POST //POST-request: Ny data der skal til serveren; En ny bruger oprettes
    @Path("/user")
    @Produces("application/json")
    public Response createUser(String data) {

        User user = new Gson().fromJson(data, User.class);

        boolean createdUser = Logic.createUser(user);



        if (createdUser) {
            return Response
                    .status(201)
                    .entity("{\"message\":\"User was created\"}")
                    .header("Access-Control-Allow-Origin", "*")
                    .build();
        } else {
            return Response
                    .status(400)
                    .entity("{\"message\":\"Failed. User was not created\"}")
                    .header("Access-Control-Allow-Origin", "*")
                    .build();
        }
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

        JSONParser jsonParser = new JSONParser();

        String gameName;
        Gamer host;
        Gamer opponent;

        try {

            //Initialize Object class as json, parsed by jsonParsed.
            Object obj = jsonParser.parse(json);

            //Instantiate JSONObject class as jsonObject equal to obj object.
            JSONObject jsonObject = (JSONObject) obj;

            //Getting values from our JSON objects and setting variables + fetching User objects host and opponent via db.getUser.
            gameName = ((String) jsonObject.get("gameName"));

            host = new Gamer();
            host.setId(((Long) jsonObject.get("host")).intValue());
            host.setControls((String) jsonObject.get("hostControls"));

            opponent = new Gamer();
            opponent.setId(((Long) jsonObject.get("opponent")).intValue());

            //host = Logic.getUser(((Long) jsonObject.get("host")).intValue());
            //opponent  = Logic.getUser(((Long) jsonObject.get("opponent")).intValue());

            Game createGame = Logic.createGame(gameName,host,opponent);

            String gameJson = new Gson().toJson(createGame);

            return Response.status(201).entity(gameJson)
                    .header("Access-Control-Allow-Headers", "*")
                    .build();

            //TODO: changed JSONObject so it imports from org.json.simple.JSONObject instead of the codehaus lib
        } /*catch (JSONException e) {
            e.printStackTrace();
        }*/ catch (org.json.simple.parser.ParseException e) {
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

        boolean deleteGame = Logic.deleteUser(gameId);
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

    @GET //"GET-request"
    @Path("/games/{userid}")
    @Produces("application/json")
    // TODO: Rename method in wrapper + logic: getScoresByUserID
   public Response getGamesByUserID(@PathParam("userid") int userid) {

        ArrayList<Score> score = Logic.getGamesByUserID(userid);

        return Response.status(201).entity(new Gson().toJson(score))
                .header("Access-Control-Allow-Headers", "*")
                .build();
    }

//
//    }

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
