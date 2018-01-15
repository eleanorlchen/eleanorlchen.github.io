This is a video poker game. The player will play 1 deck out of 52 cards and cards will be initally shuffled 3 times. 
The player will initally be given 100 tokens to play. Each time, the player can bet between 1-5 tokens. The top 5 cards will be disributed to 
the player. The player have one chance to change his/her hand: the player can reject no cards or reject 1-5 cards, then the rejected cards will
be replaced by the top of the cards from the deck. To do that, first, the player will be asked how many cards he wants to replace. If he/she enters 0, 
then no cards will be replaced. If he/she chooses anything other than 0, he/she needs enter the position of the card he/she wants to replace by 
entering the position of each card separated by comma (the position starts with 1), for example,1,2,3. After cacluated payout amount the player
will be asked to choose to play again or to not by enterring "Y/N". If the player enteres N, the game will be stopped. If the player 
enters  Y, the game will continue until the bankroll reaches zero. 
Here are the pay out rules:
1) No pair—The lowest hand, containing five separate cards that do not match up
to create any of the hands below.
2) One pair—Two cards of the same value, for example two queens. Payout: 1
3) Two pairs—Two pairs, for example two queens and two 5’s. Payout: 2
4) Three of a kind—Three cards of the same value, for example three queens. Payout: 3
5) Straight—Five cards with consecutive values, not necessarily of the same suit,
   such as 4, 5, 6, 7, and 8. The ace can either precede a 2 or follow a king. Payout: 4
6) Flush—Five cards, not necessarily in order, of the same suit. Payout: 5
7) Full House—Three of a kind and a pair, for example three queens and two 5’s. Payout: 6
8) Four of a Kind—Four cards of the same value, such as four queens. Payout: 25
9) Straight Flush—A straight and a flush: Five cards with consecutive values of the same suit. Payout: 50
10) Royal Flush—The best possible hand in poker. A 10, jack, queen, king, and ace,
   all of the same suit. Payout: 250
The displayed winning amount equals the number of tokens the player bet times the payout amount, the actual winning amount is the displayed amount
minus the bet amount. For example, if you bet 5 token, you get three of a kind, the payout of 3 of a kind is 3, your displayed winning amount will 
be 3*5 = 15, your actual net winning is 15-3 = 12 tokens. 

To write this project, I used 4 classes: Card, Player, Deck, Game. 
1) The Card class implements Comparable interface. I used the compareTo method based on the comparison of
the card rank so that I can use Collections to sort the ArrayList of Cards later. By sorting the cards, I can easily find the straight, pairs, 
3 of kind , 4 of kind or full house.
2) Deck class holds 52 cards in a deck and an instance variable top as a pointer to point the index of the top card in the card array. Everytime 
when dealing the card, the top will increase 1. To shuffle the card, I copied the cards array to an arrayList, then used Collections.shuffle 
method to shuffle the arrayList, then copied the shuffled arrayList back to the card array. I created a constant instance varaible 
SHUFFLE_TIMES = 3, so that the deck will be shuffled 3 times. The reason I used a constant is because it is easy to change to different number 
in future.
3) Player class will hold the hand of the player, the bankroll, and the bet amount of the player. To allow the player to easily evaludate 
his/her card, the card will be sorted everytime when a new card insert into the player's hand. 
4) Game class is the most complicated class in this project. Game class will distribute cards, calculate payout, calculate winning amount. 
I created additional constant instance varaibles for different payout.  I created two instance varaibles: sameCardCount, sameCardGroup to help
calculating the number of cards with the same rank in hand. Because at most there could be 2 groups of cards that have the same rank, I made 
both sameCardCount and sameCardGroup as int[2] array. sameCardCount will count how many duplicates in each group. sameCardGroup will hold the
rank for each group. I created a boolean type method for each payout type to check different payouts. To allow the player to easily to 
judge the situation, I print out the hand at the start of the game and after the player choose replace the card. 
