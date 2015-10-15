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

// The Java class will be hosted at the URI path "/helloworld more comment"
@Path("/api") // apis Path, oprettes. Der annoterer URI Path. Der skal identificere den enkelte metode!.
public class api {

    // The Java method will process HTTP GET requests
    @GET //"GET-Request" gør at vi kan forspørge en specifik data
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("text/plain")
    public String getClichedMessage() {
        // Return some cliched textual content
        return "Hello World!";
    }

    @GET //"GET-request"
    @Path("/user/") //USER-path - identifice det inden for metoden
    @Produces("application/json")
    public String getAllUsers() {

        ArrayList<model.User> users = Logic.getUsers();


        //TODO; Hent brugere fra DB
        return new Gson().toJson(users);
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
    @Path("/highscore")
    @Produces("application/json")
    public String getHighScore(String data) {

        //TODO: ();Get method from logic to return highscores.

         ArrayList<model.Score> Score = Logic.getHighscores();
         return new Gson().toJson(Score);

    }

    @GET //"GET-request"
    @Path("/score/{userid}")
    @Produces("application/json")
    public String getScore(@PathParam("userid") int userid) {

        Score score = Logic.getHighscore(userid);
        return new Gson().toJson(userid);

    }

    @GET //"GET-request"
    @Path("/games")
    @Produces("application/json")
    public String getGames() {

        ArrayList<model.Game> games = Logic.getGames();
        return new Gson().toJson(games);

    }

    @GET //"GET-request"
    @Path("/result/{gameid}")
    @Produces("application/json")
    public String getGame(@PathParam("gameid") int gameid) {

        Game game = Logic.getGame(gameid);
        return new Gson().toJson(gameid);

    }

    @POST //"POST-request" er ny data vi kan indtaste for at logge ind.
    @Path("/login/")
    @Produces("application/json")
    public Response login(String data) {

//        Logic logic = new Logic();
  //      Logic.userLogin();

        try{

            User user = new Gson().fromJson(data, User.class);

            int result = Logic.userLogin(user.getUserName(), user.getPassword());

            System.out.print(result);
            return Response.status(200).entity("{\"success\":\"true\"}").build();
        }catch (Exception e) {
            return  Response.status(400).entity("{\"Bad\"request\"true\"}").build();
            System.out.print("");
        }
        //// Authenticates a user and returns a status code according to the result.
        // CODES:
        // 1 || SUCCESS
        // 2 || USER DOES NOT EXIST
        // 3 || WRONG PASSWORD
        // logic.login();


        //return "OK";

        //såfremt der er overenstemmelse med brugernavn og password = godkendelse
    }

    @POST //"POST-request" er ny "data", der skal indtastes, for at styre spillet.
    @Path("/controls/")
    @Produces("application/json")

    public Response controls(String json) {
        // public String controls(String data)  {


        //    Control control1 = new Gson().fromJson(json, Control.class);
        /* Vi laver her et json til gson statement, denne linje gør at vores json kode bliver konventeret
         javascript kode */
        //System.out.println(control1.getMovement());

        Logic

        if (control1.getMovement().equals("w"))
            return Response.status(201).entity("Success").build();

        if (control1.getMovement().equals("a")) {
            return Response.status(201).entity("Success").build();

        }
        if (control1.getMovement().equals("s")) {
            return Response.status(201).entity("Success").build();

        }
        if (control1.getMovement().equals("d")) {
            return Response.status(201).entity("Success").build();

        } else {
            return Response.status(500).entity("Fail").build();
            //If-else statement, for de forskellige indtast muligheder, såfremt værdien er ugyldig udprintes en fejlkode.


        }

        // System.out.println(data);
        //return "OK" ;
    }

    @POST //POST-request: Ny data der skal til serveren; En ny bruger oprettes
    @Path("/user/")
    @Produces("text/plain")
    public String createUser(String data) {

        User user = null;

        boolean createdUser = Logic.createUser(user);

        if(createdUser){

        } else {

        }


        //return new Gson().toJson(createUser);

    }

    @POST //POST-request: Nyt data; nyt spil oprettes
    @Path("/create")
    @Produces("text/plain")
    public String createGame(String gameName, int userId) {

        User host = Logic.getUser(userId);
        createGame = Logic.createGame(gameName, host);
        return new Gson().toJson(createGame);
    }

    @POST //POST-request: Opstart af nyt spil
    @Path("/gameId")
    @Produces("text/plain")
    public String startGame(int gameId) {

        Map startGame = Logic.startGame(gameId);
        return new Gson().toJson(startGame);

    }

    @DELETE //DELETE-request fjernelse af data (bruger): Slet bruger
    @Path("/user/")
    @Produces("text/plain")
    public String deleteUser(int userId) {

        boolean deleteUser = Logic.deleteUser(userId);
        return new Gson().toJson(deleteUser);
    }

    @DELETE //DELETE-request fjernelse af data(spillet slettes)
    @Path("/gameId/")
    @Produces("text/plain")
    public String deleteGame(int gameId) {

        boolean deleteGame = Logic.deleteUser(gameId);
        return new Gson().toJson(deleteGame);
    }

    /**
     * Login method
     *
     * Request example:
     * {"userName":"killerxp2000", "password":"abc123"}
     * @param data
     * @return
     */
    @POST //"POST-request" er ny data vi kan indtaste for at logge ind.
    @Path("/login/")
    @Produces("application/json")
    public Response login(String data)  {

        try {
            User user = new Gson().fromJson(data, User.class);

            Logic logic = new Logic();
            int result = Logic.login(user.getUserName(), user.getPassword());

            System.out.println(result);
            return Response.status(200).entity("{\"success\":\"true\"}").build();

        }
        catch (Exception e){
            e.printStackTrace();
            return Response.status(400).entity("{\"success\":\"false\", \"message\":\"bad authentication\"}").build();
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
