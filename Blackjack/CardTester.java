/**
 * CardTester.java 
 *
 * @author: Matt Rosenbaum
 * Assignment #: Blackjack Project
 * 
 * Brief Program Description: This program creates
 * the tester for the card class.
 *
 */

public class CardTester 
{
    public static void main(String[] args)
    {
        //Constructs new cards
        Card c = new Card("♥", "Queen");
        Card c1 = new Card("♣", "Seven");
        Card c2 = new Card("♠", "Ace");
        Card c3 = new Card("♦", "Jack");
        Card c4 = new Card("♣", "Four");

        //Prints out each card
        System.out.println(c);
        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3);
        System.out.println(c4);
        
        //If the card's rank is "Ace", then this
        //test will change the value of the card
        //from 11 to 1
        System.out.println("\nTEST changeAce\n");   
        c2.changeAce();
        System.out.println(c2);
        c3.changeAce();
        System.out.println(c3);
    }
}
/*

Output:

Rank: Queen	Suit: ♥		Value: 10
Rank: Seven	Suit: ♣		Value: 7
Rank: Ace	Suit: ♠		Value: 11
Rank: Jack	Suit: ♦		Value: 10
Rank: Four	Suit: ♣		Value: 4

TEST changeAce

Rank: Ace	Suit: ♠		Value: 1
Rank: Jack	Suit: ♦		Value: 10
	
*/