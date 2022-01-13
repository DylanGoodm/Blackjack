/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goodmandylanproject;

import java.util.ArrayList;

/**
 *
 * @author Dylan Goodman
 */
public class Hand {
    
    private ArrayList<Card> cards = new ArrayList<Card>();
    private int numCards;
    private int cardValue;
    
    
    /**
     * Gets the value of numCards
     * @return the value of numCards
     */
    public int getNumCards(){
        return numCards;
    }
    
    /**
     * Gets the value of the cards in the hand
     * @return the value of cards in the hand
     */
    public int getCardValue(){
        return cardValue;
    }
    
    /**
     * Sets the value of cards in the hand
     * @param value the value to set the cards
     */
    public void setCardValue(int value){
        cardValue = value;
    }
    
    /**
     * Gets the ArrayList containing the cards
     * @return the ArrayList containing the cards
     */
    public ArrayList<Card> getHandCards(){
        return cards;
    }
    
    /**
     * Adds a card to the hands ArrayList
     * @param card the card to be added
     */
    public void addCard(Card card){
        cards.add(card);
        numCards++;
        cardValue += card.getPosition();
    }
    
    /**
     * Tells which card should be visible to others
     */
    public Card showCard(){
        return cards.get(0);
    }
    
    /**
     * Finds out if a player has blackjack
     * @return whether or not the player has blackjack
     */
    public boolean isBlackjack(){
        boolean ace = false;
        //checks hand for an ace
        for (int i = 0; i < cards.size(); i++) {
            if(cards.get(i).isAce()){
                ace = true;
            }     
        }
        //if cards value is already 21 its blackjack
        if(cardValue == 21){
            return true;
        //if ace in hand(ace value = 1)makes it an 11 to check for blackjack
        }else if(ace && cardValue + 10 == 21){
            return true;
        }else{
            return false;
        } 
    }
    
    /**
     * Finds out if a player has bust
     * @return whether of not a player has bust
     */
    public boolean isBust(){
        if(cardValue > 21){
            return true;
        }else{
            return false;
        }
    }
    
}
