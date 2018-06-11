package sample.model;

import java.sql.*;
import java.util.Properties;

public class Database {
    private static String dbUrl;
    private static String dbUser;
    private static String dbPass;

    private Connection connect;
    private Statement stmt;

    public Database() {
        try {
            Class.forName("org.postgresql.Driver");
            connect = DriverManager.getConnection(dbUrl, dbUser, dbPass);
            connect.setAutoCommit(false);
        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    static void loadParams(Properties properties){
        String host = properties.getProperty("db_host");
        String port = properties.getProperty("db_port");
        String database = properties.getProperty("db_name");
        dbUser = properties.getProperty("db_user");
        dbPass = properties.getProperty("db_pass");
        dbUrl = "jdbc:postgresql://" + host + ":" + port + "/" + database;
    }

    public ResultSet dbselect(String query) throws db_exception {
        try {
            stmt = connect.createStatement();
            return stmt.executeQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
            throw new db_exception();
        }
    }

    public void executeUpdate(String query) {
        try {
            stmt = connect.createStatement();
            stmt.executeUpdate(query);
            connect.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateScore(Integer setInt, String setString ) {
        try {
            String query = "UPDATE player SET score = score + ? WHERE login = ?";//TODO
            PreparedStatement preparedStatement = connect.prepareStatement(query);
            preparedStatement.setInt(1, setInt);
            preparedStatement.setString(2, setString);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close() throws SQLException{
        connect.close();
    }

    public static class db_exception extends Exception {
    }
}