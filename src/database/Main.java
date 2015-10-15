package database;

import java.sql.ResultSet;
import java.sql.SQLException;
//
public class Main {

    public static void main(String[] args) {

        DatabaseWrapper db = new DatabaseWrapper();


        System.out.print(db.getUser(1));


    }


}