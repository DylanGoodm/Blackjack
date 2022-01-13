/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goodmandylanproject;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Dylan Goodman
 */
public class Blackjack {
    
    private int numPlayers;
    private ArrayList<Player> players = new ArrayList<Player>();
    private Dealer dealer;
    private Deck d;
    private boolean gameOver;
    private boolean turnOver;
    Scanner scan = new Scanner(System.in);
    
    public Blackjack(){
        getInfo();
    }
    
    
    
    /**
     * Gets the number of players in the game
     * @return the number of players in the game
     */
    public int getNumPlayers(){
        return numPlayers;
    }
    
    /**
     * Tells if the game is over
     * @return if the game is over
     */
    public boolean isGameOver(){
        return gameOver;
    }
    
    /**
     * Ends the game
     * @param g if the game is over or not
     */
    public void setGameOver(boolean g){
        gameOver = g;
    }
    
    /**
     * Gets info on the player
     */
    public void getInfo(){
        int balance = 0;
        //gets info from user
        System.out.println("Please enter your name");
        String name = scan.nextLine();
        System.out.println("Enter your balance as a whole number");
        boolean stop = false;
        while(!stop){
            //makes sure a user enters an int
            while(!scan.hasNextInt()){     
                scan.next();
                System.out.println("Invalid input, please enter a whole number");
            }
            balance = scan.nextInt();
            scan.nextLine();
            if(balance == 0){
                System.out.println("I feel bad for you, let me give you a dollar\n");
                balance = 1;
            }
            if (balance > 0){
                break;
            }else{
                System.out.println("You can't have a balance less than 0");
            }
        }
        //creates a new player object with the given info
        Player p = new Player(name, balance);
        players.add(p); 
    }
    
    /**
     * Sets up the game
     */
    public void startGame(){
        //creates the player, dealer and deck
        Hand h = new Hand();
        Hand dh = new Hand();
        players.get(0).setHand(h);
        dealer = new Dealer();
        dealer.setHand(dh);
        d = new Deck();
        d.shuffleDeck();
        deal(d);
    }
    
    /**
     * Takes the actions to play the game
     */
    public void playGame(){
        //takes the players turn
        playTurn(players.get(0));
        //checks who won
        checkWinner();
        System.out.println(players.get(0).getName() + "'s balance is now: $" + players.get(0).getBalance());
        //finds out if the player is broke
        if(players.get(0).getBalance() == 0){
            gameOver = true;
            System.out.println("Looks like you're out of money");
        }
        isGameOver();
    }
    
    /**
     * Deals the cards
     */
    public void deal(Deck d){
        //deals two cards to the player
        for (int i = 0; i < players.size(); i++) {
            players.get(i).getHand().addCard(d.getNextCard());
            d.removeCard();
            players.get(i).getHand().addCard(d.getNextCard());
            d.removeCard();
        }
        //deals two cards to the dealer
        dealer.getHand().addCard(d.getNextCard());
        d.removeCard();
        dealer.getHand().addCard(d.getNextCard());
        d.removeCard();
    }
    
    /**
     * Has a Player take their turn
     * @param player the Player who is taking the turn
     */
    public void playTurn(Player player){
        //has the player bet an amount
        System.out.println(players.get(0).getName() + "'s balance is $" + players.get(0).getBalance());
        System.out.println("Now enter your bet: ");
        //makes sure a user enters an int
        while(!scan.hasNextInt()){     
            scan.next();
            System.out.println("Invalid input, please enter an integer");
        }
        int bet = scan.nextInt();
        boolean stop = false;
        scan.nextLine();
        //only lets a player bet an amount lower than thier balance
        while(!stop){
            if(bet <= players.get(0).getBalance() && bet > 0){
                players.get(0).setBet(bet);
                stop = true;
            //resets if bet is not applicable
            } else if(bet > players.get(0).getBalance()){
                System.out.println("Bet amount exceeds available funds");
                System.out.println("Please bet a value lower than your balance of $" + players.get(0).getBalance() + ": ");
                //makes sure a user enters an int
                while(!scan.hasNextInt()){     
                    scan.next();
                    System.out.println("Invalid input, please enter an integer");
                }
                bet = scan.nextInt();
                scan.nextLine();
            }else{
                System.out.println("Please bet a valid amount");
                //makes sure a user enters an int
                while(!scan.hasNextInt()){     
                    scan.next();
                    System.out.println("Invalid input, please enter an integer");
                }
                bet = scan.nextInt();
                scan.nextLine();
            }
        }
        
        //has the player either hit or stand
        while(!player.getStand()){
            //prints out the players cards and their values
            System.out.println("Your cards are:");
            for (int i = 0; i < player.getHand().getHandCards().size(); i++) {
                if(player.getHand().getHandCards().get(i).getPosition() == 1){
                    System.out.println(" " + player.getHand().getHandCards().get(i) + ": " + "1/11");
                }else{
                    System.out.println(" " + player.getHand().getHandCards().get(i) + ": " + player.getHand().getHandCards().get(i).getPosition());
                }
            }
            //shows the player the dealer's card that is visible
            if(dealer.getHand().showCard().isAce()){
                System.out.println("\nThe dealer's shown card is: " + dealer.getHand().showCard() + ": 1/11");
            }else{
                System.out.println("\nThe dealer's shown card is: " + dealer.getHand().showCard() + ": " + dealer.getHand().showCard().getPosition());
            }
            if(player.getHand().isBlackjack()){
                player.getHand().setCardValue(21);
                break;
            }
            //prompts the user to either hit or stand
            System.out.println("Type 1 to hit\nType 2 to stand");
            //makes sure a user enters an int
            while(!scan.hasNextInt()){     
                scan.next();
                System.out.println("Invalid input, please enter an integer");
            }
            int input = scan.nextInt();
            scan.nextLine();
            switch(input){
                case 1:
                    //if the user hits, adds a card to their hand
                    player.getHand().addCard(d.getNextCard());
                    d.removeCard();
                    //checks to see if player is bust or has 21
                    if(player.checkHand()){
                        player.setStand(true);
                    }
                    break;
                case 2:
                    //handles the if case for aces
                    for (int i = 0; i < player.getHand().getHandCards().size(); i++) {
                        if(player.getHand().getHandCards().get(i).getPosition() == 1){
                            if(player.getHand().getCardValue() + 10 <= 21){
                                player.getHand().setCardValue(player.getHand().getCardValue() + 10);
                            }
                        }
                    }
                    player.setStand(true);  
                    break;
                
            }
            if(!player.getStand()){
                System.out.println("\n\n\nPlease enter 1 or 2\n");
            }
            
        }
   
    }
    
