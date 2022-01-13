/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goodmandylanproject;

/**
 *
 * @author Dylan Goodman
 */
public class Dealer {
    
    private Hand dealerHand;
    private boolean stand;
    
    /**
     * Gets the dealers hand
     * @return the dealers hand
     */
    public Hand getHand(){
        return dealerHand;
    }
    
    /**
     * Sets a dealers hand
     * @param hand the hand to be set
     */
    public void setHand(Hand hand){
        dealerHand = hand;
    }
    
    /**
     * Gets whether or not a player stands
     * @return whether or not a player stands
     */
    public boolean getStand(){
        return stand;
    }
    
    /**
     * Sets whether or not a player stands
     * @param s whether or not a player stands
     */
    public void setStand(boolean s){
        stand = s;
    }
}
