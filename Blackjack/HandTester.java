/**
 * HandTester.java 
 *
 * @author: Matt Rosenbaum
 * Assignment #: Blackjack Project
 * 
 * Brief Program Description: This program will
 * test the hand class
 *
 */

public class HandTester 
{
    public static void main(String[] args)
    {
        //Creates a new hand
        Hand h = new Hand();

        System.out.println("\nEXPECTED: \n\nCards: Seven of Clubs, Six of Diamonds, Tens of Spades\nHand Value: 23\nPlayer does not have blackjack\nPlayer does not have five card charlie\nPlayer busted\n");

        //Adds three  cards to the hand
        h.addCard(new Card("♣", "Seven"));
        h.addCard(new Card("♦", "Six"));
        h.addCard(new Card("♠", "Ten"));
        System.out.println(h);

        //Checks to see if the player has blackjack
        System.out.println("\n\nTEST blackjack");
        System.out.println("\nThe player has blackjack: " + h.blackjack());

        //Checks to see if the player has fiveCardCharlie
        System.out.println("\n\nTEST fiveCardCharlie");
        System.out.println("\nFive Card Charlie: " + h.fiveCardCharlie());    

        //Checks to see if the player has busted
        System.out.println("\n\nTEST bust");
        System.out.println("\nThe player busted: " + h.bust());

        //Clears the hand completely
        h.clearHand();

        System.out.println("\nEXPECTED: \n\nCards: Jack of Clubs, Ace of Clubs\nHand Value: 21\nPlayer does have blackjack\nPlayer does not have five card charlie\nPlayer did not bust\n");

        //Adds two new cards to the hand
        h.addCard(new Card("♣", "Jack"));
        h.addCard(new Card("♣", "Ace"));
        System.out.println("\nNew Hand: \n\n" + h + "\n");

        System.out.println("\n\nTEST blackjack");
        System.out.println("\nThe player has blackjack: " + h.blackjack());

        System.out.println("\n\nTEST fiveCardCharlie");
        System.out.println("\nFive Card Charlie: " + h.fiveCardCharlie());    

        System.out.println("\n\nTEST bust");
        System.out.println("\nThe player busted: " + h.bust());

        //Clears the hand again
        h.clearHand();
        h.addCard(new Card("♦", "Two"));
        h.addCard(new Card("♣", "Five"));
        h.addCard(new Card("♦", "Three"));
        h.addCard(new Card("♥", "Four"));
        h.addCard(new Card("♥", "Two"));
        System.out.println("\nEXPECTED: \n\nCards: Two of Diamonds, Five of Clubs, Three of Diamonds, Four of Hearts, Two of Hearts\nHand Value: 16\nPlayer does not have blackjack\nPlayer does have five card charlie\nPlayer did not bust\n");
        System.out.println("\nNew Hand: \n\n" + h + "\n");

        System.out.println("\n\nTEST blackjack");
        System.out.println("\nThe player has blackjack: " + h.blackjack());

        System.out.println("\n\nTEST fiveCardCharlie");
        System.out.println("\nFive Card Charlie: " + h.fiveCardCharlie());    

        System.out.println("\n\nTEST bust");
        System.out.println("\nThe player busted: " + h.bust());

        h.clearHand();

        System.out.println("\n\nACE TESTS (x3)");
        h.addCard(new Card("♣", "Ace"));
        h.addCard(new Card("♦", "Two"));
        h.addCard(new Card("♦", "Ace"));
        System.out.println("\nEXPECTED: \n\nCards: Ace of Clubs, Two of Diamonds, Ace of Diamonds\nHand Value: 14\n");
        System.out.println(h);

        h.clearHand();

        System.out.println("\n\nACE TEST");
        h.addCard(new Card("♣", "Ace"));
        h.addCard(new Card("♦", "Two"));
        h.addCard(new Card("♦", "Ace"));
        h.addCard(new Card("♥", "King"));
        System.out.println("\nEXPECTED: \n\nCards: Ace of Clubs, Two of Diamonds, Ace of Diamonds, King of Hearts\nHand Value: 14\n");
        System.out.println(h);

        h.clearHand();

        System.out.println("\n\nACE TEST");
        h.addCard(new Card("♣", "Ace"));
        h.addCard(new Card("♦", "Two"));
        h.addCard(new Card("♦", "Ace"));
        h.addCard(new Card("♥", "Ace"));
        h.addCard(new Card("♣", "Eight"));
        System.out.println("\nEXPECTED: \n\nCards: Ace of Clubs, Two of Diamonds, Ace of Diamonds, Ace of Hearts, Eight of Clubs\nHand Value: 14\n");
        System.out.println(h);
    }
}
/*

Output: 



EXPECTED: 

Cards: Seven of Clubs, Six of Diamonds, Tens of Spades
Hand Value: 23
Player does not have blackjack
Player does not have five card charlie
Player busted

Hand: 

Rank: Seven	Suit: ♣		Value: 7
Rank: Six	Suit: ♦		Value: 6
Rank: Ten	Suit: ♠		Value: 10

Hand Value: 23



TEST blackjack

The player has blackjack: false


TEST fiveCardCharlie

Five Card Charlie: false


TEST bust

The player busted: true

EXPECTED: 

Cards: Jack of Clubs, Ace of Clubs
Hand Value: 21
Player does have blackjack
Player does not have five card charlie
Player did not bust


New Hand: 

Hand: 

Rank: Jack	Suit: ♣		Value: 10
Rank: Ace	Suit: ♣		Value: 11

Hand Value: 21




TEST blackjack

The player has blackjack: true


TEST fiveCardCharlie

Five Card Charlie: false


TEST bust

The player busted: false

EXPECTED: 

Cards: Two of Diamonds, Five of Clubs, Three of Diamonds, Four of Hearts, Two of Hearts
Hand Value: 16
Player does not have blackjack
Player does have five card charlie
Player did not bust


New Hand: 

Hand: 

Rank: Two	Suit: ♦		Value: 2
Rank: Five	Suit: ♣		Value: 5
Rank: Three	Suit: ♦		Value: 3
Rank: Four	Suit: ♥		Value: 4
Rank: Two	Suit: ♥		Value: 2

Hand Value: 16




TEST blackjack

The player has blackjack: false


TEST fiveCardCharlie

Five Card Charlie: true


TEST bust

The player busted: false


ACE TESTS (x3)

EXPECTED: 

Cards: Ace of Clubs, Two of Diamonds, Ace of Diamonds
Hand Value: 14

Hand: 

Rank: Ace	Suit: ♣		Value: 1
Rank: Two	Suit: ♦		Value: 2
Rank: Ace	Suit: ♦		Value: 11

Hand Value: 14



ACE TEST

EXPECTED: 

Cards: Ace of Clubs, Two of Diamonds, Ace of Diamonds, King of Hearts
Hand Value: 14

Hand: 

Rank: Ace	Suit: ♣		Value: 1
Rank: Two	Suit: ♦		Value: 2
Rank: Ace	Suit: ♦		Value: 1
Rank: King	Suit: ♥		Value: 10

Hand Value: 14



ACE TEST

EXPECTED: 

Cards: Ace of Clubs, Two of Diamonds, Ace of Diamonds, Ace of Hearts, Eight of Clubs
Hand Value: 14

Hand: 

Rank: Ace	Suit: ♣		Value: 1
Rank: Two	Suit: ♦		Value: 2
Rank: Ace	Suit: ♦		Value: 1
Rank: Ace	Suit: ♥		Value: 1
Rank: Eight	Suit: ♣		Value: 8

Hand Value: 13

 */