    /**
     * Has the dealer take its turn
     */
    public void dealerTurn(){
        //preset rules for the dealer to follow
        while(!dealer.getStand()){  
            //dealer must stand if card value is 17 or higher
            if(dealer.getHand().getCardValue() >= 17){
                dealer.setStand(true);
            //dealer draws if card value is 17 or lower
            }else{
                dealer.getHand().addCard(d.getNextCard());
                d.removeCard();
            }
        }
    }
    
    /**
     * Prints out the rules of the game
     */
    public void printRules(){
        System.out.println("Hello welcome to Blackjack, here are the rules:");
        System.out.println("The object of this game is to beat the dealers hand in value by getting as close to 21 as possible with your cards");
        System.out.println("Every cards value is equal to its face number(i.e. The two of diamonds = 2) and every card above 10 counts as a value of 10");
        System.out.println("The Ace is a special card which can count as either a 1 or an 11");
        System.out.println("At the beginning of the game the player will bet an amount and then both the player and dealer will be dealt 2 cards with one being face up");
        System.out.println("The player is allowed to ask for a 'hit' or 'stand'");
        System.out.println("If the player chooses to stand they are done with their turn, if they choose hit they get one more card from the deck");
        System.out.println("The round ends for a player when they choose to stand, achieve 21(blackjack) or have their cards value go over 21(bust)");
        System.out.println("After the players turn the dealer will take their turn, choosing to hit below 17 and stand at 17");
        System.out.println("If the player wins they get double the amount of their bet in winnings");
        System.out.println("If the player loses they lose their entire bet");
        System.out.println("Have fun!");
    }
    
    /**
     * Checks for a winner
     */
    public void checkWinner(){
        boolean pBust = false;
        boolean dBust = false;
        //handles whether or not player has blackjack or bust for win handling
        for (int i = 0; i < players.size(); i++) {
            if(players.get(i).getHand().isBlackjack()){
                System.out.println("You have Blackjack!");
                players.get(i).setStand(true);
            }else if(players.get(i).getHand().isBust()){
                players.get(i).setStand(true);
                pBust = true;
            }
            //takes the dealers turn once the user has stood
            if(players.get(i).getStand()){
                dealerTurn();
                turnOver = true;
                //handles if the dealer has bust
                if(dealer.getHand().isBust()){
                    dBust = true;
                }
            }
            //handles the winnings after the turn is over
            if(turnOver){
                System.out.println("Your cards were:");
                //prints out the players cards and their values
                for (int j = 0; j < players.get(i).getHand().getHandCards().size(); j++) {
                    if(players.get(i).getHand().getHandCards().get(j).getPosition() == 1){
                        System.out.println(" " + players.get(i).getHand().getHandCards().get(j) + ": " + "1/11");
                    }else{
                        System.out.println(" " + players.get(i).getHand().getHandCards().get(j) + ": " + players.get(i).getHand().getHandCards().get(j).getPosition());
                    }
                }
                //the dealer and player's hand value is printed
                System.out.println("Player's hand value: " + players.get(i).getHand().getCardValue());
                System.out.println("Dealer's hand value: " + dealer.getHand().getCardValue());
                //determines the outcome of the game depending on who bust or who has the higher hand
                if(pBust && dBust){
                    System.out.println("Its a draw");
                }else if(pBust){
                    System.out.println("You lost this round.");
                    System.out.println("You lost $" + players.get(i).getBet());
                    players.get(i).setBalance(players.get(i).getBalance() - players.get(i).getBet());
                }else if(dBust){
                    System.out.println("You won!");
                    System.out.println("You won $" + players.get(i).getBet());
                    players.get(i).setBalance(players.get(i).getBalance() + players.get(i).getBet());
                }else if(players.get(i).getHand().getCardValue() > dealer.getHand().getCardValue()){
                    System.out.println("You won!");
                    System.out.println("You won $" + players.get(i).getBet());
                    players.get(i).setBalance(players.get(i).getBalance() + players.get(i).getBet());
                }else if(players.get(i).getHand().getCardValue() == dealer.getHand().getCardValue()){
                    System.out.println("Its a draw");
                }else{
                    System.out.println("You lost this round.");
                    System.out.println("You lost $" + players.get(i).getBet());
                    players.get(i).setBalance(players.get(i).getBalance() - players.get(i).getBet());
                }
            }
        }
        turnOver = false;
        players.get(0).setStand(false);

    }
    
    /**
     * Allows a Player to quit the game/used to handle more than one player
     * @param player the Player to quit
     */
    public void quit(Player player){
        
    }
    
    /**
     * Checks if any Players are left in the game/ used to handle more than one player
     * @return whether or not there are any Players left in the game
     */
    public boolean checkPlayers(){
        return false;
    }
    
    
}
