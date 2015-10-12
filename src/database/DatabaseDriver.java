package database;

import com.sun.rowset.CachedRowSetImpl;

import javax.xml.transform.Result;
import java.sql.*;

public class DatabaseDriver {

//    public String create() {
//
//    }

    public String read(String table) {

        String sqlStatement = "select * from " + table;

        return sqlStatement;
    }
}
