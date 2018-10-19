import java.util.ArrayList;
/**
 * Hand.java  
 *
 * @author: Matt Rosenbaum
 * Assignment #: Blackjack Project
 * 
 * Brief Program Description: This program creates
 * the blueprint for the hand class
 *
 */
public class Hand
{
    private ArrayList<Card> hand;
    /**
     * Constructs the hand class
     */
    public Hand()
    {
        hand = new ArrayList<Card>();
    }

    /**
     * This method adds a card to the hand
     * 
     * @param c The card
     * @return hand
     */
    public ArrayList<Card> addCard(Card c)
    {
        hand.add(c);
        return hand;
    }

    /**
     * This method returns the hand
     * 
     * @return hand
     */
    public ArrayList<Card> getHand()
    {
        return hand;
    }

    /**
     * This method will get the value of the hand
     * 
     * @return value
     */
    public int getHandValue()
    {
        ArrayList<Card> aces = new ArrayList<Card>();
        int value = 0;
        for(Card c : hand)
        {
            if(c.getValue() == 11)
            {
                aces.add(c);
            }
            value += c.getValue();
            for(Card ca : aces)
            {
                if(value > 21)
                {
                    ca.changeAce();
                    value -= 10;
                }
            }
        }
        return value;
    }

    /**
     * This method will determine whether the hand
     * is a five card charlie
     * If the size of the hand equals 5 and
     * the value of the hand is less than 21,
     * then the method returns true
     * 
     * @return boolean
     */
    public boolean fiveCardCharlie()
    {
        if((hand.size() == 5) && (getHandValue() < 21))
        {
            return true;
        }
        return false;
    }

    /**
     * This method will determine whether the
     * player has blackjack or not
     * 
     * @return boolean
     */
    public boolean blackjack()
    {
        if((hand.size() == 2) && (getHandValue() == 21))
        {
            return true;
        }
        return false;
    }

    /**
     * This method will determine if the player's
     * hand busts or not
     * 
     * @return boolean
     */
    public boolean bust()
    {
        if(getHandValue() > 21)
        {
            return true;
        }
        return false;
    }

    /**
     * This method will clear the hand
     */
    public void clearHand()
    {
        hand.clear();
    }

    /**
     * This method creates a toString to tell
     * the value of the hand and the cards in the hand
     * 
     * @return yarn
     */
    public String toString()
    {

        String plug = "";
        String yarn = "";
        String stream = "";
        plug = "Hand: \n\n"; 
        for(Card c : hand)
        {
            getHandValue();
            yarn += c + "\n";
        }
        stream = "\nHand Value: " + getHandValue() + "\n"; 
        return plug + yarn + stream;
    }
}
