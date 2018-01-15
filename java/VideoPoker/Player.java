/**
 * This program creates a player class for player to bet in the game and 
 * manage cards in hand 
 * This class will be used in video poker game project
 * 
 * @author: <Elle Chen>
 * @date: <11/4/17>
 * 
 */

import java.util.ArrayList;
import java.util.Collections;

public class Player 
{
		
	private ArrayList<Card> hand; // the player's cards
	private double bankroll;
    private double bet;
    // Initalize 100 tokens for player
    private final double INITIAL_AMOUNT = 100;
		
	public Player(){		
	    // create a player here
	    this.bet = 0;
        this.bankroll = INITIAL_AMOUNT;
        this.hand = new ArrayList<Card>();
	}

	public void addCard(Card c)
    {
	    // add the card c to the player's hand
	    this.hand.add(c);        
        Collections.sort(this.hand);
	}

	public void removeCard(Card c)
    {
	    // remove the card c from the player's hand
	    this.hand.remove(c);
    }
		
    public void bets(double amt)
    {
        // player makes a bet
        // remove the bet amount from bank roll
        this.bet = amt;
        this.bankroll = this.bankroll - bet;
    }

    public void winnings(double odds)
    {
        //	adjust bankroll if player wins
        bankroll = bankroll + odds;
    }

    public double getBankroll()
    {
        // return current balance of bankroll
        return bankroll;
    }

    // Accessor method returns hand 
    public ArrayList<Card> getHand()
    {
        return hand;
    }
    
    // Mutator method to remove play's hand after each play
    public void resetHand()
    {
        this.hand = new ArrayList<Card>();
    }
}
    


