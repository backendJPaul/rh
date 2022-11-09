package com.jpaul;

import com.jpaul.dao.DAO;
import com.jpaul.dao.DAOManager;
import com.jpaul.dao.impl.DAOUser;
import com.jpaul.model.User;

public class App
{
    public static void main( String[] args ){

        DAOManager daoManager = new DAOManager();
        try{
            User user = new User();
            user.setId(8);
            System.out.println(daoManager.getDAOUser().delete(user));

            /*
            User user = new User();
            user.setId(7);
            user.setName("roxana");
            user.setPassword("root");
                user.setIdRole(3);
             */
            //System.out.println(daoManager.getDAOUser().update(user).toString());
        }
        catch(Exception e){
            System.out.println(e.toString());
        }
    }
}
