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
public class Player {
    
    private String name;
    private int balance;
    private int bet;
    private Hand playerHand;
    private boolean stand;
    
    /**
     * Initializes the player with a name and balance
     * @param name players name
     * @param balance players balance
     */
    public Player(String name, int balance){
        this.name = name;
        this.balance = balance;
        
    }
    
    /**
     * Gers the contents of name
     * @return the contents of name
     */
    public String getName(){
        return name;
    }
    
    /**
     * Gets the players hand
     * @return the players hand
     */
    public Hand getHand(){
        return playerHand;
    }
    
    public void setHand(Hand hand){
        playerHand = hand;
    }
    
    /**
     * Gets the amount in bet
     * @return the amount in bet
     */
    public int getBet(){
        return bet;
    }
    
    public void setBet(int bet){
        this.bet = bet;
    }
    
    /**
     * Gets the amount in balance
     * @return The amount in balance
     */
    public int getBalance(){
        return balance;
    }
    
    /**
     * Sets the balance for a player
     * @param bal the balance
     */
    public void setBalance(int bal){
        balance = bal;
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
    
    /**
     * Checks the hand for blackjack or bust
     * @return if hand contains blackjack or bust
     */
    public boolean checkHand(){
        if (playerHand.isBlackjack()){
            return true;
        }else if(playerHand.isBust()){
            return true;
        }else{
            return false;
        }
    }
    
}
