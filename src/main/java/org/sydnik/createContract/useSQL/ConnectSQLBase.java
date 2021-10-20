package org.sydnik.createContract.useSQL;

import java.sql.*;

public class ConnectSQLBase {
    private final String URL = "jdbc:mysql://localhost:3306/mydbtest";
    private final String USERNAME = "root";
    private final String PASSWORD = "root";
    private static ConnectSQLBase instance;
    private Driver driver;
    private Connection connection;

    private ConnectSQLBase(){
        try {
            driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    public static ConnectSQLBase getInstance (){
        if(instance==null){
            instance = new ConnectSQLBase();
        }
        return instance;
    }

    public Connection getConnection() {
        try {
        if(connection==null || connection.isClosed()){

                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                return connection;
        }
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        return connection;
    }
    public Statement getStatement(){
        try {
            return getConnection().createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }


    public void closeConnection() throws SQLException {
        if(connection!=null && !connection.isClosed()){
            connection.close();
        }
    }

}
