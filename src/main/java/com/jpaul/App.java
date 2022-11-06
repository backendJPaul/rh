package com.jpaul;

public class App 
{
    public static void main( String[] args ){
        UserDAO userDAO = new UserDAO();
        try{
            userDAO.saveUser();
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
    }
}
