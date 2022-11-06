package com.jpaul;

import java.sql.*;

public class DAO {
    protected ResultSet resultSet;
    protected CallableStatement callableStatement;
    protected Connection connection;

    public void connectDatabase()throws Exception{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rh","admin","root");
        }
        catch(Exception e){
            throw new Exception(e.toString());
        }
    }
    public void disconnectDatabase()throws Exception{
        try{
            if(resultSet != null){
                resultSet.close();
            }
            if(callableStatement != null){
                callableStatement.close();
            }
            if(connection != null){
                connection.close();
            }
        }
        catch (Exception e){
            throw new Exception(e.toString());
        }
    }
}
