package api;

import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.net.httpserver.HttpServer;
import controller.Logic;
import model.Config;
import tui.Tui;

import java.io.IOException;

/**
 * Created by KonnerupsMac on 06/11/2015.
 */
public class Main {

    public static void main(String[] args) throws IOException {

        System.out.println("test");
        Config.init();
        HttpServer server = HttpServerFactory.create("http://localhost:" + Config.getServerPort() + "/");
        server.start();
        System.out.println("Server running");
        System.out.println("Visit: http://localhost:" + Config.getServerPort() + "/api");
        Tui.serverMenu();
        System.out.println("Hit return to confirm to stop...");
        System.in.read();
        System.out.println("Stopping server");
        server.stop(0);
        System.out.println("Server stopped");
    }
}
