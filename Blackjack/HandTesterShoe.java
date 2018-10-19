/**
 * HandTesterShoe.java 
 *
 * @author: Matt Rosenbaum
 * Assignment #: Blackjack Project
 * 
 * Brief Program Description: This program tests
 * the hand class, and has the shoe class incorporated
 *
 */

public class HandTesterShoe 
{
   public static void main(String[] args)
   {
       //Creates a show w/ 2 decks,
       //A hand for the player,
       //And a hand for the dealer
       Shoe s = new Shoe(2);
       Hand ph = new Hand();
       Hand dh = new Hand();
       
       //Shuffles and prints the shoe
       s.shuffle();
       System.out.println(s + "\n");
       
       //Player will get the top and third card of shoe
       //Dealer will get second and fourth card of shoe
       ph.addCard(s.drawCard());
       dh.addCard(s.drawCard());
       ph.addCard(s.drawCard());
       dh.addCard(s.drawCard());
       
       System.out.println(ph + "\n");
       System.out.println(dh);
       
       System.out.println("\n\n" + s);       
   }
}
/*

Output:

Number of Decks: 2

Shoe (Bottom card is last on list, top card is first on list): 

Rank: Queen	Suit: ♦		Value: 10
Rank: Four	Suit: ♠		Value: 4
Rank: King	Suit: ♠		Value: 10
Rank: Five	Suit: ♣		Value: 5
Rank: Eight	Suit: ♥		Value: 8
Rank: Seven	Suit: ♠		Value: 7
Rank: Three	Suit: ♦		Value: 3
Rank: Four	Suit: ♦		Value: 4
Rank: Queen	Suit: ♦		Value: 10
Rank: Three	Suit: ♣		Value: 3
Rank: Queen	Suit: ♥		Value: 10
Rank: Nine	Suit: ♥		Value: 9
Rank: Three	Suit: ♦		Value: 3
Rank: Six	Suit: ♣		Value: 6
Rank: Four	Suit: ♥		Value: 4
Rank: Ace	Suit: ♠		Value: 11
Rank: Seven	Suit: ♦		Value: 7
Rank: King	Suit: ♠		Value: 10
Rank: Seven	Suit: ♦		Value: 7
Rank: Ace	Suit: ♣		Value: 11
Rank: Ten	Suit: ♦		Value: 10
Rank: Ten	Suit: ♦		Value: 10
Rank: Five	Suit: ♥		Value: 5
Rank: Ten	Suit: ♣		Value: 10
Rank: Jack	Suit: ♥		Value: 10
Rank: Eight	Suit: ♥		Value: 8
Rank: Queen	Suit: ♠		Value: 10
Rank: Nine	Suit: ♣		Value: 9
Rank: Seven	Suit: ♠		Value: 7
Rank: Three	Suit: ♠		Value: 3
Rank: Seven	Suit: ♣		Value: 7
Rank: Nine	Suit: ♣		Value: 9
Rank: Nine	Suit: ♥		Value: 9
Rank: Ten	Suit: ♠		Value: 10
Rank: Jack	Suit: ♣		Value: 10
Rank: Jack	Suit: ♥		Value: 10
Rank: Seven	Suit: ♥		Value: 7
Rank: King	Suit: ♣		Value: 10
Rank: Six	Suit: ♦		Value: 6
Rank: Nine	Suit: ♠		Value: 9
Rank: Ace	Suit: ♦		Value: 11
Rank: Eight	Suit: ♦		Value: 8
Rank: Ace	Suit: ♥		Value: 11
Rank: Three	Suit: ♠		Value: 3
Rank: Eight	Suit: ♠		Value: 8
Rank: Nine	Suit: ♠		Value: 9
Rank: Ace	Suit: ♠		Value: 11
Rank: Two	Suit: ♦		Value: 2
Rank: Seven	Suit: ♥		Value: 7
Rank: Six	Suit: ♦		Value: 6
Rank: Five	Suit: ♠		Value: 5
Rank: Four	Suit: ♥		Value: 4
Rank: Five	Suit: ♠		Value: 5
Rank: Jack	Suit: ♠		Value: 10
Rank: Three	Suit: ♥		Value: 3
Rank: Two	Suit: ♣		Value: 2
Rank: King	Suit: ♥		Value: 10
Rank: Queen	Suit: ♣		Value: 10
Rank: Five	Suit: ♣		Value: 5
Rank: Queen	Suit: ♥		Value: 10
Rank: Six	Suit: ♥		Value: 6
Rank: Two	Suit: ♠		Value: 2
Rank: Two	Suit: ♠		Value: 2
Rank: Five	Suit: ♥		Value: 5
Rank: Two	Suit: ♦		Value: 2
Rank: Ace	Suit: ♥		Value: 11
Rank: Jack	Suit: ♦		Value: 10
Rank: Jack	Suit: ♣		Value: 10
Rank: Three	Suit: ♥		Value: 3
Rank: Nine	Suit: ♦		Value: 9
Rank: Three	Suit: ♣		Value: 3
Rank: Four	Suit: ♠		Value: 4
Rank: Five	Suit: ♦		Value: 5
Rank: Two	Suit: ♣		Value: 2
Rank: Seven	Suit: ♣		Value: 7
Rank: Eight	Suit: ♣		Value: 8
Rank: Eight	Suit: ♠		Value: 8
Rank: Ace	Suit: ♣		Value: 11
Rank: Six	Suit: ♥		Value: 6
Rank: Six	Suit: ♠		Value: 6
Rank: King	Suit: ♣		Value: 10
Rank: King	Suit: ♥		Value: 10
Rank: Eight	Suit: ♦		Value: 8
Rank: Four	Suit: ♣		Value: 4
Rank: Six	Suit: ♠		Value: 6
Rank: Two	Suit: ♥		Value: 2
Rank: King	Suit: ♦		Value: 10
Rank: Eight	Suit: ♣		Value: 8
Rank: Nine	Suit: ♦		Value: 9
Rank: Ten	Suit: ♠		Value: 10
Rank: Five	Suit: ♦		Value: 5
Rank: Queen	Suit: ♣		Value: 10
Rank: Four	Suit: ♦		Value: 4
Rank: Jack	Suit: ♦		Value: 10
Rank: Ten	Suit: ♥		Value: 10
Rank: Ace	Suit: ♦		Value: 11
Rank: Jack	Suit: ♠		Value: 10
Rank: Two	Suit: ♥		Value: 2
Rank: Ten	Suit: ♥		Value: 10
Rank: Ten	Suit: ♣		Value: 10
Rank: Four	Suit: ♣		Value: 4
Rank: Queen	Suit: ♠		Value: 10
Rank: King	Suit: ♦		Value: 10
Rank: Six	Suit: ♣		Value: 6


Hand: 

Rank: Queen	Suit: ♦		Value: 10
Rank: King	Suit: ♠		Value: 10

Hand Value: 20


Hand: 

Rank: Four	Suit: ♠		Value: 4
Rank: Five	Suit: ♣		Value: 5

Hand Value: 9



Number of Decks: 2

Shoe (Bottom card is last on list, top card is first on list): 

Rank: Eight	Suit: ♥		Value: 8
Rank: Seven	Suit: ♠		Value: 7
Rank: Three	Suit: ♦		Value: 3
Rank: Four	Suit: ♦		Value: 4
Rank: Queen	Suit: ♦		Value: 10
Rank: Three	Suit: ♣		Value: 3
Rank: Queen	Suit: ♥		Value: 10
Rank: Nine	Suit: ♥		Value: 9
Rank: Three	Suit: ♦		Value: 3
Rank: Six	Suit: ♣		Value: 6
Rank: Four	Suit: ♥		Value: 4
Rank: Ace	Suit: ♠		Value: 11
Rank: Seven	Suit: ♦		Value: 7
Rank: King	Suit: ♠		Value: 10
Rank: Seven	Suit: ♦		Value: 7
Rank: Ace	Suit: ♣		Value: 11
Rank: Ten	Suit: ♦		Value: 10
Rank: Ten	Suit: ♦		Value: 10
Rank: Five	Suit: ♥		Value: 5
Rank: Ten	Suit: ♣		Value: 10
Rank: Jack	Suit: ♥		Value: 10
Rank: Eight	Suit: ♥		Value: 8
Rank: Queen	Suit: ♠		Value: 10
Rank: Nine	Suit: ♣		Value: 9
Rank: Seven	Suit: ♠		Value: 7
Rank: Three	Suit: ♠		Value: 3
Rank: Seven	Suit: ♣		Value: 7
Rank: Nine	Suit: ♣		Value: 9
Rank: Nine	Suit: ♥		Value: 9
Rank: Ten	Suit: ♠		Value: 10
Rank: Jack	Suit: ♣		Value: 10
Rank: Jack	Suit: ♥		Value: 10
Rank: Seven	Suit: ♥		Value: 7
Rank: King	Suit: ♣		Value: 10
Rank: Six	Suit: ♦		Value: 6
Rank: Nine	Suit: ♠		Value: 9
Rank: Ace	Suit: ♦		Value: 11
Rank: Eight	Suit: ♦		Value: 8
Rank: Ace	Suit: ♥		Value: 11
Rank: Three	Suit: ♠		Value: 3
Rank: Eight	Suit: ♠		Value: 8
Rank: Nine	Suit: ♠		Value: 9
Rank: Ace	Suit: ♠		Value: 11
Rank: Two	Suit: ♦		Value: 2
Rank: Seven	Suit: ♥		Value: 7
Rank: Six	Suit: ♦		Value: 6
Rank: Five	Suit: ♠		Value: 5
Rank: Four	Suit: ♥		Value: 4
Rank: Five	Suit: ♠		Value: 5
Rank: Jack	Suit: ♠		Value: 10
Rank: Three	Suit: ♥		Value: 3
Rank: Two	Suit: ♣		Value: 2
Rank: King	Suit: ♥		Value: 10
Rank: Queen	Suit: ♣		Value: 10
Rank: Five	Suit: ♣		Value: 5
Rank: Queen	Suit: ♥		Value: 10
Rank: Six	Suit: ♥		Value: 6
Rank: Two	Suit: ♠		Value: 2
Rank: Two	Suit: ♠		Value: 2
Rank: Five	Suit: ♥		Value: 5
Rank: Two	Suit: ♦		Value: 2
Rank: Ace	Suit: ♥		Value: 11
Rank: Jack	Suit: ♦		Value: 10
Rank: Jack	Suit: ♣		Value: 10
Rank: Three	Suit: ♥		Value: 3
Rank: Nine	Suit: ♦		Value: 9
Rank: Three	Suit: ♣		Value: 3
Rank: Four	Suit: ♠		Value: 4
Rank: Five	Suit: ♦		Value: 5
Rank: Two	Suit: ♣		Value: 2
Rank: Seven	Suit: ♣		Value: 7
Rank: Eight	Suit: ♣		Value: 8
Rank: Eight	Suit: ♠		Value: 8
Rank: Ace	Suit: ♣		Value: 11
Rank: Six	Suit: ♥		Value: 6
Rank: Six	Suit: ♠		Value: 6
Rank: King	Suit: ♣		Value: 10
Rank: King	Suit: ♥		Value: 10
Rank: Eight	Suit: ♦		Value: 8
Rank: Four	Suit: ♣		Value: 4
Rank: Six	Suit: ♠		Value: 6
Rank: Two	Suit: ♥		Value: 2
Rank: King	Suit: ♦		Value: 10
Rank: Eight	Suit: ♣		Value: 8
Rank: Nine	Suit: ♦		Value: 9
Rank: Ten	Suit: ♠		Value: 10
Rank: Five	Suit: ♦		Value: 5
Rank: Queen	Suit: ♣		Value: 10
Rank: Four	Suit: ♦		Value: 4
Rank: Jack	Suit: ♦		Value: 10
Rank: Ten	Suit: ♥		Value: 10
Rank: Ace	Suit: ♦		Value: 11
Rank: Jack	Suit: ♠		Value: 10
Rank: Two	Suit: ♥		Value: 2
Rank: Ten	Suit: ♥		Value: 10
Rank: Ten	Suit: ♣		Value: 10
Rank: Four	Suit: ♣		Value: 4
Rank: Queen	Suit: ♠		Value: 10
Rank: King	Suit: ♦		Value: 10
Rank: Six	Suit: ♣		Value: 6

 */