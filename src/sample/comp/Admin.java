package sample.comp;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Admin {
    public int id;
    public String name;
    public String surname;
    public String login;

    public Admin() {
        id = 1;
        name = "Michal";
        surname = "Halo";
        login = "mhalo";
    }

    public Admin(ResultSet rs) throws SQLException {
        id = rs.getInt("id");
        name = rs.getString("name");
        surname = rs.getString("surname");
        login = rs.getString("login");
    }
}
