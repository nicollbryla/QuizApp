package main.java.sample.model;

import java.sql.*;
import java.util.Properties;

public class Database {
    private static String dbUrl;
    private static String dbUser;
    private static String dbPass;

    public Connection connect;
    public Statement stmt;
    public ResultSet rs;
    public PreparedStatement preparedStatement;
    public boolean insert;
    public boolean  update;

    public Database(){
        connect = null;
        stmt = null;
    }

    public static void loadParams(Properties properties){
        String host = properties.getProperty("db_host");
        String port = properties.getProperty("db_port");
        String database = properties.getProperty("db_name");
        dbUser = properties.getProperty("db_user");
        dbPass = properties.getProperty("db_pass");
        dbUrl = "jdbc:postgresql://" + host + ":" + port + "/" + database;
    }

    public ResultSet dbselect(String query) throws db_exception {
        try {
            insert = false;
            Class.forName("org.postgresql.Driver");
            connect = DriverManager.getConnection(dbUrl, dbUser, dbPass);
            connect.setAutoCommit(false);
            stmt = connect.createStatement();
            rs = stmt.executeQuery(query);
            return rs;
        } catch (Exception e) {
            e.printStackTrace();
            throw new db_exception();
        }
    }

    public void insert(String query) {
        try {
            insert = false;
            Class.forName("org.postgresql.Driver");
            connect = DriverManager.getConnection(dbUrl, dbUser, dbPass);
            connect.setAutoCommit(false);
            stmt = connect.createStatement();
            stmt.executeUpdate(query);
            insert = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Integer setInt, String setString ) {
        try {
            update = true;
            insert = false;
            Class.forName("org.postgresql.Driver");
            connect = DriverManager.getConnection(dbUrl, dbUser, dbPass);
            String query = "UPDATE player SET score = score + ? WHERE login = ?";
             preparedStatement = connect.prepareStatement(query);
            preparedStatement.setInt(1,setInt);
            preparedStatement.setString(2,setString);
            preparedStatement.executeUpdate();
            connect.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void close() throws SQLException{
        if (insert){
            stmt.close();
            connect.commit();
        } else if(update){
            preparedStatement.close();
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