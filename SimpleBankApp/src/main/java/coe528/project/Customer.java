/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coe528.project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Customer extends User {
    
    private String localUser = this.getUsername();
    private Level level;
    
    public Customer(String username, String password, String role) {
        // Use super to retrieve information from the parent class User
        super(username, password, role);
    }
    
    @Override
    public boolean authenticate() {
        // Retrieve customer information from the file
        File file = new File("Customer Accounts/" + localUser + ".txt");
        
        if(!file.exists()){
            System.out.println("This customer does not exist");
            return false;
        }
        
        else{
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                // Read each line in the file
                while ((line = reader.readLine()) != null) {
                    // Check if the line contains the password
                    if (line.startsWith("Password:")) {
                        // Extract the password from the line
                        String storedPassword = line.substring("Password:".length()).trim();
                        // Check if the stored password matches the provided password
                        if (getPassword().equals(storedPassword)) {
                            return true; 
                        }
                    }
                }
            } 
            catch (IOException e) {
                System.out.println("Error retrieving customer information: " + e.getMessage());
            }
        }

        // Authentication failed
        return false;
    }

    // Method to check the balance
    public double checkBalance() {
        File file = new File("Customer Accounts/" + localUser + ".txt");
        double bal = 0;
        
        try(BufferedReader read = new BufferedReader(new FileReader(file))){
           String line;
           while ((line = read.readLine()) != null){
               if(line.startsWith("Balance:")){
                   bal += Double.parseDouble(line.substring("Balance:".length()).trim());
                   return bal;
                   
               }
           }
        }
        catch(IOException e){
            System.out.println("Error retrieving customer information: " + e.getMessage());
        }
        return 0;
    }
    
    // Method to deposit
    public boolean deposit(double amount) {
        File file = new File("Customer Accounts/"+ localUser + ".txt");
        double oldBal = 0;
        double newBal;
        
        if(!file.exists()){
            System.out.println("Error: Account file not found for user " + localUser);
        }
        
        try(BufferedReader read = new BufferedReader(new FileReader(file))){
           String line;
           StringBuilder content = new StringBuilder();
           
           while ((line = read.readLine()) != null){
               if(line.startsWith("Balance: ")){
                   oldBal = Double.parseDouble(line.substring("Balance: ".length()).trim());
               }
               content.append(line).append("\n");
           }
           
           newBal = oldBal + amount;
           changeLevel();
           String update = content.toString().replaceAll("Balance: " + oldBal, "Balance: " + newBal);
           
           try(PrintWriter writer = new PrintWriter(new FileWriter(file))){
               writer.print(update);
               return true;
           }
        }
        
        catch(IOException e){
            System.out.println("Error retrieving customer information: " + e.getMessage());
            return false;
        }
    }
     
    public Level getLevel() {
        double balance = checkBalance();
        Level level;
        if (balance >= 20000) {
            level = new Platinum();
        } 
        else if (balance >= 10000) {
            level = new Gold();
        } 
        else {
            level = new Silver();
        }
        return level;
    }
    
    public double getFee(){
        Level customerLevel = getLevel();
        return customerLevel.getFee();
    }

    public void changeLevel(){
        Level newLevel = getLevel();
        Level currentLevel = level;
        
        level = newLevel;
        
        // Update the customer's level in the file
        File file = new File("Customer Accounts/" + localUser + ".txt");
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                StringBuilder content = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith("Level:")) {
                        content.append("Level: ").append(newLevel).append("\n");
                    } 
                    else {
                        content.append(line).append("\n");
                    }
                }
                // Write the updated content back to the file
                try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
                    writer.print(content);
                }
            } 
            catch (IOException e) {
                System.out.println("Error updating customer level in file: " + e.getMessage());
            }
        } 
        else {
            System.out.println("Error: Customer file not found.");
        }
    
    }
    
    // Method to withdraw money from the account
    public boolean withdraw(double amount) {
        File file = new File("Customer Accounts/"+ localUser + ".txt");
        double oldBal = 0;
        double newBal;
        
        if(!file.exists()){
            System.out.println("Error: Account file not found for user " + localUser);
        }
        
        try(BufferedReader read = new BufferedReader(new FileReader(file))){
           String line;
           StringBuilder content = new StringBuilder();
           
           while ((line = read.readLine()) != null){
               if(line.startsWith("Balance: ")){
                   oldBal = Double.parseDouble(line.substring("Balance: ".length()).trim());
               }
               content.append(line).append("\n");
           }
           
           newBal = oldBal - amount;
           if(newBal < 0){
               System.out.println("Cannot withdraw this amount as your balance becomes negative");
               return false;
           }
           
           // Call changelevel after withdrawing to see if level needs to be changed
           changeLevel();
           String update = content.toString().replaceAll("Balance: " + oldBal, "Balance: " + newBal);
           
           try(PrintWriter writer = new PrintWriter(new FileWriter(file))){
               writer.print(update);
               return true;
           }
        }
        
        catch(IOException e){
            System.out.println("Error retrieving customer information: " + e.getMessage());
            return false;
        }
    }
}



