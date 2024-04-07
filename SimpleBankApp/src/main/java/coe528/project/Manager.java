/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coe528.project;


/**
 *
 * @author ashsu
 */
public class Manager extends User{
    
    public Manager(String username, String password, String role){
        // Use super to retrieve information from the parent class User
        super(username,password,role);
    }

    @Override
    public boolean authenticate() {
        if(this.username.equals("admin") && this.password.equals("admin") && this.role.equals("manager")) 
            return true;
        else
            return false;
    }

    public String add(String username, String password, double balance){
        String message = AccountFile.createCustomerFile(username, password, balance);
        return message;
    }
    
    public String remove(String username){
        String message = AccountFile.removeCustomer(username);
        return message;
    }
    
}
