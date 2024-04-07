/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coe528.project;

/**
 *
 * @author ashsu
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class AccountFile {
    
    // Creates a directory to store the individual account files
    private static File directory = new File("Customer Accounts/");
    
    // Method to create the customers file
    public static String createCustomerFile(String username, String password, double balance){
        
        // Creates the directory if it does not exist
        if(directory.exists() == false){
            directory.mkdirs();
        }
   
        // Creates file using customers username
        File file = new File("Customer Accounts/" + username + ".txt");
        
        // If file already exists return this error
        if(file.exists()){
            return "Error: A customer with username '" + username + "' already exists.";
        }
        
        // Write the necessary information of the customer as well as their level
        try(PrintWriter writer = new PrintWriter(new FileWriter(file))){
            writer.println("Username: " + username);
            writer.println("Password: " + password);
            writer.println("Role: customer");
            writer.println("Balance: " + balance);
            if(balance < 10000){
                writer.println("Level: Silver");
            }
            else if (balance >= 10000 && balance < 20000){
                writer.println("Level: Gold");
            }
            else if(balance >= 20000){
                writer.println("Level: Platinum");
            }
            return "Customer file created for User: " + username;
        }
        catch(IOException e){
            return "Error creating customer file: " + e.getMessage();
        }
    }
    
    // Method to remove customer
    public static String removeCustomer(String username){
        File file = new File("Customer Accounts/" + username + ".txt");
        
        // If the file associated with the username exists delete it
        if(file.exists()){
            if(file.delete()){
                return "Customer file successfuly deleted for User: " + username;
            }
            else{
                return "Failed to delete customer file for User: " + username;
            }
        }
        else{
            return "Customer file does not exist for User: " + username;
        }
    }
    
    // Method to retrieve customer username
    public static String getUsername(String username) {
        File file = new File("Customer Accounts/" + username + ".txt");
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith("Username:")) {
                        return line.substring("Username:".length()).trim();
                    }
                }
            } 
            catch (IOException e) {
                System.out.println("Error retrieving username: " + e.getMessage());
            }
        }
        return null; // Return null if the username is not found
    }
    
    // Method to retrieve customer password
    public static String getPassword(String username) {
        File file = new File("Customer Accounts/" + username + ".txt");
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith("Password:")) {
                        return line.substring("Password:".length()).trim();
                    }
                }
            } 
            catch (IOException e) {
                System.out.println("Error retrieving password: " + e.getMessage());
            }
        }
        return null; 
    }

    // Method to retrieve customer role
    public static String getRole(String username) {
        File file = new File("Customer Accounts/" + username + ".txt");
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith("Role:")) {
                        return line.substring("Role:".length()).trim();
                    }
                }
            } 
            catch (IOException e) {
                System.out.println("Error retrieving Role: " + e.getMessage());
            }
        }
        return null; 
    }

    // Method to retrieve customer balance
    public static double getBalance(String username) {
        File file = new File("Customer Accounts/" + username + ".txt");
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith("Balance:")) {
                        String balanceString = line.substring("Balance:".length()).trim();
                        return Double.parseDouble(balanceString);
                    }
                }
            } 
            catch (IOException e) {
                System.out.println("Error retrieving username: " + e.getMessage());
            }
        }
        return 0; 
    }
    
}
