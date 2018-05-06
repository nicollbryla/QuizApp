package sample.comp;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Admin {
    private int id;
    private String name;
    private String surname;
    private String login;

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
