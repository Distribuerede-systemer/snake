package api;

import java.io.IOException;
import java.sql.*;
//import config.config;
//import tui.Logik;
//import database.DatabaseDriver;
//import Config.init

/**
 * Created by Tobias on 15/10/15.
 */

public class Main {

    /**
     * Create an object of tui class.
     * This method starts the program
     */

    public static void main(String[] args) {

        Config.init();

        new DatabaseDriver.dbExist();

        new DatabaseDriver.checkConnection();

        Tui tui = new Tui();0
        tui.getuserMenu();




    }
}

