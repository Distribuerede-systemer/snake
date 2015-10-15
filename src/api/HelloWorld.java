package api;

import com.sun.net.httpserver.HttpServer;
import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.google.gson.Gson;

import java.io.IOException;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import controller.Logic;
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

        Logic logic = new Logic();

        //TODO; Hent brugere fra DB
        return new Gson().toJson(logic.getUsers());
    }

    @GET //"GET-request"
    @Path("/user/{username}")
    @Produces("application/json")
    public String getUser(@PathParam("username") String username) {

        //udprint/hent/identificer af data omkring spillere

        User user = Logic.getUser(123);

        return new Gson().toJson(user);
    }

    @GET //"GET-request"
    @Path("/highscore")
    @Produces("application/json")
    public String getScore(String data) {

        //TODO: Get method from logic to return highscore.
        System.out.println(data);
        //udprintning/hent af data omkring highscore

        return data;

    }

    @GET //"GET-request"
    @Path("/games")
    @Produces("application/json")
    public String getGames() {


        return "";

    }

    @GET //"GET-request"
    @Path("/result/{gameid}")
    @Produces("application/json")
    public String getGame(@PathParam("gameid") int gameid) {



        return "";

    }

    @POST //"POST-request" er ny data vi kan indtaste for at logge ind.
    @Path("/login/")
    @Produces("application/json")
    public Response login(String data) {

        Logic logic = new Logic();


        try{

            User user = new Gson().fromJson(data, User.class);


            return Response.status(200).entity("{\"success\":\"true\"}").build();
        }catch (Exception e) {
            return  Response.status(400).entity("{}").build();
        }
        //// Authenticates a user and returns a status code according to the result.
        // CODES:
        // 1 || SUCCESS
        // 2 || USER DOES NOT EXIST
        // 3 || WRONG PASSWORD
        // logic.login();


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

        /*if (control1.getMovement().equals("w"))
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


        }*/

        // System.out.println(data);
        //return "OK" ;
        return Response.status(200).entity("OK").build();
    }

    @POST //POST-request: Ny data der skal til serveren; En ny bruger oprettes
    @Path("/user/")
    @Produces("text/plain")
    public String createUser(String data) {

        System.out.println(data);
        return "OK";
    }

    @POST //POST-request: Nyt data; nyt spil oprettes
    @Path("/create")
    @Produces("text/plain")
    public String createGame(String data) {

        System.out.println(data);
        return "OK";
    }

    @POST //POST-request: Opstart af nyt spil
    @Path("/start")
    @Produces("text/plain")
    public String startGame(String data) {

        System.out.println(data);
        return "OK";
    }

    @DELETE //DELETE-request fjernelse af data (bruger): Slet bruger
    @Path("/user/")
    @Produces("text/plain")
    public String deleteUser(String data) {

        System.out.println(data);
        return data + " has been deleted";
    }

    @DELETE //DELETE-request fjernelse af data(spillet slettes)
    @Path("/game/")
    @Produces("text/plain")
    public String deleteGame(String data) {

        System.out.println(data);
        return data + " has been deleted";
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

    class Game {

        private String gamename;
        private int result;

        public void setGamename(String gamename) {
            this.gamename = gamename;
        }

        public void setResult(int result) {
            this.result = result;
        }

        public String getGamename() {
            return gamename;
        }

        public int getResult() {
            return result;
        }

    }

    class Control {

        private String movement;


        public void setMovement(String movement) {
            this.movement = movement;
        }


        public String getMovement() {
            return movement;
        }


    }
}