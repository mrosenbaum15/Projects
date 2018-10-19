/**
 * Card.java  
 *
 * @author: Matt Rosenbaum
 * Assignment #: Blackjack Project
 * 
 * Brief Program Description: This program creates
 * the blueprint for the card class.
 *
 */
public class Card
{
    public int value;
    public String rank;
    public String suit;

    /**
     * Constructs the card class
     * 
     * @param s The suit of the card
     * @param r The rank of the card
     * 
     */
    public Card(String suit, String rank)
    {
        this.suit = suit;
        this.rank = rank;        
        if(getRank().equals("Ace"))
        {
            value = 11;
        }
        else if(getRank().equals("King") || getRank().equals("Queen") ||
        getRank().equals("Jack") || getRank().equals("Ten"))
        {
            value = 10;
        }
        else if(getRank().equals("Nine"))
        {
            value = 9;
        }
        else if(getRank().equals("Eight"))
        {
            value = 8;
        }
        else if(getRank().equals("Seven"))
        {
            value = 7;
        }
        else if(getRank().equals("Six"))
        {
            value = 6;
        }
        else if(getRank().equals("Five"))
        {
            value = 5;
        }
        else if(getRank().equals("Four"))
        {
            value = 4;
        }
        else if(getRank().equals("Three"))
        {
            value = 3;
        }
        else
        {
            value = 2;
        }
    }

    /**
     * This method will return the rank of the card
     * 
     * @return rank
     */
    public String getRank()
    {
        return rank;
    }

    /**
     * This method will return the suit of the card
     * 
     * @return suit
     */
    public String getSuit()
    {
        return suit;
    }

    /**
     * This method will change the value of an ace to 1
     */
    public void changeAce()
    {                
        if(getRank().equals("Ace"))
        {
            value = 1;
        }
    }

    public int getValue()
    {
        return value;
    }

    /**
     * This method will create a toString to print out
     * the suit, rank, and value
     * 
     * @return twine
     */
    public String toString()
    { 
        String twine = "";
        twine = "Rank: " + getRank() + "\tSuit: " + getSuit() + "\t\tValue: " + value;
        return twine;
    }
}
