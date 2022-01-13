/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goodmandylanproject;


import java.util.Scanner;

/**
 *
 * @author Dylan Goodman
 */
public class GoodmanDylanProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        /* deck() Creates a 52 card deck, which uses an ArrayList of class Card
        *  playGame() Handles playing of the game, starting of methods and determining if the game is over
        *  playTurn()/dealerTurn() Has the each person take their turn, betting/hitting/standing 
        *  checkWinner() Determines the outcome of the games based on the results
        *  getInfo()/startGame() Set up information and other necessary things for the game i.e. creating players/deck/dealer, shuffling deck/dealing cards
        */
        
        Scanner scan = new Scanner(System.in);
        System.out.println("Blackjack Test Harness: ");
        System.out.println(" 1. Start test");
        System.out.println(" 2. Quit program");
        boolean stop = false;
        
        while(!stop){
            while(!scan.hasNextInt()){     
                scan.next();
                System.out.println("Invalid input, please enter a whole number");
            }
            int inputNum = scan.nextInt();
            scan.nextLine();
            switch(inputNum){
                case 1:
                    Blackjack b = new Blackjack();
                    b.printRules();
                    //loops each round
                    while(!b.isGameOver()){
                        b.startGame();
                        b.playGame();
                        //asks user if they would like to exit
                        System.out.println("Would you like to continue y/n");
                        String input = scan.next();
                        scan.nextLine();
                        if(input.equals("n") || input.equals("N")){
                            break;
                        }
                    }
                    stop = true;
                    break;
                case 2:
                    scan.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Enter a valid menu option");
            }   
                
        }
        scan.close();
        System.out.println("Thanks for playing!");
        
        
    }//end main
    
}
