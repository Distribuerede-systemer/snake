package database;

import java.sql.SQLException;

public class Database {

    DatabaseDriver db = new DatabaseDriver();

    public void createUser()
    {
        try
        {
            db.getCreateUser().setString(1, "Michael");
            db.getCreateUser().setString(2, "Jan");

            db.getCreateUser().executeUpdate();
        }
        catch ( SQLException sqlException )
        {
            sqlException.printStackTrace();
            db.close();
        }
    }

    
}
