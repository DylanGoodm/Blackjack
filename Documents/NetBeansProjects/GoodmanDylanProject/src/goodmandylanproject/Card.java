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
public class Card {
    
    private String name;
    private int position;
    private String suit;
    private boolean isAce;
    
    /**
     * Creates a card with name suit and position/number
     * @param nm Name of the card
     * @param pos Position of the card
     * @param st Suit of the card
     */
    public Card(String nm, int pos, String st){
        name = nm;
        position = pos;
        suit = st;
    }
    
    /**
     * Gets contents of name
     * @return contents of name
     */
    public String getName(){
        return name;
    }
    
    /**
     * Gets the value of position
     * @return the value of position
     */
    public int getPosition(){
        return position;
    }
    
    /**
     * Gets contents of suit
     * @return contents of suit
     */
    public String getSuit(){
        return suit;
    }
    
    /**
     * Tells whether or not a card is an ace
     * @return whether or not a card is an ace
     */
    public boolean isAce(){
        if(name == "Ace")
            return true;
        else
            return false;
    }
    
    /**
     * Prints out a card
     * @return the card
     */
    @Override
    public String toString(){
        return name + " of " + suit;
    }
    
    
}
