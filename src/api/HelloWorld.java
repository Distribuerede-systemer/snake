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
        return "Hello World";
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

    @POST
    @Path("/user/")
    @Produces("text/plain")
    public String createUser(String data)  {

        System.out.println(data);
        return "OK" ;
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
