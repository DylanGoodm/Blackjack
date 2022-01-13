/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goodmandylanproject;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Dylan Goodman
 */
public class Deck {
    
    private ArrayList<Card> cards = new ArrayList<Card>();
    private Card nextCard;
    
    /**
     * creates a new 52 card deck
     */
    public Deck(){
        String suit;
        String name;
        int pos;
        //names for all cards
        String[] names = {"Ace", "Two", "Three", "Four", "Five", "Six", "Seven",
                         "Eight", "Nine", "Ten", "Jack", "Queen", "King"};
        //creats a card for each value
        for (int i = 0; i < 13; i++) {
            pos = i + 1;
            name = names[i];
            //assigsns jack - king with position 10
            if(i > 9){
                pos = 10;
            }
            //creates 4 of the same card with different suits
            for (int j = 0; j < 4; j++) {
               //
               if(j == 0){
                   suit = "spades";
               }else if(j == 1){
                   suit = "hearts";
               }else if(j == 2){
                   suit = "diamonds";
               }else {
                   suit = "clubs";
               }
               //adds the new card to the deck
               cards.add(new Card(name, pos, suit));
            }   
        }
    }
    
    /**
     * Shuffles the deck ArrayList
     */
    public void shuffleDeck(){
        Collections.shuffle(cards);
    }
    
    /**
     * Deals a card to a players hand
     * @param hand the hand for the card to be dealt to
     */
    public void dealCard(Hand hand){
        hand.addCard(nextCard);
        cards.remove(nextCard);
    }
    
    /**
     * Removes a card from the deck
     */
    public void removeCard(){
        cards.remove(0);
    }
    
    /**
     * Gets the next card in the deck
     * @return the next card in the deck
     */
    public Card getNextCard(){
        return cards.get(0);
    }
    
}
