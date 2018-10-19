import java.util.Scanner;
import java.lang.Thread;
import java.util.ArrayList;
/**
 * Dealer.java  
 *
 * @author: Matt Rosenbaum
 * Assignment #: Blackjack Project
 * 
 * Brief Program Description: This program creates
 * the blueprint for the dealer class.
 *
 *-hand
 *-shoe
 *deal()
 *start()
 *offerInsurance()
 *payout()
 */
public class Dealer
{
    private Player player;
    private Shoe shoe;
    private Scanner sc; 
    private int numDecks;
    private Hand hand;
    private Hand h;
    boolean again = true;

    /**
     * Constructs the dealer class
     * 
     * @param p The player
     * @param n The number of decks to be used
     */    
    public Dealer(Player p, int n)
    {
        hand = new Hand();
        shoe = new Shoe(n);
        shoe.shuffle();
        h = new Hand();
        player = p;
        numDecks = n;
        sc = new Scanner(System.in);
    }

    /**
     * Starts the game
     */
    public void start()
    throws java.lang.InterruptedException
    {       
        sc = new Scanner(System.in);
        System.out.println("\f");
        player.bet();
        deal();
        shoe.resetShuffle();
    }

    /**
     * Returns the dealer's hand
     * 
     * @return h
     */
    public Hand getHand()
    {
        return h;
    }

    /**
     * Calls for the dealer to deal the first cards of the hand
     */
    public void deal()
    throws java.lang.InterruptedException
    {
        System.out.println("\n");
        dealFirst();
    }

    /**
     * Deals the player's first card, then the dealer's first card,
     * and finally deals that player's second card
     */
    public void dealFirst()
    throws java.lang.InterruptedException
    {
        player.getHand().addCard(shoe.drawCard());
        h.addCard(shoe.drawCard());
        player.getHand().addCard(shoe.drawCard());      
        System.out.println("Your " + player.getHand());
        offerInsurance();
    }   

    /**
     * Checks to see if the player has blackjack before continuing with the game
     */
    public void checkBlackjack()
    throws java.lang.InterruptedException
    {
        displayHand();
        if(player.getHand().blackjack() && (h.getHandValue() != 10 || h.getHandValue() != 11))
        {
            payout();
        }
        else if(player.getHand().blackjack() && (!h.blackjack()))
        {
            payout();
        }
        else
        {
            doubleDown();
        }
    }

    /**
     * Asks the player if he or she would like to double their bet
     * 
     * If yes, pot = pot * 2
     */
    public void doubleDown()
    throws java.lang.InterruptedException
    {
        int pot = player.getPot();
        boolean dub = true;
        if(!h.blackjack() && player.getPot() <= (player.getBankroll() * .5))
        {
            System.out.println("\nDouble down? Enter yes or no.\n");
            String d = sc.nextLine();
            System.out.println();
            while(dub)
            {
                sc = new Scanner(System.in);
                if(d.equalsIgnoreCase("Yes"))
                {
                    System.out.println("\f");
                    player.setPot(pot *= 2);   
                    System.out.println("Your bet is now: $" + pot + ".\n");
                    player.getHand().addCard(shoe.drawCard());
                    System.out.println("Your " + player.getHand());
                    if(player.getHand().bust())
                    {
                        dub = false;
                        payout();
                    }
                    else
                    {
                        dub = false;
                        dealDealerNext();
                        payout();
                    }
                }
                else if(d.equalsIgnoreCase("No"))
                {
                    dub = false;
                    dealPlayerNext();
                }
                else
                {
                    System.out.println("\nInvalid entry. Please enter yes or no.\n");
                    d = sc.nextLine();
                    System.out.println();
                }
            }
        }
        else
        {
            dealPlayerNext();
        }
    }

