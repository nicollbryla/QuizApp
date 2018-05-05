package sample.comp;

import java.sql.*;

public class Database {
    public Connection connect;
    public Statement stmt;
    public ResultSet rs;
    public boolean insert;

    public Database(){
        connect = null;
        stmt = null;
    }

    public ResultSet dbselect(String query) throws db_exception {
        try {
            insert = false;
            Class.forName("org.postgresql.Driver");
            connect = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "niko");
            connect.setAutoCommit(false);
            stmt = connect.createStatement();
            rs = stmt.executeQuery(query);
            return rs;
        } catch (Exception e) {
            System.out.println(e);
            throw new db_exception();
        }
    }

    public void insert(String query) throws db_exception{
        try {
            insert = false;
            Class.forName("org.postgresql.Driver");
            connect = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "niko");
            connect.setAutoCommit(false);
            stmt = connect.createStatement();
            stmt.executeUpdate(query);
            System.out.println("halkoasinfoisabni");
            insert = true;
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void close() throws SQLException{
        if (insert){
            stmt.close();
            connect.commit();
        }
        else {
            stmt.close();
            rs.close();
        }
        connect.close();
    }

    public static class db_exception extends Exception {
    }
}