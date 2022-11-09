package com.jpaul.dao;

public class DAO {

    protected java.sql.Connection connection;
    protected java.sql.CallableStatement callableStatement;
    protected java.sql.ResultSet resultSet;

    protected void connectDatabase()throws Exception{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/rh","admin","root");
        }
        catch(Exception e){
            throw new Exception(e.toString());
        }
    }
    public void disconnectDatase() throws Exception {
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
        catch(Exception e){
            throw new Exception(e.toString());
        }

    }
}