    /**
     * Deals the player's next cards, asking the player
     * if he or she wants to hit or stand
     */
    public void dealPlayerNext()
    throws java.lang.InterruptedException
    {
        boolean finish = true;
        while(finish)
        {
            sc = new Scanner(System.in);
            System.out.println("Hit or stand?\n");
            String hit = sc.nextLine(); 
            if(hit.equalsIgnoreCase("Hit"))
            {
                player.getHand().addCard(shoe.drawCard());
                System.out.println("\f\nYour " + player.getHand());
                displayHand();
                if((player.getHand().getHandValue() == 21 && !player.getHand().blackjack()))
                {
                    finish = false; 
                    dealDealerNext();
                    payout();                         
                }
                else if(player.getHand().getHandValue() > 21)
                {
                    finish = false;
                    payout();
                }
                else if(player.getHand().fiveCardCharlie())
                {
                    if(h.getHandValue() != 11 || h.getHandValue() != 10)
                    {
                        finish = false;
                        payout();
                    }
                    else if(h.getHandValue() == 11 || h.getHandValue() == 10)
                    {
                        h.addCard(shoe.drawCard());
                        System.out.println("Dealer's " + h);
                        if(h.blackjack())
                        {
                            finish = false;
                            System.out.println("You lose! The dealer had blackjack and you didn't.\nYour bankroll is now: $" + (player.getBankroll() - player.getPot())); 
                            player.setBankroll(player.getBankroll() - player.getPot());
                        }
                        else
                        {
                            finish = false;
                            System.out.println("Winner! You got five card charlie!\nYou win: $" + player.getPot());
                            System.out.println("Your bankroll is now: $" + (player.getBankroll() + player.getPot()));
                            player.setBankroll(player.getBankroll() + player.getPot());
                        }
                    }
                }
            }
            else if(hit.equalsIgnoreCase("Stand"))
            {                  
                finish = false;  
                System.out.println("\f\nYour " + player.getHand());
                dealDealerNext();
                payout();
            }
            else
            {
                System.out.println("\nInvalid entry. Please enter hit or stand.\n");
            }
        }
    }

    /**
     * Displays the first card of the dealer's hand
     */
    public void displayHand()
    {
        for(int i = 2; i < hand.getHand().size(); i++)
        {
            h.addCard(hand.getHand().get(i));
        }
        System.out.println("Dealer's " + h);
    }

    /**
     * Adds cards to the dealer's hand until the dealer's hand value is
     * 17 or above
     */
    public void dealDealerNext()
    {       
        while((h.getHandValue() < 17) && !h.blackjack())
        {
            h.addCard(shoe.drawCard());
        }
        System.out.println("\nDealer's " + h);
    }

