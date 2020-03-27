package com.sample.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

public class JDBCConnection {

    public static Connection connection = null;
    public static Map<String,String>  databaseProperties = YamlMapParser.getDbproperties();
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(databaseProperties.get("driverClass"));
        connection= DriverManager.getConnection(
                databaseProperties.get("url"),databaseProperties.get("user"),databaseProperties.get("password"));

        return connection;
    }

    public static void connectionHealthCheck() throws SQLException, ClassNotFoundException {
        Connection con = getConnection();
        if(con !=null){
            System.out.println("Connection established"+con.getClass().getName());
        }else{
            System.out.println("Connection not established!!!");
        }
    }

}
