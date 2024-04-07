/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coe528.project;

/**
 *
 * @author ashsu
 */
public abstract class Level {
    public abstract double getFee();
    
    public abstract void changeLevel(Customer c);
    
    public abstract String toString();
}