    /**
     * Offers the user insurance if the dealer's first card is an ace,
     * which pays 2:1
     */
    public void offerInsurance()
    throws java.lang.InterruptedException
    {
        boolean o = true;
        if(h.getHandValue() == 11)
        {
            displayHand();
            while(o)
            {
                sc = new Scanner(System.in);
                System.out.println("\nWould you like insurance? Enter yes or no.\n");
                String i = sc.nextLine();
                if(i.equalsIgnoreCase("Yes"))
                {
                    System.out.println("\nYour total bet is now: $" + (player.getPot() + (player.getPot() / 2)) + "\n");
                    boolean finish = true;
                    while(finish)
                    {
                        if(player.getHand().blackjack())
                        {
                            finish = false; 
                            dealDealerNext();
                            if(h.blackjack())
                            {
                                System.out.println("Dealer has blackjack.\nYou won insurance but pushed the hand.\nYour bankroll is now: $" + (player.getBankroll() + (player.getPot() / 2)));
                                player.setBankroll(player.getBankroll() + (player.getPot() / 2));
                            }
                            else
                            {
                                System.out.println("Dealer does not have blackjack.\nYou lost insurance but got blackjack and won the hand.\nYour bankroll is now: $" + (player.getBankroll() + ((player.getPot() * 3 / 2) - (player.getPot() / 2))));
                                player.setBankroll(player.getBankroll() + ((player.getPot() * 3 / 2) - (player.getPot() / 2)));
                            }
                        }
                        else
                        {
                            sc = new Scanner(System.in);
                            System.out.println("Hit or stand?\n");
                            String hit = sc.nextLine(); 
                            if(hit.equalsIgnoreCase("Hit"))
                            {
                                player.getHand().addCard(shoe.drawCard());
                                System.out.println("\f\nYour " + player.getHand());
                                displayHand();
                                if((player.getHand().getHandValue() == 21 && !player.getHand().blackjack()))
                                {
                                    finish = false; 
                                    dealDealerNext();
                                    if(h.blackjack())
                                    {
                                        System.out.println("Dealer has blackjack.\nYou won insurance but lost the hand.\nYour bankroll is now: $" + (player.getBankroll() - (player.getPot() / 2)));
                                        player.setBankroll(player.getBankroll() - (player.getPot() / 2));
                                    }
                                    else if((player.getHand().getHandValue() > h.getHandValue()) && !player.getHand().bust())
                                    {
                                        System.out.println("Dealer did not have blackjack.\nYou lost insurance but won the hand.\nYour bankroll is now: $" + (player.getBankroll() + (player.getPot() / 2)));
                                        player.setBankroll(player.getBankroll() + (player.getPot() / 2));
                                    }
                                    else if(h.bust() && !player.getHand().bust())
                                    {
                                        System.out.println("Dealer did not have blackjack.\nYou lost insurance but won the hand.\nYour bankroll is now: $" + (player.getBankroll() + (player.getPot() / 2)));
                                        player.setBankroll(player.getBankroll() + (player.getPot() / 2));
                                    }
                                    else
                                    {
                                        System.out.println("Dealer did not have blackjack.\nYou lost insurance and pushed the hand.\nYour bankroll is now: $" + (player.getBankroll() - (player.getPot() / 2)));
                                        player.setBankroll(player.getBankroll() - (player.getPot() / 2));
                                    }
                                }
                                else if(player.getHand().getHandValue() > 21)
                                {
                                    finish = false;
                                    dealDealerNext();
                                    if(h.blackjack())
                                    {
                                        System.out.println("Dealer had blackjack. You won insurance but lost the hand.\nYour bankroll is now: $" + (player.getBankroll() - (player.getPot() / 2)));
                                        player.setBankroll(player.getBankroll() - (player.getPot() / 2));
                                    }
                                    else
                                    {
                                        System.out.println("Dealer didn't have blackjack. You lost insurance and lost the hand.\nYour bankroll is now: $" + (player.getBankroll() - (player.getPot() * 3 / 2)));
                                        player.setBankroll(player.getBankroll() - (player.getPot() * 3 / 2));
                                    }
                                }
                                else if(player.getHand().fiveCardCharlie())
                                {
                                    finish = false;
                                    dealDealerNext();
                                    if(h.blackjack())
                                    {
                                        System.out.println("Dealer had blackjack.\nYou won insurance but lost the hand.\nYour bankroll is now: $" + (player.getBankroll() - (player.getPot() / 2)));
                                        player.setBankroll(player.getBankroll() - (player.getPot() / 2));
                                    }
                                    else if(!h.blackjack())
                                    {
                                        System.out.println("Dealer did not have blackjack.\nYou lost insurance but won the hand, as you had five card charlie.\nYour bankroll is now: $" + (player.getBankroll() + (player.getPot() / 2)));
                                        player.setBankroll(player.getBankroll() + (player.getPot() / 2));
                                    }
                                }
                            }
                            else if(hit.equalsIgnoreCase("Stand"))
                            {                  
                                finish = false;  
                                System.out.println("\f\nYour " + player.getHand());
                                dealDealerNext();
                                if(h.blackjack())
                                {
                                    System.out.println("Dealer had blackjack.\nYou won insurance but lost the hand.\nYour bankroll is now: $" + (player.getBankroll() - (player.getPot() / 2)));
                                    player.setBankroll(player.getBankroll() - (player.getPot() / 2));
                                }
                                else if(player.getHand().getHandValue() > h.getHandValue() && !player.getHand().bust())
                                {
                                    System.out.println("Dealer did not have blackjack.\nYou lost insurance but won the hand.\nYour bankroll is now: $" + (player.getBankroll() + (player.getPot() / 2)));
                                    player.setBankroll(player.getBankroll() + (player.getPot() / 2));
                                }
                                else if(h.getHandValue() > player.getHand().getHandValue() && (h.getHandValue() <= 21))                                 
                                {
                                    System.out.println("Dealer did not have blackjack.\nYou lost insurance and lost the hand.\nYour bankroll is now: $" + (player.getBankroll() - (player.getPot())));
                                    player.setBankroll(player.getBankroll() - (player.getPot() * 3 /2));
                                }
                                else if(h.bust())
                                {
                                    System.out.println("Dealer did not have blackjack.\nYou lost insurance but won the hand.\nYour bankroll is now: $" + (player.getBankroll() + (player.getPot() / 2)));
                                    player.setBankroll(player.getBankroll() + (player.getPot() / 2));
                                }
                                else
                                {
                                    System.out.println("Dealer did not have blackjack.\nYou lost insurance and pushed the hand.\nYour bankroll is now: $" + (player.getBankroll() - (player.getPot() / 2)));
                                    player.setBankroll(player.getBankroll() - (player.getPot() / 2));
                                }
                            }
                            else
                            {
                                System.out.println("Invalid entry. Please enter hit or stand.");
                            }
                        }
                        o = false;
                    }
                }
                else if(i.equalsIgnoreCase("No"))
                {
                    o = false;
                    System.out.println("\f\nYour " + player.getHand());
                    checkBlackjack();
                }
                else
                {
                    System.out.println("Invalid entry. Please enter yes or no.");
                }
            }
        }
        else
        {
            checkBlackjack();
        }
    }

