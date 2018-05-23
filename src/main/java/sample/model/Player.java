package sample.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Player {
    public int id;
    public String name;
    public String surname;
    public String login;
    public int score;
    public boolean asGuest;
    public int scoreDuringGame = 0;

    public Player(){
        id = 1;
        name = "Jan";
        surname = "Opat";
        login = "jn";
        score = 0;
        asGuest = false;
    }

    public Player(boolean asGuest) {
        name = "Jan";
        surname = "Opat";
        login = "jn";
        score = 0;
        this.asGuest = asGuest;
    }

    public Player(String name){
        this.name = name;
        score = 0;
    }

    public Player(ResultSet rs) throws SQLException {
        id = rs.getInt("id");
        name = rs.getString("name");
        surname = rs.getString("surname");
        login = rs.getString("login");
        score = rs.getInt("score");

    }

    /*public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append(name + ' ' + Integer.toString(score));
        return builder.toString();
    }*/

}