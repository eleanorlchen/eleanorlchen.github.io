/**
 * This program creates a deck class to shuffle and deal cards in the deck.
 * This class will be used in video poker game project.
 * 
 * @author: <Elle Chen>
 * @date: <11/4/17>
 * 
 */

import java.util.Collections;
import java.util.ArrayList;

public class Deck
{
	
	private Card[] cards;
	private int top; // the index of the top of the deck
    // Shuffle 3 times per play
	private final int SHUFFLE_TIMES = 3;
	
	public Deck()
    {
		// make a 52 card deck here
		cards = new Card[52];
        int index = 0; // index of cards
        for(int i=1; i < 5; i++) // 4 suits
        {
            for(int j=1; j < 14; j++) // 13 ranks
            {
                cards[index] = new Card(i,j);
                index++;
            }
        }
        top = 0;
	}
	
	public void shuffle()
    {
		// shuffle the deck here
		// Save the cards into cardlist so that we can 
		// use Collections.shuffle() method to shuffle
        ArrayList<Card> cardList = new ArrayList<Card>();
        for(int i=0; i < cards.length; i++)
        {
            cardList.add(cards[i]);
        }
        for(int i=0; i < SHUFFLE_TIMES; i++)
        {
            Collections.shuffle(cardList);
        }
        // copy the shuffled card pack to cards
        for(int i=0; i < cards.length; i++)
        {
            cards[i] = cardList.get(i);
        }
	}
	
	public Card deal()
    {
		// deal the top card in the deck
		top++;
        return cards[top-1];
	}	
}