    /**
     * Decides of the player won or lost
     * Also changes the player's bankroll based off of the result of the hand
     */
    public void payout()
    {
        if(h.blackjack()) 
        {
            System.out.println("You lose! The dealer had blackjack and you didn't.\nYour bankroll is now: $" + (player.getBankroll() - player.getPot())); 
            player.setBankroll(player.getBankroll() - player.getPot());
        }
        else if(player.getHand().fiveCardCharlie())
        {
            System.out.println("Winner! You got five card charlie!\nYou win: $" + player.getPot());
            System.out.println("Your bankroll is now: $" + (player.getBankroll() + player.getPot()));
            player.setBankroll(player.getBankroll() + player.getPot());
        }
        else if((h.getHandValue() > player.getHand().getHandValue() && !h.bust()))
        {
            System.out.println("You lose! The dealer had a greater hand value than you.\nYour bankroll is now: $" + (player.getBankroll() - player.getPot()));
            player.setBankroll(player.getBankroll() - player.getPot());
        }
        else if(player.getHand().bust())
        {
            System.out.println("You lose! You busted.\nYour bankroll is now: $" + (player.getBankroll() - player.getPot()));
            player.setBankroll(player.getBankroll() - player.getPot());
        }
        else if(h.bust())       
        {
            System.out.println("Winner! The dealer busted.\nYou win: $" + player.getPot());
            System.out.println("Your bankroll is now: $" + (player.getBankroll() + player.getPot()));
            player.setBankroll(player.getBankroll() + player.getPot());
        }
        else if(player.getHand().getHandValue() > h.getHandValue() && (!player.getHand().bust() && (!player.getHand().blackjack())))
        {
            System.out.println("Winner! You had a greater hand value than the dealer.\nYou win: $" + player.getPot());
            System.out.println("Your bankroll is now: $" + (player.getBankroll() + player.getPot()));
            player.setBankroll(player.getBankroll() + player.getPot());
        }       
        else if(player.getHand().blackjack())
        {
            System.out.println("Winner! You got blackjack!\nYou win: $" + player.getPot());
            System.out.println("Your bankroll is now: $" + (player.getBankroll() + (player.getPot() * 3 / 2)));
            player.setBankroll(player.getBankroll() + (player.getPot() * 3 / 2));
        }
        else if((player.getHand().getHandValue() == h.getHandValue()))
        {
            System.out.println("Push. You and the dealer had the same hand value.\nYou get your bet back.");
            System.out.println("\nYour bankroll is still: $" + (player.getBankroll()));
        }   
    }
}