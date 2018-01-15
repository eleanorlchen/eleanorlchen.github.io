/**
 * This program creates a card class that contains the suit and rank of cards
 * This will be used in video poker game project
 * 
 * @author: <Elle Chen>
 * @date: <11/4/17>
 * 
 */

public class Card implements Comparable<Card>
{
	
	private int suit; // use integers 1-4 to encode the suit
	private int rank; // use integers 1-13 to encode the rank
	
	public Card(int s, int r)
    {
       // initalize suit and rank
        suit = s;
        rank = r;
	}
	
	public int compareTo(Card c)
    {
		// use this method to compare cards so they 
		// may be easily sorted  (sort by rank)
        if(c.rank == this.rank )
        {
            if(this.suit > c.suit)
            {
                return 1;
            }
            else if (this.suit == c.suit)
            {
                return 0;
            }
            else 
            {
                return -1;
            }
        }
        else if (this.rank > c.rank)
        {
           return 1;
        }
        else 
        {
           return -1;
        }

	}
	
	public String toString()
    {
		// use this method to easily print a Card object
		// c for clubs, d for diamonds, h for hearts, s for spades
		String suitString = "clubs";
        String rankString = Integer.toString(rank);
        if(suit == 2)
        {
            suitString = "diamonds";
        }
        else if(suit == 3)
        {
            suitString = "hearts";
        }
        else if(suit ==4)
        {
            suitString = "spades";
        }       
        if(rank == 11)
        {
            rankString = "Jack";
        }
        else if (rank == 12)
        {
            rankString = "Queen";
        }
        else if (rank == 13)
        {
            rankString = "King";
        }
        else if (rank == 1)
        {
            rankString = "Ace";
        }
        return suitString+rankString;
	}
	
    // Accessor method returns card's rank
    public int getRank()
    {
        return rank;
    }
    
    // Accessor method returns card's suit
    public int getSuit()
    {
        return suit;
    }
}
