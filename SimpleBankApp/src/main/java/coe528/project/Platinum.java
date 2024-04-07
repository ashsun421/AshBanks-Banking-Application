package coe528.project;

/**
 *
 * @author ashsu
 */

// Run this file indviudally to show repOk() and toString() functionality
public class Platinum extends Level{
    
    /**
     * OVERVIEW: Platinum is a subclass of Level and represents the highest level a
     * customer can receive. This level allows for the customer to have no additional
     * fees charged when online shopping. This class is mutable as it modifies 
     * the users level.
     * 
     * ABSTRACTION FUNCTION: Abstraction will always return the level of the Account.
     * 
     * REP INVARIANT: The fee for the Platinum level must always be $0.
     */
    
    private final double fee = 0;

    /** 
     * REQUIRES: None.
     * MODIFIES: None.
     * EFFECTS: Returns the fee associated with the Platinum level, which is always $0.
     */
    @Override
    public double getFee() {
        return this.fee;
    }
    
    /**
     * REQUIRES: The customer object being used as a parameter cannot be null.
     * MODIFIES: The level of the customer may change based on their balance.
     * EFFECTS: Changes the level of the customer to Gold if their balance is 
     *          between $10,000 and $20,000, to Silver if their balance if less than
     *          $10,000, and remaining Platinum if balance is greater than $20,000.
     */
    @Override
    public void changeLevel(Customer c) {
        if(c != null){
            if(c.checkBalance() >= 10000 && c.checkBalance() < 20000){
                Level l = new Gold();

            }
            else if(c.checkBalance() < 10000){
                Level l = new Silver();
            }

        }
    }
    
    /**
     * REQUIRES: None.
     * MODIFIES: None.
     * EFFECTS: Returns the Platinum level as a String.
     */
    @Override
    public String toString() {
        return "Platinum " + fee;
    }
    
    /**
     * REQUIRES: None.
     * MODIFIES: None.
     * EFFECTS: Returns true if rep invariant holds, else returns false.
     */
    public boolean repOk(){
        if(fee != 0){
            return false;
        }
        else{
            return true;
        }
    }
    
    public static void main(String[] args) {
        // Create an instance of Platinum
        Platinum platinumLevel = new Platinum();

        // Check the toString() method
        System.out.println("Result from the toString(): " + platinumLevel.toString());

        // Check the repOk() method
        System.out.println("Result from the repOk(): " + platinumLevel.repOk());
    }
    
}




