package api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import logic.Tui;

/**
 * Created by tobiasjeremiassen on 12/10/15.
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

    //DELETE and replace with config

    private static final String sqlUrl = "jdbc:mysql://localhost:10321/";
    private static final String sqlUser = "root";
    private static final String sqlPassword = "";

    private static Connection connection = null;

    public static void main(String[] args) throws IOException {


        /**
         * Check if the connection to the Database is valid.
         *
         */

        try {

            connection = DriverManager.getConnection(sqlUrl, sqlUser, sqlPassword);

            //Returns true if the connection has not been closed and is still valid.
            // The driver shall submit a query on the connection or use some other mechanism that
            // positively verifies the connection is still valid when this method is called.

            if (connection.isValid(1000)) {

                //If the database is valid, print "DB ok"
                System.out.println("DB is running! cool");

            } else {

                try {

                    CSVLoader loader = new CSVLoader(getCon());

                    loader.loadCSV("C:\\employee.sql", "CUSTOMER", true);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            }


        } catch (SQLException e) {

            //handle the exception
            e.printStackTrace();

            System.exit(1);
        }
    }
}






/*
//tere

        HttpServer server = HttpServerFactory.create("http://localhost:9998/");
        server.start();

        System.out.println("Server running");
        System.out.println("Visit: http://localhost:9998/helloworld");
        System.out.println("Hit return to stop...");
        System.in.read();
        System.out.println("Stopping server");
        server.stop(0);
        System.out.println("Server stopped");
    }

*/

    //Start the program
     // new Tui();


/*    Tui tui = new Tui();
    tui.start();*/





//TEST !"#!"€!"

