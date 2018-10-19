import java.util.ArrayList;
import java.util.Scanner;
/**
 * Player.java  
 *
 * @author: Matt Rosenbaum
 * Assignment #: Blackjack Project
 * 
 * Brief Program Description: This program creates
 * the blueprint for the player class
 *
 *-hand
 *-name(String)
 *-Bankroll (double)
 *getCard()
 *Bet()
 *doubleDown()
 */
public class Player
{
    public Hand hand;
    private String name;
    private int bankroll;
    private Scanner sc;
    private int pot;

    /**
     * Constructs the player class
     * 
     * @param n Name of the player
     * @param b The player's bankroll
     */
    public Player(String n, int b)
    {
        hand = new Hand();
        name = n;
        bankroll = b;
        sc = new Scanner(System.in);
    }  

    /**
     * Gets the player's hand
     * 
     * @return hand
     */
    public Hand getHand()
    {
        return hand;
    }

    /**
     * Get's the pot or the bet of the player
     * 
     * @return pot
     */
    public int getPot()
    {
        return pot;
    }

    /**
     * Sets the value of the pot
     * 
     * @param pot The pot
     */
    public void setPot(int pot)
    {
        this.pot = pot;
    }

    /**
     * Sets the value of the player's bankroll
     * 
     * @param bankroll The player's bankroll
     */
    public void setBankroll(int bankroll)
    {
        this.bankroll = bankroll;
    }

    /**
     * Gets the value of the player's bankroll
     * 
     * @return bankroll
     */
    public int getBankroll()
    {
        return bankroll;
    }

    /**
     * Gets the player's name
     * 
     * @return name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Asks the player what he or she would like to bet
     */
    public void bet()
    {
        boolean b = true;

        System.out.println("What would you like to bet? Must be equal to or above $10.\n\nYou may enter a bet that is more than half of your bankroll,\nbut you will not be allowed to double down.\n\nOtherwise, place a bet worth less than half ($" + (getBankroll() / 2) + ").\n\nThe bet must have an even-numbered value.\n\nNO DECIMALS OR STRINGS!!\n");
        int bet = sc.nextInt();
        while(b)
        {
            sc = new Scanner(System.in);
            if(bet > getBankroll() || bet == 0 || bet % 2 != 0 || (bet < 10 && bet < 0))
            {
                System.out.println("\nInvalid entry. Please enter an even-numbered value of 10 or more, but not greater than your bankroll.\n");
                bet = sc.nextInt();
            }
            else if(bet < 0)
            {
                System.out.println("\nInvalid entry.\nYou can't have a negative bet. Please enter an even-numbered value of 10 or more, but not greater than your bankroll.\n");
                bet = sc.nextInt();  
            }
            else
            {
                b = false;
            }
        }
        pot = bet;
        System.out.println("\f\nOkay, " + getName() + ". Your bet is: $" + pot + ".");
    }

    /**
     * Asks the player if he or she would like to double their bet
     * 
     * If yes, pot = pot * 2
     */
    public void doubleDown()
    {
        boolean dub = true;
        if(hand.getHand().size() == 2 && !hand.blackjack())
        {
            sc.nextLine();
            System.out.println("\nDouble down? Enter yes or no.");
            String d = sc.nextLine();
            while(dub)
            {
                sc = new Scanner(System.in);
                if(d.equalsIgnoreCase("Yes"))
                {
                    dub = true;
                    pot *= 2;   
                    System.out.println("Your bet is now: $" + pot + ".\n");
                }
                else
                {
                    System.out.println("Invalid entry. Please enter yes or no.");
                    d = sc.nextLine();
                }
            }
        }        
    }
}
