import java.util.Scanner;
/**
 * Game.java 
 *
 * @author: Matt Rosenbaum
 * Assignment #: Blackjack Project
 * 
 * Brief Program Description: This program will
 * run the blackjack game
 *
 *dealer.start()
 */

public class Game
{
    public static void main(String[] args)
    throws java.lang.InterruptedException
    {
        Scanner sc = new Scanner(System.in);
        boolean hr = true;
        boolean bankroll = true;
        boolean decks = true;
        boolean g = true;
        boolean pa = true;

        System.out.println("\n❀Welcome to the Casino of Roses!❀");
        System.out.println("\nHopefully you brought some cash!");
        System.out.println("\nBefore we get started, you need to know the House Rules.");

        System.out.println("\n\nHouse Rules:\n\n");
        System.out.println("1) You can use any number of decks from 1-6.");
        System.out.println("2) Set any bankroll you want, just not zero.");
        System.out.println("3) You cannot double down if you bet more than half of your bankroll.");
        System.out.println("4) Otherwise, if the player doubles down, they will be dealt only one more card,");
        System.out.println("and then the dealer will finish its hand.");
        System.out.println("5) If the player has a hand size of five cards and has not busted, and the dealer");
        System.out.println("does not have blackjack, the user has Five Card Charlie and wins the hand.");
        System.out.println("6) Blackjack pays 3:2.");
        System.out.println("7) No splitting.");
        System.out.println("8) Dealer draws on 16 and stands on all 17's.");
        System.out.println("9) Beating the dealer pays even money.");
        System.out.println("10) If dealer and player both get blackjack or have same hand value, it is a push.");
        System.out.println("11) You cannot place a bet with an odd-numbered value.");
        System.out.println("12) You cannot place a bet of less than $10.");
        System.out.println("13) Insurance pays 2:1 and is only offered if the dealer pulls an ace.");
        System.out.println("\nHit enter when finished.\n");

        String x = sc.nextLine();

        while(hr)
        {
            if(x.equals(""))
            {
                hr = false;
            }
            else
            {
                System.out.println("Invalid entry. Hit enter to advance.\n");
                x = sc.nextLine();
            }
        }

        System.out.println("\f\nWhat is your name?\n");
        String name = sc.nextLine();

        System.out.println("\nOkay, " + name + ". What would you like your bankroll to be? Must be at least 100.\n\nNO DECIMALS OR STRINGS!!\n");
        int bank = sc.nextInt();

        while(bankroll)
        {
            if(bank == 0 || (bank < 100 && bank > 0))
            {
                System.out.println("\nInvalid entry. Enter an integer of 100 or more.\n");
                bank = sc.nextInt();
            }
            else if(bank < 0)
            {
                System.out.println("\nInvalid entry.You can't have a negative bankroll. Enter an integer of 100 or more.\n");
                bank = sc.nextInt(); 
            }
            else
            {
                bankroll = false;
            }
        }
        System.out.println("\nYour bankroll is: $" + bank + "\n");
        sc.nextLine();

        System.out.println(name + ", how many decks do you want to use? 1 - 6.\n");
        int deck = sc.nextInt();
        while(decks)
        {
            if(deck > 0 && deck < 7)
            {
                decks = false;
            }
            else if(deck < 0)
            {
                System.out.println("\nInvalid entry. You cannot have a negative number of decks. Enter a value between 1 and 6.\n");
                deck = sc.nextInt();
            }
            else
            {
                System.out.println("\nInvalid entry. Enter a value between 1 and 6.\n");
                deck = sc.nextInt();
            }
        }

        System.out.println("\f");
        Player p = new Player(name, bank);
        Dealer d = new Dealer(p, deck);

        sc.nextLine();

        System.out.println("Hit enter to start the game!\n");
        String game = sc.nextLine();
        while(g)
        {
            if(game.equals(""))
            {
                g = false;
            }
            else
            {
                System.out.println("\nInvalid entry. Hit enter to advance.");
                game = sc.nextLine();
            }
        }

        System.out.println("\f");
        d.start();
        
        if (p.getBankroll() == 0)
        {
            System.out.println("\nGame over! Your bankroll hit zero. Thanks for playing!");
        }
        else
        {
            while(pa)
            {
                sc = new Scanner(System.in);
                System.out.println("\nPlay again? Yes or no.\n");
                String again = sc.nextLine();
                if(again.equalsIgnoreCase("yes"))
                {
                    p.getHand().clearHand();
                    d.getHand().clearHand();
                    d.start();
                }
                else if(again.equalsIgnoreCase("no"))
                {
                    pa = false;
                    System.out.println("\f\n\n\n\n\n\n\n\n\n\n\t\t\t\t   Thanks for playing!\n\t\t\tYou started with: $" + bank + "  You finished with: $" + p.getBankroll() + "\n\t\t\t\t   Please come again.");
                }
                else
                {
                    System.out.println("\nInvalid entry. Please enter yes or no.\n");
                }
            }
        }
    }
}
/*

Output:

❀Welcome to the Casino of Roses!❀

Hopefully you brought some cash!

Before we get started, you need to know the House Rules.

House Rules:

1) You can use any number of decks from 1-6.
2) Set any bankroll you want, just not zero.
3) You cannot double down if you bet more than half of your bankroll.
4) Otherwise, if the player doubles down, they will be dealt only one more card,
and then the dealer will finish its hand.
5) If the player has a hand size of five cards and has not busted, and the dealer
does not have blackjack, the user has Five Card Charlie and wins the hand.
6) Blackjack pays 3:2.
7) No splitting.
8) Dealer draws on 16 and stands on all 17's.
9) Beating the dealer pays even money.
10) If dealer and player both get blackjack or have same hand value, it is a push.
11) You cannot place a bet with an odd-numbered value.
12) You cannot place a bet of less than $10.
13) Insurance pays 2:1 and is only offered if the dealer pulls an ace.

Hit enter when finished.

What is your name?
Matt
Okay, Matt. What would you like your bankroll to be? Must be at least 100.
1000
Your bankroll is: $1000
Matt, how many decks do you want to use? 1 - 6.
2
Hit enter to start the game!
What would you like to bet? Must be equal to or above $10.
You may enter a bet that is more than half of your bankroll,
but you will not be allowed to double down.

Otherwise, place a bet worth less than half ($500).

The bet must have an even-numbered value.

100

Okay, Matt. Your bet is: $100.

Your Hand: 
Rank: Four  Suit: ♠     Value: 4
Rank: Three Suit: ♦     Value: 3

Hand Value: 7

Dealer's Hand: 

Rank: Five  Suit: ♣     Value: 5

Hand Value: 5

Double down? Enter yes or no.
No
Hit or stand?
hit
Your Hand: 
Rank: Four  Suit: ♠     Value: 4
Rank: Three Suit: ♦     Value: 3
Rank: Ace   Suit: ♠     Value: 11

Hand Value: 18

Dealer's Hand: 

Rank: Five  Suit: ♣     Value: 5

Hand Value: 5

Hit or stand?

stand

Your Hand: 

Rank: Four  Suit: ♠     Value: 4
Rank: Three Suit: ♦     Value: 3
Rank: Ace   Suit: ♠     Value: 11

Hand Value: 18

Dealer's Hand: 
Rank: Five  Suit: ♣     Value: 5
Rank: Nine  Suit: ♦     Value: 9
Rank: Ten   Suit: ♣     Value: 10

Hand Value: 24

Winner! The dealer busted.
You win: $100
Your bankroll is now: $1100

Play again? Yes or no.

yes

What would you like to bet? Must be equal to or above $10.
You may enter a bet that is more than half of your bankroll,
but you will not be allowed to double down.

Otherwise, place a bet worth less than half ($550).

The bet must have an even-numbered value.

100

Okay, Matt. Your bet is: $100.

Your Hand: 
Rank: Six   Suit: ♦     Value: 6
Rank: Five  Suit: ♦     Value: 5

Hand Value: 11

Dealer's Hand: 

Rank: Seven Suit: ♦     Value: 7

Hand Value: 7

Double down? Enter yes or no.
Yes

Your bet is now: $200.

Your Hand: 

Rank: Six   Suit: ♦     Value: 6
Rank: Five  Suit: ♦     Value: 5
Rank: King  Suit: ♥     Value: 10

Hand Value: 21

Dealer's Hand: 
Rank: Seven Suit: ♦     Value: 7
Rank: Queen Suit: ♣     Value: 10

Hand Value: 17

Winner! You had a greater hand value than the dealer.
You win: $200
Your bankroll is now: $1300

Play again? Yes or no.

No

Thanks for playing!
You started with: $1000  You finished with: $1300
Please come again.

 */