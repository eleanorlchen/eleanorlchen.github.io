/**
 * This program creates a game class to play video poker game
 * This class is going to play the game according to the game rules,
 * distribute cards to player, print player's hand, 
 * calculate winnings, ask the player to continue playing
 * This class will be used in video poker game project.
 * 
 * @author: <Elle Chen>
 * @date: <11/4/17>
 * 
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class Game 
{
	
	private Player p;
	private Deck cards;
	// Add instance varaiables 
	private ArrayList<Card> hand; //Store player's hand
    //Store the count for the duplicate cards
    private int[] sameCardCount; 
    //Store the coutn for the duplicate card rank
    private int[] sameCardGroup; 
    // Constants for payOut amount
    private final int ROYALFLUSH_PAYOUT = 250;
    private final int STRAIGHTFLUSH_PAYOUT = 50;
    private final int FOURKIND_PAYOUT = 25;
    private final int FULLHOUSE_PAYOUT = 6;
    private final int FLUSH_PAYOUT = 5;
    private final int STRAIGHT_PAYOUT = 4;
    private final int THREEKIND_PAYOUT = 3;
    private final int TWOPAIR_PAYOUT = 2;
    private final int ONEPAIR_PAYOUT =1;    	
	
	public Game(String[] testHand)
    {
		// This constructor is to help test your code
		// use the contents of testHand to
		// make a hand for the player
		// use the following encoding for cards
		// c = clubs
		// d = diamonds
		// h = hearts
		// s = spades
		// 1-13 correspond to ace - king
		// example: s1 = ace of spades
		// example: testhand = {s1, s13, s12, s11, s10} = royal flush
		p = new Player();
        cards = new Deck();		
        sameCardCount = new int[2];
        sameCardGroup = new int[2];
        
        for(int i=0; i < testHand.length; i++)
        {
            int suit = suitCharactorToNumber(testHand[i].substring(0,1));           
            int rank = Integer.parseInt(testHand[i].substring(1));
            Card inputCard = new Card(suit, rank);
            p.addCard(inputCard);
            cards.deal();
        }        
	}    
    	
	public Game()
    {
		// This no-argument constructor is to play a normal game
	    p = new Player();
        cards = new Deck();
        
        cards.shuffle();	
        
        sameCardCount = new int[2];
        sameCardGroup = new int[2];
	}

    // this method play the game	
	public void play()
    {
		boolean continuePlay = true;
        Scanner input = new Scanner(System.in);
        
        // continue play until bank roll <= 0
        while(continuePlay ) 
        {
            System.out.println("Enter the amount you want to bet (1 to 5)");            
            double bet = input.nextDouble();
            if(bet > 5)
            {
                System.out.println("The bet amount cannot be over 5");
                continue;
            }
            if(p.getBankroll() - bet < 0 )
            {
                System.out.println("The bet amount cannot over bankroll");
                continue;
            }
            p.bets(bet);
            dealCard();            
            printHand();  // print out hand
            reDistributeCard(input);
            printHand(); // print out hand again
            System.out.println("Your hand is "+ checkHand(p.getHand()));
            double payOut = getPayout();
            p.winnings(bet * payOut);
            System.out.println("Congratulations! You win "+ bet * payOut);
            System.out.println("Your current bankroll is " + p.getBankroll());
            System.out.println("Do you want to continue play? Y/N");
            if(input.next().toUpperCase().compareTo("N") == 0)  
            {
                continuePlay = false;
                System.out.println("Thanks for playing.");
            }
            
            if(p.getBankroll() <= 0)
            {
                System.out.println("Oops, you are out of money");
                return;
            }
            resetGame(); // reset game for each new play
        }
	}
	
	public String checkHand(ArrayList<Card> hand)
    {
		// this method should take an ArrayList of cards
		// as input and then determine what evaluates to and
		// return that as a String
		if(hand.size() !=5)
        {
            return "Wrong number of cards";
        }
        this.hand = hand;
        // sort hand 
        Collections.sort(this.hand);
        checkSameCards();
        return getHandString();
	}	
    
    // initialize same card array
    private void initSameCardArray()
    {
        // same count start with 1
        sameCardCount[0] = 1;
        sameCardCount[1] = 1;
        sameCardGroup[0] = 0;
        sameCardGroup[1] = 0;
    }
	
    // check the group of duplicate cards in hand 
    private void checkSameCards()
    {
        initSameCardArray();
        int index = 0;
        // loop until find two group of duplicate cards
        for(int i=0; i < hand.size() && index < 2; i++) 
        {
            sameCardGroup[index] = hand.get(i).getRank();
            for(int j=i+1; j < hand.size(); j++)
            {
                if(hand.get(j).getRank() == sameCardGroup[index])
                {
                    sameCardCount[index]++;    
                }
            }
            
            if(sameCardCount[index] > 1)
            {
                // if the duplcate found, the outer loop start with new index
                i = i + sameCardCount[index]-1;
                // moving to next group
                index++;                
            }
        }
    }
    
    // check four of kind 
    private boolean isFourKind()
    {
        return sameCardCount[0] == 4 || sameCardCount[1] == 4;
    }
    
    // check three of kind 
    private boolean isThreeKind()
    {
        return sameCardCount[0] == 3 || sameCardCount[1] == 3;	
    }
    
    // check two pair
    private boolean isTwoPair()
    {
        return sameCardCount[0] == 2 && sameCardCount[1] == 2;
    }
    
    // check one pair
    private boolean isOnePair()
    {
        return sameCardCount[0] == 2;
    }
    
    // check full house
    private boolean isFullHouse()
    {
        return (sameCardCount[0] == 2 && sameCardCount[1] == 3
            || sameCardCount[0] == 3 && sameCardCount[1] == 2 );
    }
    
    // check flush
    private boolean isFlush()
    {
        for(int i=0; i < 4; i++)
        {
            if(hand.get(i).getSuit() != hand.get(i+1).getSuit())
            {
                return false;
            }
        }
        return true;
    }
    
    // check straight
    private boolean isStraight()
    {
        if(hand.get(0).getRank() != 10)
        {
            return (hand.get(0).getRank() == hand.get(1).getRank() -1
                   && hand.get(1).getRank() == hand.get(2).getRank() -1
                   && hand.get(2).getRank() == hand.get(3).getRank() -1
                   && hand.get(3).getRank() == hand.get(4).getRank() -1);
        }
        else 
        {
            return isHightStraight();
        }
    }
    
    // check high straight
    private boolean isHightStraight()
    {
        return (hand.get(0).getRank() == 1
               && hand.get(1).getRank() == 10
               && hand.get(2).getRank() == 11
               && hand.get(3).getRank() == 12
               && hand.get(4).getRank() == 13);
    }
    
    // check rolyFlush
    private boolean isRolyFlush()
    {
        if(isFlush())
        {
            return isHightStraight();
        }
        return false;
    }
    
    // assign suit letter to number
    private int suitCharactorToNumber(String strSuit)
    {
        if(strSuit.compareTo("d") == 0 )
        {
            return 2;
        }
        else if(strSuit.compareTo("h") == 0)
        {
            return 3;
        }
        else if(strSuit.compareTo("s") == 0)
        {
            return 4;
        }
        return 1;
    }
    
    // reset game
    private void resetGame()
    {
        cards.shuffle();
        p.resetHand();
    }
    
    // deal 5 cards
    private void dealCard()
    {
        if(p.getHand().size() == 0)
        {
            for (int i=0; i < 5; i++)
            {
                p.addCard(cards.deal());
            }
        }
    }
    
    // print Hand
    private void printHand()
    {
        // Copy p.Hands to instance varaiables for future use
        hand = new ArrayList<Card>(p.getHand());
        System.out.println("Your hand is ");
        for(int i = 0; i < hand.size(); i++)
        {
            System.out.printf("%18s", hand.get(i).toString());
        }
        System.out.println();
    }
    
    // User can reject 1,2,3,4,5 cards or reject none
    private void reDistributeCard(Scanner input)
    {
        System.out.println("Enter the number of cards you want to replace ");
        if(input.nextInt() > 0)
        {
            System.out.println("Please enter the position of the cards you" +
                     " want to replace separated by comma, i.e. 1,2,3");
            String[] replaceIndex = input.next().split(",");
            
            for(int i=0; i < replaceIndex.length; i++)
            {   // remove the replacing card
                p.removeCard(hand.get(Integer.parseInt(replaceIndex[i])-1));                
            }
            // deal the card again
            for(int i=0; i < replaceIndex.length; i++)
            {
                p.addCard(cards.deal());
            }
        }
    }
    
    // Calculate payout amount 
    private double getPayout()
    {
        if(this.isRolyFlush())
        {
            return ROYALFLUSH_PAYOUT;
        }
        else if(this.isFourKind())
        {
            return FOURKIND_PAYOUT;
        }
        else if(this.isStraight())
        {
            if(this.isFlush())
            {
                return STRAIGHTFLUSH_PAYOUT;
            }
            else 
            {
                return STRAIGHT_PAYOUT;    
            }            
        }
        else if(this.isFullHouse())
        {
            return FULLHOUSE_PAYOUT;
        }
        else if(this.isFlush())
        {
            return FLUSH_PAYOUT;
        }
        else if(this.isThreeKind())
        {
            return THREEKIND_PAYOUT;
        }
        else if(this.isTwoPair())
        {
            return TWOPAIR_PAYOUT;
        }
        else if(this.isOnePair())
        {
            return ONEPAIR_PAYOUT;
        }        
        return 0;                
    }
    
    // print the name of hand 
    private String getHandString()
    {
        if(this.isRolyFlush())
        {
            return "Royal Flush";
        }
        else if(this.isFourKind())
        {
            return "Four of a Kind " + this.sameCardGroup[0];
        }
        else if(this.isStraight())
        {
            if(this.isFlush())
            {
                return "Straight Flush";
            }
            else 
            {
                return "Straight";    
            }            
        }
        else if(this.isFullHouse())
        {
            return "Full House";
        }
        else if(this.isFlush())
        {
            return "Flush";
        }
        else if(this.isThreeKind())
        {
            return "Three of a Kind " + this.sameCardGroup[0];
        }
        else if(this.isTwoPair())
        {
            return "Two pair of "+sameCardGroup[0]+", "+ sameCardGroup[1];
        }
        else if(this.isOnePair())
        {
            return "one pair of "+ this.sameCardGroup[0];
        }        
        return "No pair"; 
    }
}
