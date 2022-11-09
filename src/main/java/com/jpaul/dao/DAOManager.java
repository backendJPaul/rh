package com.jpaul.dao;

import com.jpaul.dao.impl.DAOUser;

public class DAOManager {
    private DAOUser daoUser = null;

    public DAOUser getDAOUser(){
        return daoUser = new DAOUser();
    }


}
