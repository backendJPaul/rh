package com.jpaul;

public class UserDAO extends DAO {

    public void saveUser() throws Exception{
        this.connectDatabase();
        this.callableStatement = connection.prepareCall("call saveUser(?,?,?)");
        this.callableStatement.setString(1,"priscila");
        this.callableStatement.setString(2,"ticlavilca");
        this.callableStatement.setInt(3,3);

        this.resultSet = this.callableStatement.executeQuery();
        this.resultSet.next();

        User user = new User();
        user.setId(this.resultSet.getLong("id"));
        user.setName(this.resultSet.getString("name"));
        user.setPassword(this.resultSet.getString("password"));
        user.setRole(this.resultSet.getString("role"));

        System.out.println(user.toString());



    }

}
