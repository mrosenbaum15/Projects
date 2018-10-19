import java.util.ArrayList;
/**
 * Deck.java  
 *
 * @author: Matt Rosenbaum
 * Assignment #: Blackjack Project
 * 
 * Brief Program Description: This program creates
 * the blueprint for the deck class.
 * (no reset or shuffle)
 *
 */
public class Deck
{
    private ArrayList<Card> deck;
    /**
     * Constructs the deck class
     * Creates a deck with cards in the deck
     * in the correct order
     */
    public Deck()
    {
        deck = new ArrayList<Card>();
        fillDeck();
    }

    /**
     * This method fills the deck
     */
    public void fillDeck()
    {
        String[] ranks = {"Ace", "Two", "Three", "Four", "Five",
                "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};

        String attempt = "";
        
        for(int i = 0; i < ranks.length; i++)
        {
            deck.add(new Card("♥", ranks[i]));
        }
        
        for(int i = 0; i < ranks.length; i++)
        {
            deck.add(new Card("♣", ranks[i]));
        }
        
        for(int i = ranks.length - 1; i >= 0; i--)
        {
            deck.add(new Card("♦", ranks[i]));
        }
        
        for(int i = ranks.length - 1; i >= 0; i--)
        {
            deck.add(new Card("♠", ranks[i]));
        }
    }

    /**
     * This method will create a new array list
     * The card at the top of the deck will be added
     * to the new array list, and then that card
     * will be removed from the deck
     * 
     * @return draw
     */
    public Card drawCard()
    {
        Card c = deck.get(0);
        deck.remove(c);
        return c;
    }

    /**
     * This method will return the deck
     * 
     * @return deck
     */
    public ArrayList<Card> getDeck()
    {
        return deck;
    }

    /**
     * This method will shuffle the deck of cards
     */
    public void shuffle()
    {
        ArrayList<Card> j = new ArrayList<Card>();

        for(int i = deck.size() - 1; i >= 0; i--)
        {
            int index = (int)(Math.random() * deck.size());
            j.add(deck.get(index));
            deck.remove(index);
        }
        deck = j;
    }

    /**
     * This method will reset the deck 
     * and shuffle the cards when the 
     * amount of cards in a deck is less than 20.
     */
    public void resetShuffle()
    {
        if(deck.size() < 11)
        {
            System.out.println("Reshuffling...");
            deck.clear();
            fillDeck();
            shuffle();
        }
    }

    /**
     * This method creates a toString that will
     * print out the deck
     * 
     * @return thread
     */
    public String toString()
    {
        String thread = "";
        String pipe = "";
        pipe = "Deck (Bottom card is last on list, top card is first on list): \n\n";
        for(Card c : deck)
        {
            thread += c + "\n";
        }
        return pipe + thread;
    }
}
