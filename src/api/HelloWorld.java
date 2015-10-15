package api;

import com.sun.net.httpserver.HttpServer;
import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.google.gson.Gson;
import java.io.IOException;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;



// The Java class will be hosted at the URI path "/helloworld more comment"
@Path("/api") // Creates an api Path, which annotates the URI Path, for every single method.
public class HelloWorld {
    // The Java method will process HTTP GET requests
    @GET //"GET-Request" Requesting specific data set.
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("text/plain")
    public String getClichedMessage() {
        // Return some cliched textual content
        return "Hello World!";
    }

    @GET //"GET-request"  Requesting specific data set.
    @Path("/user/") //Creates an USER-path - annotation to the URI-path.
    @Produces("application/json")
    public String getAllUsers() {

        //TODO; Hent brugere fra DB
        return "users";
    }

    /**
     *
     * @param username the user's username
     * @return - Returning the username
     */

    @GET //"GET-request"  Requesting specific data set.
    @Produces("application/json")
    public String getUser(@PathParam("userid") String username) {

        System.out.println(userid);
        //Printing/Identifying data among users
        return "userid " + userid;
        //returning the userid if correct
    }

    /**
     *
     * @param data - Receives data of the user's highscore
     * @return - Returns data highscore
     */

    @GET //"GET-request"  Requesting specific data set.
    @Path("/highscore") //Creates a highscore-path - annotation to the URI-path.
    @Produces("application/json")
    public String getScore(String data) {

        System.out.println(data);
        //Printing/identifying highscore data

        return data;

    }

    /**
     *
     * @param data - Receives game data
     * @return all games
     */
    @GET //"GET-request"  Requesting specific data set.
    @Path("/games") //Creates a games-path - annotation to the URI-path.
    @Produces("application/json")
    public String getGames(String data) {

        System.out.println(data);
        //Printing/identifying game data
        return data;
    }

    /**
     *
     * @param gameid requesting the specific gameid
     * @return the gameid
     */
    @GET //"GET-request"  Requesting specific data set.
    @Path("/result/{gameid}") //Creates a gameid-path - annotation to the URI-path.
    @Produces("application/json")
    public String getGame(@PathParam("gameid") String gameid) {

        Game game1 = new Game();
        game1.setGamename("Diablo");
        game1.setResult(100);

        System.out.println(gameid);

        return new Gson().toJson(game1);

    }

    /**
     *
     * @param data login with data
     * @return OK if connected
     */
    @POST //"POST-request" new data - in this case to log-in
    @Path("/login/") //Creates a login-path - annotation to the URI-path.
    @Produces("application/json")
    public String login(String data)  {

        System.out.println(data);
        return "OK" ;

        //If username & password correct = acceptance.
    }

    /**
     *
     * @param json response control via json
     * @return response status
     */

    @POST //"POST-request" new data - in this case to input controls, to control the game
    @Path("/controls/") //Creates a control-path - annotation to the URI-path.
    @Produces("application/json")
    public Response controls (String json) {
        // public String controls(String data)  {

        Control control1 = new Gson().fromJson(json, Control.class);
        //  JSON to GSON statement, the line makes our JSON script to Javascript code
        System.out.println(control1.getMovement());

        if (control1.getMovement().equals("w"))
            return Response.status(201).entity("Success").build();

            if (control1.getMovement().equals("a")) {
            return Response.status(201).entity("Success").build();

        }   if (control1.getMovement().equals("s")) {
            return Response.status(201).entity("Success").build();

        }   if (control1.getMovement().equals("d")) {
            return Response.status(201).entity("Success").build();

        }
        else { return Response.status(500).entity("Fail").build();
            //An If-else statement, for the single inputs, as if the value(s) gets denied, an error will be printed.
        }

       // System.out.println(data);
        //return "OK" ;
    }

    /**
     *
     * @param data creates user
     * @return OK if created
     */

    @POST //POST-request: New data; Creates a new user.
    @Path("/user/") //Creates/ In the user-path - annotation to the URI-path.
    @Produces("text/plain")
    public String createUser(String data)  {

        System.out.println(data);
        return "OK" ;
}

    /**
     *
     * @param data creates game makes it to data
     * @return ok if created
     */
    @POST //POST-request: New data; creates game.
    @Path("/create") //Creates a create-path - annotation to the URI-path.
    @Produces("text/plain")
    public String createGame(String data)  {

        System.out.println(data);
        return "OK" ;
    }


    /**
     *
     * @param data starting game
     * @return OK if started
     */
    @POST //POST-request: New data; Starting a new game.
    @Path("/start") //Creates a start-path - annotation to the URI-path.
    @Produces("text/plain")
    public String startGame(String data)  {

        System.out.println(data);
        return "OK" ;
    }

    /**
     *
     * @param data delete user data
     * @return confirms deleted user
     */
    @DELETE //DELETE-request; Removes an user: Delete user
    @Path("/user/") //Creates/In the user-path - annotation to the URI-path.
    @Produces("text/plain")
    public String deleteUser(String data)  {

        System.out.println(data);
        return data + " has been deleted" ;
    }

    /**
     *
     * @param data delete game data
     * @return confirms deleted game
     */
    @DELETE //DELETE-request Removing data; delete game.
    @Path("/game/") //Creates a game-path - annotation to the URI-path.
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
        //Printing Server status
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