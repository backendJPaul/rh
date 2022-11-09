package com.jpaul.dao.impl;

import com.jpaul.dao.DAO;
import com.jpaul.dao.IDAO;
import com.jpaul.dao.IDAOUser;
import com.jpaul.model.User;

import java.util.ArrayList;
import java.util.List;

public class DAOUser extends DAO implements IDAOUser {
    @Override
    public List<User> fetch() throws Exception {
        this.connectDatabase();
        this.callableStatement = this.connection.prepareCall("call fetchUser(?)");
        this.callableStatement.setBoolean(1,true);

        return setAll();
    }


    @Override
    public User gotoId(User o) throws Exception {
        this.connectDatabase();
        this.callableStatement = this.connection.prepareCall("call gotoUserId(?,?)");
        this.callableStatement.setBoolean(1,true);
        this.callableStatement.setInt(2,o.getId());

        return set();
    }

    @Override
    public User save(User o) throws Exception {
        this.connectDatabase();
        this.callableStatement = this.connection.prepareCall("call saveUser(?,?,?)");
        this.callableStatement.setString(1, o.getName());
        this.callableStatement.setString(2, o.getPassword());
        this.callableStatement.setString(3, o.getRole());

        return set();
    }

    @Override
    public List<User> search(String pattern) throws Exception {

        this.connectDatabase();
        this.callableStatement = connection.prepareCall("call searchUser(?)");
        this.callableStatement.setString(1,pattern);
        return setAll();
    }

    @Override
    public User update(User o) throws Exception {

        this.connectDatabase();
        this.callableStatement = this.connection.prepareCall("call updateUser(?,?,?,?)");
        this.callableStatement.setInt(1,o.getId());
        this.callableStatement.setString(2,o.getName());
        this.callableStatement.setString(3,o.getPassword());
        this.callableStatement.setInt(4,o.getIdRole());
        return set();
    }

    @Override
    public User delete(User o) throws Exception {
        this.connectDatabase();
        this.callableStatement = connection.prepareCall("call deleteUser(?)");
        this.callableStatement.setInt(1,o.getId());

        return set();
    }



    @Override
    public User set()throws Exception {

        this.resultSet = this.callableStatement.executeQuery();
        this.resultSet.next();

        User user = new User();
        user.setId(this.resultSet.getInt("id"));
        user.setName(this.resultSet.getString("name"));
        user.setPassword(this.resultSet.getString("password"));
        user.setRole(this.resultSet.getString("role"));

        return user;
    }

    @Override
    public List<User> setAll()throws Exception {

        this.resultSet = this.callableStatement.executeQuery();

        ArrayList<User> userArrayList = new ArrayList<User>();

        while(this.resultSet.next()){
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setName(resultSet.getString("name"));
            user.setPassword(resultSet.getString("password"));
            user.setRole(resultSet.getString("role"));
            userArrayList.add(user);
        }
        return userArrayList;
    }
}