package main.java.sample.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Admin {
    private int id;
    private String login;
    private String name;
    private String surname;

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
