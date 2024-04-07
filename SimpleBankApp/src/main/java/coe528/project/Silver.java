/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coe528.project;

/**
 *
 * @author ashsu
 */
public class Silver extends Level{
    
    private final double fee = 20;
    
    @Override
    public double getFee() {
        return this.fee;
    }
    
    @Override
    public void changeLevel(Customer c) {
        if(c != null){
            if(c.checkBalance() >= 10000 && c.checkBalance() < 20000){
                Level l = new Gold();

            }
            else if(c.checkBalance() >= 20000){
                Level l = new Platinum();
            }
        }
    }

    @Override
    public String toString() {
        return "Silver";
    }
    
}
