package api;

import com.sun.net.httpserver.HttpServer;
import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import controller.Logic;
import model.Game;
import sun.rmi.runtime.Log;
import model.User;

// The Java class will be hosted at the URI path "/helloworld more comment"
@Path("/api") // apis Path, oprettes. Der annoterer URI Path. Der skal identificere den enkelte metode!.
public class HelloWorld {

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
    @Path("/user/{username}")
    @Produces("application/json")
    public String getUser(@PathParam("username") String username) {

        username = Logic.getUser();
        //udprint/hent/identificer af data omkring spillere

        return new Gson().toJson(username);
    }

    @GET //"GET-request"
    @Path("/highscore")
    @Produces("application/json")
    public String getScore(String data) {

        //TODO: ();Get method from logic to return highscore.



        System.out.println(data);
        //udprintning/hent af data omkring highscore

        return data;

    }

    @GET //"GET-request"
    @Path("/result/{userid}")
    @Produces("application/json")
    public String getGame(@PathParam("userid") int userid) {

        userid = Logic.getGame();
        return new Gson().toJson(gameid);

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

        gameid = Logic.getGame();
        return new Gson().toJson(gameid);

    }

    @POST //"POST-request" er ny data vi kan indtaste for at logge ind.
    @Path("/login/")
    @Produces("application/json")
    public Response login(String data) {

        Logic logic = new Logic();


        try{

            User user = new Gson().fromJson(data, User.class);

            int result = logic.login(user.getUserName(), user.getPassword());

            System.out.print(result);
            return Response.status(200).entity("{\"success\":\"true\"}").build();
        }catch (Exception e) {
            return  Response.status(400).entity("{}").build();
            System.out.print("");
        }
        //// Authenticates a user and returns a status code according to the result.
        // CODES:
        // 1 || SUCCESS
        // 2 || USER DOES NOT EXIST
        // 3 || WRONG PASSWORD
        // logic.login();


        return "OK";

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

        Logic.

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

        createUser = Logic.createUser();

        return new Gson().toJson(createUser);

    }

    @POST //POST-request: Nyt data; nyt spil oprettes
    @Path("/create")
    @Produces("text/plain")
    public String createGame(String data) {

        createGame = Logic.createGame();
        return new Gson().toJson(createGame);
    }

    @POST //POST-request: Opstart af nyt spil
    @Path("/start")
    @Produces("text/plain")
    public String startGame(String data) {

        startGame = Logic.startGame();
        return new Gson().toJson(startGame);
    }

    @DELETE //DELETE-request fjernelse af data (bruger): Slet bruger
    @Path("/user/")
    @Produces("text/plain")
    public String deleteUser(String data) {

        deleteUser = Logic.deleteUser();
        return new Gson().toJson(deleteUser);
    }

    @DELETE //DELETE-request fjernelse af data(spillet slettes)
    @Path("/game/")
    @Produces("text/plain")
    public String deleteGame(String data) {

        deleteGame = Logic.deleteUser();
        return new Gson().toJson(deleteGame)
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
            int result = logic.login(user.getUserName(), user.getPassword());

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
