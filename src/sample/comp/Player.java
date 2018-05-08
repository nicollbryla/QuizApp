package sample.comp;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Player {
    public int id;
    public String name;
    public String surname;
    public String login;
    public int score;

    public Player(){
        id = 1;
        name = "Jan";
        surname = "Opat";
        login = "jn";
        score = 0;
    }
    public Player(ResultSet rs) throws SQLException {
        id = rs.getInt("id");
        name = rs.getString("name");
        surname = rs.getString("surname");
        login = rs.getString("login");
        score = 0;
    }
}