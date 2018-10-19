/**
 * ShoeTester.java 
 *
 * @author: Matt Rosenbaum
 * Assignment #: Blackjack Project
 * 
 * Brief Program Description: This program tests
 * the shoe class
 *
 */

public class ShoeTester 
{
    public static void main(String[] args)
    {
        //Constructs a new shoe with 2 decks in it
        Shoe s = new Shoe(2);

        //Constructs a new shoe with 1 deck in it
        Shoe s2 = new Shoe(1);

        Shoe s3 = new Shoe(2);
        Shoe s4 = new Shoe(2);

        //Prints out the first two shoes
        System.out.println(s);
        System.out.println(s2);

        //Shuffles Shoe s twice
        System.out.println("\nTEST shuffle: \n");
        s.shuffle();
        System.out.println("\nNew, shuffled deck: \n" + s);
        s.shuffle();
        System.out.println("\nNew, shuffled deck: \n" + s);

        //Will reset the shoe and shuffle its cards
        //if more there are less than 20 cards left. 
        //Won't reset or shuffle the shoe has 20 or more cards left.
        System.out.println("\nTEST resetShuffle: ");
        System.out.println("\nThe shoe contains the first 90 cards of two unshuffled decks.\nThe shoe will reshuffle because there is less than twenty cards left.\n");
        for(int i = 0; i < 90; i++)
        {
            System.out.println(s3.drawCard());
        }
        s3.resetShuffle();
        System.out.println("\nNew, reshuffled shoe: \n\n" + s3);

        System.out.println("\nTEST resetShuffle: ");
        System.out.println("\nThe first 80 cards in a default two-deck shoe. The shoe should not reset\nand shuffle because there are more than 20 cards left in it.\n");
        for(int i = 0; i < 80; i++)
        {
            System.out.println(s4.drawCard());
        }
        s4.resetShuffle();
        System.out.println("\nRemaining cards in shoe: \n\n" + s4);
    }
}
/*

Output:

Number of Decks: 2

Shoe (Bottom card is last on list, top card is first on list): 

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

Number of Decks: 1

Shoe (Bottom card is last on list, top card is first on list): 

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


TEST shuffle: 


New, shuffled deck: 
Number of Decks: 2

Shoe (Bottom card is last on list, top card is first on list): 

Rank: Two	Suit: ♥		Value: 2
Rank: Nine	Suit: ♥		Value: 9
Rank: Two	Suit: ♣		Value: 2
Rank: Four	Suit: ♦		Value: 4
Rank: Queen	Suit: ♣		Value: 10
Rank: King	Suit: ♦		Value: 10
Rank: King	Suit: ♣		Value: 10
Rank: Seven	Suit: ♥		Value: 7
Rank: Four	Suit: ♦		Value: 4
Rank: Ace	Suit: ♠		Value: 11
Rank: Four	Suit: ♠		Value: 4
Rank: Three	Suit: ♠		Value: 3
Rank: Jack	Suit: ♦		Value: 10
Rank: Eight	Suit: ♥		Value: 8
Rank: Three	Suit: ♣		Value: 3
Rank: Ten	Suit: ♠		Value: 10
Rank: Queen	Suit: ♦		Value: 10
Rank: Two	Suit: ♥		Value: 2
Rank: Seven	Suit: ♦		Value: 7
Rank: Five	Suit: ♣		Value: 5
Rank: Queen	Suit: ♠		Value: 10
Rank: Ace	Suit: ♦		Value: 11
Rank: Five	Suit: ♥		Value: 5
Rank: Four	Suit: ♥		Value: 4
Rank: Ten	Suit: ♣		Value: 10
Rank: Nine	Suit: ♣		Value: 9
Rank: Three	Suit: ♣		Value: 3
Rank: Three	Suit: ♠		Value: 3
Rank: Ace	Suit: ♠		Value: 11
Rank: King	Suit: ♥		Value: 10
Rank: Jack	Suit: ♦		Value: 10
Rank: Five	Suit: ♦		Value: 5
Rank: King	Suit: ♠		Value: 10
Rank: Nine	Suit: ♥		Value: 9
Rank: Queen	Suit: ♣		Value: 10
Rank: Ten	Suit: ♦		Value: 10
Rank: Nine	Suit: ♠		Value: 9
Rank: Eight	Suit: ♠		Value: 8
Rank: Four	Suit: ♥		Value: 4
Rank: Queen	Suit: ♦		Value: 10
Rank: Five	Suit: ♥		Value: 5
Rank: Six	Suit: ♠		Value: 6
Rank: Seven	Suit: ♦		Value: 7
Rank: Ace	Suit: ♣		Value: 11
Rank: Ten	Suit: ♥		Value: 10
Rank: Six	Suit: ♣		Value: 6
Rank: Five	Suit: ♠		Value: 5
Rank: Two	Suit: ♣		Value: 2
Rank: King	Suit: ♥		Value: 10
Rank: Ace	Suit: ♣		Value: 11
Rank: Three	Suit: ♦		Value: 3
Rank: Ten	Suit: ♥		Value: 10
Rank: King	Suit: ♦		Value: 10
Rank: Eight	Suit: ♥		Value: 8
Rank: Two	Suit: ♠		Value: 2
Rank: King	Suit: ♣		Value: 10
Rank: Three	Suit: ♥		Value: 3
Rank: Three	Suit: ♥		Value: 3
Rank: Jack	Suit: ♣		Value: 10
Rank: Two	Suit: ♠		Value: 2
Rank: Five	Suit: ♠		Value: 5
Rank: Seven	Suit: ♣		Value: 7
Rank: Queen	Suit: ♥		Value: 10
Rank: Ten	Suit: ♦		Value: 10
Rank: Seven	Suit: ♠		Value: 7
Rank: Ace	Suit: ♥		Value: 11
Rank: Jack	Suit: ♥		Value: 10
Rank: Nine	Suit: ♦		Value: 9
Rank: Two	Suit: ♦		Value: 2
Rank: Five	Suit: ♣		Value: 5
Rank: Four	Suit: ♣		Value: 4
Rank: Three	Suit: ♦		Value: 3
Rank: Ace	Suit: ♦		Value: 11
Rank: Eight	Suit: ♦		Value: 8
Rank: Eight	Suit: ♣		Value: 8
Rank: Four	Suit: ♣		Value: 4
Rank: Ace	Suit: ♥		Value: 11
Rank: Six	Suit: ♥		Value: 6
Rank: Seven	Suit: ♣		Value: 7
Rank: Eight	Suit: ♣		Value: 8
Rank: Jack	Suit: ♠		Value: 10
Rank: Seven	Suit: ♥		Value: 7
Rank: Six	Suit: ♥		Value: 6
Rank: Nine	Suit: ♠		Value: 9
Rank: King	Suit: ♠		Value: 10
Rank: Queen	Suit: ♠		Value: 10
Rank: Jack	Suit: ♥		Value: 10
Rank: Eight	Suit: ♦		Value: 8
Rank: Six	Suit: ♦		Value: 6
Rank: Six	Suit: ♠		Value: 6
Rank: Five	Suit: ♦		Value: 5
Rank: Six	Suit: ♣		Value: 6
Rank: Jack	Suit: ♣		Value: 10
Rank: Two	Suit: ♦		Value: 2
Rank: Four	Suit: ♠		Value: 4
Rank: Nine	Suit: ♦		Value: 9
Rank: Six	Suit: ♦		Value: 6
Rank: Queen	Suit: ♥		Value: 10
Rank: Seven	Suit: ♠		Value: 7
Rank: Nine	Suit: ♣		Value: 9
Rank: Eight	Suit: ♠		Value: 8
Rank: Jack	Suit: ♠		Value: 10
Rank: Ten	Suit: ♠		Value: 10
Rank: Ten	Suit: ♣		Value: 10


New, shuffled deck: 
Number of Decks: 2

Shoe (Bottom card is last on list, top card is first on list): 

Rank: Nine	Suit: ♦		Value: 9
Rank: Three	Suit: ♠		Value: 3
Rank: Two	Suit: ♣		Value: 2
Rank: Ace	Suit: ♣		Value: 11
Rank: Seven	Suit: ♦		Value: 7
Rank: Five	Suit: ♦		Value: 5
Rank: King	Suit: ♠		Value: 10
Rank: Five	Suit: ♥		Value: 5
Rank: Queen	Suit: ♦		Value: 10
Rank: Eight	Suit: ♣		Value: 8
Rank: Ace	Suit: ♠		Value: 11
Rank: Ten	Suit: ♠		Value: 10
Rank: Seven	Suit: ♠		Value: 7
Rank: Seven	Suit: ♣		Value: 7
Rank: Nine	Suit: ♥		Value: 9
Rank: Ten	Suit: ♦		Value: 10
Rank: Eight	Suit: ♣		Value: 8
Rank: Nine	Suit: ♦		Value: 9
Rank: Seven	Suit: ♦		Value: 7
Rank: Nine	Suit: ♠		Value: 9
Rank: King	Suit: ♣		Value: 10
Rank: Ace	Suit: ♥		Value: 11
Rank: Five	Suit: ♣		Value: 5
Rank: Six	Suit: ♦		Value: 6
Rank: Ten	Suit: ♦		Value: 10
Rank: Seven	Suit: ♥		Value: 7
Rank: Four	Suit: ♦		Value: 4
Rank: Queen	Suit: ♥		Value: 10
Rank: Five	Suit: ♠		Value: 5
Rank: Two	Suit: ♣		Value: 2
Rank: Jack	Suit: ♠		Value: 10
Rank: Eight	Suit: ♦		Value: 8
Rank: Eight	Suit: ♥		Value: 8
Rank: Five	Suit: ♦		Value: 5
Rank: Five	Suit: ♣		Value: 5
Rank: Three	Suit: ♣		Value: 3
Rank: Jack	Suit: ♦		Value: 10
Rank: Ace	Suit: ♣		Value: 11
Rank: Queen	Suit: ♣		Value: 10
Rank: Six	Suit: ♥		Value: 6
Rank: Two	Suit: ♥		Value: 2
Rank: Two	Suit: ♦		Value: 2
Rank: Four	Suit: ♦		Value: 4
Rank: Ace	Suit: ♦		Value: 11
Rank: Three	Suit: ♠		Value: 3
Rank: King	Suit: ♥		Value: 10
Rank: Seven	Suit: ♠		Value: 7
Rank: Jack	Suit: ♠		Value: 10
Rank: Seven	Suit: ♥		Value: 7
Rank: Three	Suit: ♥		Value: 3
Rank: Ten	Suit: ♣		Value: 10
Rank: King	Suit: ♦		Value: 10
Rank: Queen	Suit: ♣		Value: 10
Rank: Queen	Suit: ♥		Value: 10
Rank: Jack	Suit: ♦		Value: 10
Rank: Four	Suit: ♣		Value: 4
Rank: Queen	Suit: ♠		Value: 10
Rank: Four	Suit: ♣		Value: 4
Rank: Jack	Suit: ♣		Value: 10
Rank: Four	Suit: ♠		Value: 4
Rank: Queen	Suit: ♦		Value: 10
Rank: Ace	Suit: ♥		Value: 11
Rank: Eight	Suit: ♥		Value: 8
Rank: Four	Suit: ♥		Value: 4
Rank: Eight	Suit: ♠		Value: 8
Rank: Jack	Suit: ♥		Value: 10
Rank: Two	Suit: ♥		Value: 2
Rank: Ace	Suit: ♦		Value: 11
Rank: Jack	Suit: ♣		Value: 10
Rank: Five	Suit: ♠		Value: 5
Rank: Ten	Suit: ♥		Value: 10
Rank: Nine	Suit: ♣		Value: 9
Rank: Seven	Suit: ♣		Value: 7
Rank: Three	Suit: ♦		Value: 3
Rank: Eight	Suit: ♦		Value: 8
Rank: Six	Suit: ♠		Value: 6
Rank: Jack	Suit: ♥		Value: 10
Rank: Three	Suit: ♥		Value: 3
Rank: Nine	Suit: ♠		Value: 9
Rank: King	Suit: ♣		Value: 10
Rank: Ten	Suit: ♥		Value: 10
Rank: King	Suit: ♦		Value: 10
Rank: Four	Suit: ♥		Value: 4
Rank: Six	Suit: ♣		Value: 6
Rank: Four	Suit: ♠		Value: 4
Rank: Ten	Suit: ♠		Value: 10
Rank: King	Suit: ♠		Value: 10
Rank: Three	Suit: ♣		Value: 3
Rank: Ten	Suit: ♣		Value: 10
Rank: Ace	Suit: ♠		Value: 11
Rank: Six	Suit: ♥		Value: 6
Rank: Two	Suit: ♠		Value: 2
Rank: Nine	Suit: ♣		Value: 9
Rank: Nine	Suit: ♥		Value: 9
Rank: Two	Suit: ♦		Value: 2
Rank: Eight	Suit: ♠		Value: 8
Rank: Six	Suit: ♦		Value: 6
Rank: Five	Suit: ♥		Value: 5
Rank: Queen	Suit: ♠		Value: 10
Rank: Six	Suit: ♣		Value: 6
Rank: Six	Suit: ♠		Value: 6
Rank: Two	Suit: ♠		Value: 2
Rank: Three	Suit: ♦		Value: 3
Rank: King	Suit: ♥		Value: 10


TEST resetShuffle: 

The shoe contains the first 90 cards of two unshuffled decks.
The shoe will reshuffle because there is less than twenty cards left.

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

New, reshuffled shoe: 

Number of Decks: 2

Shoe (Bottom card is last on list, top card is first on list): 

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

The first 80 cards in a default two-deck shoe. The shoe should not reset
and shuffle because there are more than 20 cards left in it.

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

Remaining cards in shoe: 

Number of Decks: 2

Shoe (Bottom card is last on list, top card is first on list): 

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

 */
