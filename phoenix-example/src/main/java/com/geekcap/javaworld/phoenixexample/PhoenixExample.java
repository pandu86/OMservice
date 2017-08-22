package com.geekcap.javaworld.phoenixexample;

import java.sql.*;
import java.util.Properties;

public class PhoenixExample {

    public static void main(String[] args) {
        // Create variables
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
        	System.out.println("Start");
            // Connect to the database
        	//Properties props = new Properties();
           // props.setProperty("username","root");
           // props.setProperty("password","W1A1JUUd123");
            //connection = DriverManager.getConnection("jdbc:phoenix:57.4.46.121");
        	connection = DriverManager.getConnection("jdbc:phoenix:localhost");
           

            // Create a JDBC statement
            statement = connection.createStatement();

            // Execute our statements
          //  statement.executeUpdate("create table test1 (mykey integer not null primary key, mycolumn varchar)");
            //statement.executeUpdate("CREATE SEQUENCE_1 my_sequence  START WITH 100 INCREMENT BY 2 CACHE 10");
            statement.executeUpdate("create table user_order (id integer not null primary key, mycolumn varchar)");
            statement.executeUpdate("UPSERT INTO user_order(id, mycolumn)VALUES( NEXT VALUE FOR my_sequence, 'foo')"); 
           // statement.executeUpdate("upsert into test1 values (1,'Hello')");
           // statement.executeUpdate("upsert into test1 values (1,'Hello')");
           // statement.executeUpdate("upsert into test1 values (2,'Java Application')");
            connection.commit();

            // Query for table
            ps = connection.prepareStatement("select * from test1");
            rs = ps.executeQuery();
            System.out.println("Table Values");
            while(rs.next()) {
                Integer myKey = rs.getInt(1);
                String myColumn = rs.getString(2);
                System.out.println("\tRow: " + myKey + " = " + myColumn);
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        finally {
            if(ps != null) {
                try {
                    ps.close();
                }
                catch(Exception e) {}
            }
            if(rs != null) {
                try {
                    rs.close();
                }
                catch(Exception e) {}
            }
            if(statement != null) {
                try {
                    statement.close();
                }
                catch(Exception e) {}
            }
            if(connection != null) {
                try {
                    connection.close();
                }
                catch(Exception e) {}
            }
        }
    }
}
