package api;

import com.sun.net.httpserver.HttpServer;
import com.sun.jersey.api.container.httpserver.HttpServerFactory;

import java.io.IOException;

import javax.ws.rs.*;

/**
 * Created by jesperbruun on 12/10/15.
 */
// The Java class will be hosted at the URI path "/helloworld"
@Path("/api")
public class HelloWorld {
    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("text/plain")
    public String getClichedMessage() {
        // Return some cliched textual content
        return "Hello World!";
    }

    @GET
    @Path("/user/")
    @Produces("application/json")
    public String getAllUsers() {

        //TODO; Hent brugere fra DB
        return "users";
    }

    @GET
    @Path("/user/{userid}")
    @Produces("application/json")
    public String getUser(@PathParam("userid") String userid) {

        System.out.println(userid);

        return "userid " + userid;

    }

    @GET
    @Path("/highscore")
    @Produces("application/json")
    public String getScore(String data) {

        System.out.println(data);

        return data;

    }

    @GET
    @Path("/games")
    @Produces("application/json")
    public String getGames(String data) {

        System.out.println(data);

        return data;

    }

    @GET
    @Path("/result/{gameid}")
    @Produces("application/json")
    public String getGame(@PathParam("gameid") String gameid) {

        System.out.println(gameid);

        return "gameid " + gameid;

    }

    @POST
    @Path("/login/")
    @Produces("text/plain")
    public String login(String data)  {

        System.out.println(data);
        return "OK" ;
    }

    @POST
    @Path("/controls/")
    @Produces("text/plain")
    public String controls(String data)  {

        System.out.println(data);
        return "OK" ;
    }

    @POST
    @Path("/user/")
    @Produces("text/plain")
    public String createUser(String data)  {

        System.out.println(data);
        return "OK" ;
}
    @POST
    @Path("/create")
    @Produces("text/plain")
    public String createGame(String data)  {

        System.out.println(data);
        return "OK" ;
    }

    @POST
    @Path("/start")
    @Produces("text/plain")
    public String startGame(String data)  {

        System.out.println(data);
        return "OK" ;
    }

    @DELETE
    @Path("/user/")
    @Produces("text/plain")
    public String deleteUser(String data)  {

        System.out.println(data);
        return data + " has been deleted" ;
    }

    @DELETE
    @Path("/game/")
    @Produces("text/plain")
    public String deleteGame(String data)  {

        System.out.println(data);
        return data + " has been deleted" ;
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
    }
}
