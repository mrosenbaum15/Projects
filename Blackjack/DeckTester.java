/**
 * DeckTester.java 
 *
 * @author: Matt Rosenbaum
 * Assignment #: Blackjack Project
 * 
 * Brief Program Description: This program will
 * test the Deck class
 *
 */

public class DeckTester 
{
    public static void main(String[] args)
    {
        //Constructs a new 52 card deck
        Deck d = new Deck();
        Deck d2 = new Deck();
        Deck d3 = new Deck();
        Deck d4 = new Deck();

        System.out.print(d);

        //Draws the top card on the deck
        //and then that card will be
        //removed from Deck d
        System.out.println("\nTEST drawCard: \n\n" + d.drawCard());
        System.out.println("\nDeck after card is drawn: \n");
        System.out.println(d);

        //Shuffles the deck; puts the cards
        //in a random order
        //Shuffles the deck, prints the deck
        //and then shuffles it again
        System.out.println("\nTEST shuffle: \n");
        d2.shuffle();
        System.out.println(d2);
        d2.shuffle();
        System.out.println(d2);

        //Will reset the deck and shuffle the cards
        //if more than 32 cards are drawn. Won't reset
        //or shuffle the deck if 32 or less cards are drawn
        System.out.println("\nTEST resetShuffle: ");
        System.out.println("\nDraw of top 35 cards in default deck: ");
        for(int i = 0; i < 35; i++)
        {
            System.out.println(d3.drawCard());
        }
        d3.resetShuffle();
        System.out.println("\nNew, reshuffled deck: \n\n" + d3);

        System.out.println("\nTEST resetShuffle: ");
        System.out.println("\nDraw of top 30 cards in default deck. The deck should not reset\n and shuffle because there are more than 20 cards left in the deck.");
        for(int i = 0; i < 30; i++)
        {
            System.out.println(d4.drawCard());
        }
        d4.resetShuffle();
        System.out.println("\nRemaining cards in deck: \n\n" + d4);
    }
}
/*

Deck (Bottom card is last on list, top card is first on list): 

Rank: Ace	Suit: ♥		Value: 11
Rank: Two	Suit: ♥		Value: 2
Rank: Three	Suit: ♥		Value: 3
Rank: Four	Suit: ♥		Value: 4
Rank: Five	Suit: ♥		Value: 5
Rank: Six	Suit: ♥		Value: 6
Rank: Seven	Suit: ♥		Value: 7
Rank: Eight	Suit: ♥		Value: 8
Rank: Nine	Suit: ♥		Value: 9
Rank: Ten	Suit: ♥		Value: 10
Rank: Jack	Suit: ♥		Value: 10
Rank: Queen	Suit: ♥		Value: 10
Rank: King	Suit: ♥		Value: 10
Rank: Ace	Suit: ♣		Value: 11
Rank: Two	Suit: ♣		Value: 2
Rank: Three	Suit: ♣		Value: 3
Rank: Four	Suit: ♣		Value: 4
Rank: Five	Suit: ♣		Value: 5
Rank: Six	Suit: ♣		Value: 6
Rank: Seven	Suit: ♣		Value: 7
Rank: Eight	Suit: ♣		Value: 8
Rank: Nine	Suit: ♣		Value: 9
Rank: Ten	Suit: ♣		Value: 10
Rank: Jack	Suit: ♣		Value: 10
Rank: Queen	Suit: ♣		Value: 10
Rank: King	Suit: ♣		Value: 10
Rank: King	Suit: ♦		Value: 10
Rank: Queen	Suit: ♦		Value: 10
Rank: Jack	Suit: ♦		Value: 10
Rank: Ten	Suit: ♦		Value: 10
Rank: Nine	Suit: ♦		Value: 9
Rank: Eight	Suit: ♦		Value: 8
Rank: Seven	Suit: ♦		Value: 7
Rank: Six	Suit: ♦		Value: 6
Rank: Five	Suit: ♦		Value: 5
Rank: Four	Suit: ♦		Value: 4
Rank: Three	Suit: ♦		Value: 3
Rank: Two	Suit: ♦		Value: 2
Rank: Ace	Suit: ♦		Value: 11
Rank: King	Suit: ♠		Value: 10
Rank: Queen	Suit: ♠		Value: 10
Rank: Jack	Suit: ♠		Value: 10
Rank: Ten	Suit: ♠		Value: 10
Rank: Nine	Suit: ♠		Value: 9
Rank: Eight	Suit: ♠		Value: 8
Rank: Seven	Suit: ♠		Value: 7
Rank: Six	Suit: ♠		Value: 6
Rank: Five	Suit: ♠		Value: 5
Rank: Four	Suit: ♠		Value: 4
Rank: Three	Suit: ♠		Value: 3
Rank: Two	Suit: ♠		Value: 2
Rank: Ace	Suit: ♠		Value: 11

TEST drawCard: 

Rank: Ace	Suit: ♥		Value: 11

Deck after card is drawn: 

Deck (Bottom card is last on list, top card is first on list): 

Rank: Two	Suit: ♥		Value: 2
Rank: Three	Suit: ♥		Value: 3
Rank: Four	Suit: ♥		Value: 4
Rank: Five	Suit: ♥		Value: 5
Rank: Six	Suit: ♥		Value: 6
Rank: Seven	Suit: ♥		Value: 7
Rank: Eight	Suit: ♥		Value: 8
Rank: Nine	Suit: ♥		Value: 9
Rank: Ten	Suit: ♥		Value: 10
Rank: Jack	Suit: ♥		Value: 10
Rank: Queen	Suit: ♥		Value: 10
Rank: King	Suit: ♥		Value: 10
Rank: Ace	Suit: ♣		Value: 11
Rank: Two	Suit: ♣		Value: 2
Rank: Three	Suit: ♣		Value: 3
Rank: Four	Suit: ♣		Value: 4
Rank: Five	Suit: ♣		Value: 5
Rank: Six	Suit: ♣		Value: 6
Rank: Seven	Suit: ♣		Value: 7
Rank: Eight	Suit: ♣		Value: 8
Rank: Nine	Suit: ♣		Value: 9
Rank: Ten	Suit: ♣		Value: 10
Rank: Jack	Suit: ♣		Value: 10
Rank: Queen	Suit: ♣		Value: 10
Rank: King	Suit: ♣		Value: 10
Rank: King	Suit: ♦		Value: 10
Rank: Queen	Suit: ♦		Value: 10
Rank: Jack	Suit: ♦		Value: 10
Rank: Ten	Suit: ♦		Value: 10
Rank: Nine	Suit: ♦		Value: 9
Rank: Eight	Suit: ♦		Value: 8
Rank: Seven	Suit: ♦		Value: 7
Rank: Six	Suit: ♦		Value: 6
Rank: Five	Suit: ♦		Value: 5
Rank: Four	Suit: ♦		Value: 4
Rank: Three	Suit: ♦		Value: 3
Rank: Two	Suit: ♦		Value: 2
Rank: Ace	Suit: ♦		Value: 11
Rank: King	Suit: ♠		Value: 10
Rank: Queen	Suit: ♠		Value: 10
Rank: Jack	Suit: ♠		Value: 10
Rank: Ten	Suit: ♠		Value: 10
Rank: Nine	Suit: ♠		Value: 9
Rank: Eight	Suit: ♠		Value: 8
Rank: Seven	Suit: ♠		Value: 7
Rank: Six	Suit: ♠		Value: 6
Rank: Five	Suit: ♠		Value: 5
Rank: Four	Suit: ♠		Value: 4
Rank: Three	Suit: ♠		Value: 3
Rank: Two	Suit: ♠		Value: 2
Rank: Ace	Suit: ♠		Value: 11

TEST shuffle: 
Deck (Bottom card is last on list, top card is first on list): 
Rank: King	Suit: ♣		Value: 10
Rank: Queen	Suit: ♦		Value: 10
Rank: Five	Suit: ♦		Value: 5
Rank: Jack	Suit: ♣		Value: 10
Rank: Three	Suit: ♦		Value: 3
Rank: Queen	Suit: ♥		Value: 10
Rank: Six	Suit: ♠		Value: 6
Rank: Seven	Suit: ♦		Value: 7
Rank: Eight	Suit: ♦		Value: 8
Rank: Eight	Suit: ♥		Value: 8
Rank: King	Suit: ♥		Value: 10
Rank: Two	Suit: ♣		Value: 2
Rank: Queen	Suit: ♣		Value: 10
Rank: Eight	Suit: ♣		Value: 8
Rank: Ace	Suit: ♣		Value: 11
Rank: Nine	Suit: ♠		Value: 9
Rank: Seven	Suit: ♣		Value: 7
Rank: Three	Suit: ♠		Value: 3
Rank: King	Suit: ♠		Value: 10
Rank: Four	Suit: ♣		Value: 4
Rank: Two	Suit: ♠		Value: 2
Rank: Five	Suit: ♥		Value: 5
Rank: Ace	Suit: ♠		Value: 11
Rank: Ten	Suit: ♠		Value: 10
Rank: King	Suit: ♦		Value: 10
Rank: Ten	Suit: ♣		Value: 10
Rank: Nine	Suit: ♥		Value: 9
Rank: Six	Suit: ♣		Value: 6
Rank: Seven	Suit: ♠		Value: 7
Rank: Five	Suit: ♠		Value: 5
Rank: Five	Suit: ♣		Value: 5
Rank: Ace	Suit: ♦		Value: 11
Rank: Nine	Suit: ♣		Value: 9
Rank: Four	Suit: ♥		Value: 4
Rank: Four	Suit: ♦		Value: 4
Rank: Jack	Suit: ♥		Value: 10
Rank: Two	Suit: ♦		Value: 2
Rank: Three	Suit: ♣		Value: 3
Rank: Three	Suit: ♥		Value: 3
Rank: Nine	Suit: ♦		Value: 9
Rank: Jack	Suit: ♠		Value: 10
Rank: Six	Suit: ♥		Value: 6
Rank: Queen	Suit: ♠		Value: 10
Rank: Six	Suit: ♦		Value: 6
Rank: Ace	Suit: ♥		Value: 11
Rank: Seven	Suit: ♥		Value: 7
Rank: Ten	Suit: ♦		Value: 10
Rank: Eight	Suit: ♠		Value: 8
Rank: Jack	Suit: ♦		Value: 10
Rank: Ten	Suit: ♥		Value: 10
Rank: Four	Suit: ♠		Value: 4
Rank: Two	Suit: ♥		Value: 2

Deck (Bottom card is last on list, top card is first on list): 

Rank: Two	Suit: ♥		Value: 2
Rank: Three	Suit: ♠		Value: 3
Rank: Eight	Suit: ♠		Value: 8
Rank: Three	Suit: ♣		Value: 3
Rank: Four	Suit: ♠		Value: 4
Rank: Four	Suit: ♥		Value: 4
Rank: Five	Suit: ♥		Value: 5
Rank: Six	Suit: ♥		Value: 6
Rank: Jack	Suit: ♥		Value: 10
Rank: Ace	Suit: ♥		Value: 11
Rank: Two	Suit: ♠		Value: 2
Rank: Seven	Suit: ♣		Value: 7
Rank: Five	Suit: ♣		Value: 5
Rank: Six	Suit: ♦		Value: 6
Rank: Seven	Suit: ♦		Value: 7
Rank: Four	Suit: ♣		Value: 4
Rank: Ten	Suit: ♣		Value: 10
Rank: Nine	Suit: ♠		Value: 9
Rank: Five	Suit: ♦		Value: 5
Rank: Jack	Suit: ♣		Value: 10
Rank: King	Suit: ♥		Value: 10
Rank: Ten	Suit: ♠		Value: 10
Rank: Nine	Suit: ♥		Value: 9
Rank: Queen	Suit: ♥		Value: 10
Rank: Ten	Suit: ♦		Value: 10
Rank: Ten	Suit: ♥		Value: 10
Rank: Eight	Suit: ♣		Value: 8
Rank: Ace	Suit: ♣		Value: 11
Rank: Ace	Suit: ♦		Value: 11
Rank: Ace	Suit: ♠		Value: 11
Rank: Five	Suit: ♠		Value: 5
Rank: King	Suit: ♠		Value: 10
Rank: Two	Suit: ♦		Value: 2
Rank: Jack	Suit: ♦		Value: 10
Rank: Queen	Suit: ♦		Value: 10
Rank: Three	Suit: ♦		Value: 3
Rank: Seven	Suit: ♠		Value: 7
Rank: Three	Suit: ♥		Value: 3
Rank: Four	Suit: ♦		Value: 4
Rank: Nine	Suit: ♣		Value: 9
Rank: Seven	Suit: ♥		Value: 7
Rank: King	Suit: ♣		Value: 10
Rank: Eight	Suit: ♦		Value: 8
Rank: Jack	Suit: ♠		Value: 10
Rank: Two	Suit: ♣		Value: 2
Rank: Nine	Suit: ♦		Value: 9
Rank: Six	Suit: ♣		Value: 6
Rank: Six	Suit: ♠		Value: 6
Rank: Queen	Suit: ♠		Value: 10
Rank: Queen	Suit: ♣		Value: 10
Rank: Eight	Suit: ♥		Value: 8
Rank: King	Suit: ♦		Value: 10

TEST resetShuffle: 
Draw of top 35 cards in default deck: 
Rank: Ace	Suit: ♥		Value: 11
Rank: Two	Suit: ♥		Value: 2
Rank: Three	Suit: ♥		Value: 3
Rank: Four	Suit: ♥		Value: 4
Rank: Five	Suit: ♥		Value: 5
Rank: Six	Suit: ♥		Value: 6
Rank: Seven	Suit: ♥		Value: 7
Rank: Eight	Suit: ♥		Value: 8
Rank: Nine	Suit: ♥		Value: 9
Rank: Ten	Suit: ♥		Value: 10
Rank: Jack	Suit: ♥		Value: 10
Rank: Queen	Suit: ♥		Value: 10
Rank: King	Suit: ♥		Value: 10
Rank: Ace	Suit: ♣		Value: 11
Rank: Two	Suit: ♣		Value: 2
Rank: Three	Suit: ♣		Value: 3
Rank: Four	Suit: ♣		Value: 4
Rank: Five	Suit: ♣		Value: 5
Rank: Six	Suit: ♣		Value: 6
Rank: Seven	Suit: ♣		Value: 7
Rank: Eight	Suit: ♣		Value: 8
Rank: Nine	Suit: ♣		Value: 9
Rank: Ten	Suit: ♣		Value: 10
Rank: Jack	Suit: ♣		Value: 10
Rank: Queen	Suit: ♣		Value: 10
Rank: King	Suit: ♣		Value: 10
Rank: King	Suit: ♦		Value: 10
Rank: Queen	Suit: ♦		Value: 10
Rank: Jack	Suit: ♦		Value: 10
Rank: Ten	Suit: ♦		Value: 10
Rank: Nine	Suit: ♦		Value: 9
Rank: Eight	Suit: ♦		Value: 8
Rank: Seven	Suit: ♦		Value: 7
Rank: Six	Suit: ♦		Value: 6
Rank: Five	Suit: ♦		Value: 5

New, reshuffled deck: 

Deck (Bottom card is last on list, top card is first on list): 

Rank: Four	Suit: ♦		Value: 4
Rank: Three	Suit: ♦		Value: 3
Rank: Two	Suit: ♦		Value: 2
Rank: Ace	Suit: ♦		Value: 11
Rank: King	Suit: ♠		Value: 10
Rank: Queen	Suit: ♠		Value: 10
Rank: Jack	Suit: ♠		Value: 10
Rank: Ten	Suit: ♠		Value: 10
Rank: Nine	Suit: ♠		Value: 9
Rank: Eight	Suit: ♠		Value: 8
Rank: Seven	Suit: ♠		Value: 7
Rank: Six	Suit: ♠		Value: 6
Rank: Five	Suit: ♠		Value: 5
Rank: Four	Suit: ♠		Value: 4
Rank: Three	Suit: ♠		Value: 3
Rank: Two	Suit: ♠		Value: 2
Rank: Ace	Suit: ♠		Value: 11

TEST resetShuffle: 
Draw of top 30 cards in default deck. The deck should not reset
and shuffle because there are more than 20 cards left in the deck.
Rank: Ace	Suit: ♥		Value: 11
Rank: Two	Suit: ♥		Value: 2
Rank: Three	Suit: ♥		Value: 3
Rank: Four	Suit: ♥		Value: 4
Rank: Five	Suit: ♥		Value: 5
Rank: Six	Suit: ♥		Value: 6
Rank: Seven	Suit: ♥		Value: 7
Rank: Eight	Suit: ♥		Value: 8
Rank: Nine	Suit: ♥		Value: 9
Rank: Ten	Suit: ♥		Value: 10
Rank: Jack	Suit: ♥		Value: 10
Rank: Queen	Suit: ♥		Value: 10
Rank: King	Suit: ♥		Value: 10
Rank: Ace	Suit: ♣		Value: 11
Rank: Two	Suit: ♣		Value: 2
Rank: Three	Suit: ♣		Value: 3
Rank: Four	Suit: ♣		Value: 4
Rank: Five	Suit: ♣		Value: 5
Rank: Six	Suit: ♣		Value: 6
Rank: Seven	Suit: ♣		Value: 7
Rank: Eight	Suit: ♣		Value: 8
Rank: Nine	Suit: ♣		Value: 9
Rank: Ten	Suit: ♣		Value: 10
Rank: Jack	Suit: ♣		Value: 10
Rank: Queen	Suit: ♣		Value: 10
Rank: King	Suit: ♣		Value: 10
Rank: King	Suit: ♦		Value: 10
Rank: Queen	Suit: ♦		Value: 10
Rank: Jack	Suit: ♦		Value: 10
Rank: Ten	Suit: ♦		Value: 10

Remaining cards in deck: 

Deck (Bottom card is last on list, top card is first on list): 

Rank: Nine	Suit: ♦		Value: 9
Rank: Eight	Suit: ♦		Value: 8
Rank: Seven	Suit: ♦		Value: 7
Rank: Six	Suit: ♦		Value: 6
Rank: Five	Suit: ♦		Value: 5
Rank: Four	Suit: ♦		Value: 4
Rank: Three	Suit: ♦		Value: 3
Rank: Two	Suit: ♦		Value: 2
Rank: Ace	Suit: ♦		Value: 11
Rank: King	Suit: ♠		Value: 10
Rank: Queen	Suit: ♠		Value: 10
Rank: Jack	Suit: ♠		Value: 10
Rank: Ten	Suit: ♠		Value: 10
Rank: Nine	Suit: ♠		Value: 9
Rank: Eight	Suit: ♠		Value: 8
Rank: Seven	Suit: ♠		Value: 7
Rank: Six	Suit: ♠		Value: 6
Rank: Five	Suit: ♠		Value: 5
Rank: Four	Suit: ♠		Value: 4
Rank: Three	Suit: ♠		Value: 3
Rank: Two	Suit: ♠		Value: 2
Rank: Ace	Suit: ♠		Value: 11

 */