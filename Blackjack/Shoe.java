import java.util.ArrayList;
/**
 * Shoe.java  
 *
 * @author: Matt Rosenbaum
 * Assignment #: Blackjack Project
 * 
 * Brief Program Description: This program creates
 * the blueprint for the shoe class
 *
 */
public class Shoe extends Deck
{
    private int numDecks;
    /**
     * Constructs the shoe class
     * 
     * @param n The number of decks in the shoe
     */
    public Shoe(int n)
    {
        numDecks = n;
        for(int i = 1; i < n; i++)
        {
            super.fillDeck();
        }
    }

    /**
     * This method will get and return the shoe
     * 
     * @return super.getDeck()
     */
    public ArrayList<Card> getShoe()
    {
        return super.getDeck();
    }

    /**
     * This method will get the number of decks
     * in the shoe
     * 
     * @return numDecks
     */
    public int getNumDecks()
    {
        return numDecks;
    }

    /**
     * This method will shuffle all of the cards in the shoe
     */
    public void shuffle()
    {
        super.shuffle();
    }

    /**
     * This method will reset the deck 
     * and shuffle the cards when the 
     * amount of cards in a deck is less than 20.
     */
    public void resetShuffle()
    {
        for(int i = 1; i < numDecks; i++) 
        {
            super.resetShuffle();
        }
    }

    /**
     * This method creates a toString that will
     * print out the shoe
     * 
     * @return wire
     */
    public String toString()
    {
        String wire = "";
        String zipper = "";
        zipper = "Number of Decks: " + getNumDecks() + "\n" + "\nShoe (Bottom card is last on list, top card is first on list): \n\n";
        for(Card c : super.getDeck())
        {
            wire += c + "\n";
        }
        return zipper + wire;
    }
}
