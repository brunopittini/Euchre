//COP 3330
//Assignment 7
//Bruno Pittini

package core;

import constants.Constants;
import constants.Constants.Suit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import userinterface.GameUi;

public class Game 
{
    // member variables
    private Card trump;
    private Player leadPlayer;
    private Player dealer;
    private Player wonTrick;
    private int round;
    private ArrayList<Team> teams;
    private Deck deck;
    private Scanner scan;
    private ArrayList<Player> table;
    private int dealerIdx;
    private int leadIdx;
    private GameUi ui;
    private int trumpCheck;
    private Team trumpTeam;
    
    //custom constructor
    public Game()
    {
        createTeams();
//        outputTeams();
        createDeck();
        setTable();
        dealHand();
        displayHands();
//        play();
    }
    
    private void createTeams()
    {
        // instantiate the teams ArrayList
        teams = new ArrayList();
        
        // instantiate Team One and add to ArrayList
        Team teamOne = new Team();  
        teamOne.setTeamName("Team One");
        teams.add(teamOne);
        
        // instantiate Team Two and add to ArrayList
        Team teamTwo = new Team();
        teamTwo.setTeamName("Team Two");
        teams.add(teamTwo);
        
        // adding Human Player to Team One 
        String name = JOptionPane.showInputDialog("Enter human player name");
//        scan = new Scanner(System.in);
//        System.out.println("Enter human player name");
//        String name = scan.next();        
        
        HumanPlayer hp = new HumanPlayer();
        hp.setName(name);
        hp.setGame(this);
        System.out.println("Human Player added to Team One");
        teamOne.getTeam().add(hp);
        
        // create the AI Players and add them to a team
        for(int p = 1; p <= Constants.NUM_AI_PLAYERS; p++)
        {
            AiPlayer aip = new AiPlayer();
            aip.setName("AI-" + p);
            aip.setGame(this);
            // add AI Player to a team
            if(teamOne.getTeam().size() < 2)
                teamOne.getTeam().add(aip);
            else
                teamTwo.getTeam().add(aip);            
        }
    }

    private void outputTeams()
    {      
        for(Team team : teams)
        {      
            team.outputTeam();
        } 
    }
    
    private void setTable()
    {
        // players are set up so that team members sit across from each other
        // therefore the deal would be to TeamOne.PlayerOne, TeamTwo.PlayerTwo,
        // TeamOne.PlayerTwo, TeamTwo.PlayerTwo as an example
        table = new ArrayList();
        
        // get the teams in the game
        Team teamOne = teams.get(Constants.ONE);
        Team teamTwo = teams.get(Constants.TWO);
        
        // get the players from each team
        Player teamOnePlayerOne = teamOne.getTeam().get(Constants.ONE);
        Player teamOnePlayerTwo = teamOne.getTeam().get(Constants.TWO);
        Player teamTwoPlayerOne = teamTwo.getTeam().get(Constants.ONE);
        Player teamTwoPlayerTwo = teamTwo.getTeam().get(Constants.TWO);
        
        // we want to explicitly dictate which seat each player is in so we are 
        // using the add method that takes two arguments, one to set the position
        // in the ArrayList and the associated object at that position
        table.add(0, teamOnePlayerOne);
        table.add(1, teamTwoPlayerOne);
        table.add(2, teamOnePlayerTwo);
        table.add(3, teamTwoPlayerTwo);
        
        System.out.println("************************");
        System.out.println("Players at the table are");
        System.out.println("************************");

        for(Player player : table)
        {
            System.out.println(player.getName());
        }
    }
    
    private void setDealerAndLead()
    {
        // select the first dealer
        Random random = new Random();
        dealerIdx = random.nextInt(Constants.NUM_PLAYERS);
        dealer = table.get(dealerIdx);  
        
        // create an index to keep track of which player got the card; 
        // reset when get to 3
        // set the leadIdx based on which player was selected as the dealer and
        // add one to it
        if(dealerIdx < 3)
            leadIdx = dealerIdx + 1;
        else
            leadIdx = 0;
        
        leadPlayer = table.get(leadIdx);
    }
    
    private void dealHand()
    {
        setDealerAndLead();
        
        System.out.println("********************************");
        System.out.println("          DEALING THE HAND");
        System.out.println("********************************");

        System.out.println("Player " + dealer.getName() + " will deal the hand");

        int playerIdx = leadIdx;
        
        // loop through the shuffled deck and deal five cards to each player
        // first round, two at a time
        // second round, three at a time
        
        Iterator<Card> currentCard = deck.getCardList().iterator();
        
//        System.out.println("********************************");
//        System.out.println("   FIRST DEAL, TWO CARDS EACH");
//        System.out.println("********************************");

        for(int p = 0; p < Constants.NUM_PLAYERS; p++)
        {
            dealOne(playerIdx, currentCard);
            
            // increment the player index until value of 3, then reset to 0
            if(playerIdx == 3)
                playerIdx = 0;
            else
                playerIdx++;
        }

//        System.out.println("********************************");
//        System.out.println("   SECOND DEAL, THREE CARDS EACH");
//        System.out.println("********************************");

        for(int p = 0; p < Constants.NUM_PLAYERS; p++)
        {
            dealTwo(playerIdx, currentCard);
            
            // increment the player index until value of 3, then reset to 0
            if(playerIdx == 3)
                playerIdx = 0;
            else
                playerIdx++;        
        }
        
        // set trump to next card on deck
        trump = currentCard.next();
        
        System.out.println("********************************");
        System.out.println("Trump card is " + trump.getFace() + " of " + 
                            trump.getSuit() + " color " + trump.getColor());
        System.out.println("********************************");
    }

    public Card getTrump()
    {
        return trump;
    }
    
    private void dealOne(int playerIdx, Iterator<Card> currentCard)
    {       
        for(int c = 0; c < Constants.DEAL_ONE; c++)
        {            
            if(currentCard.hasNext())
            {
                Card card = currentCard.next();

//                System.out.println("Dealing " + card.getFace() + " of " + 
//                                    card.getSuit() + " to player " + 
//                                    table.get(playerIdx).getName());
                
                // add card to a player's hand
                table.get(playerIdx).getHand().add(card);
                // remove the card from the deck after it has been dealt
                currentCard.remove();
            }
        }
    }        
    
    private void dealTwo(int playerIdx, Iterator<Card> currentCard)
    {
        for(int c = 0; c < Constants.DEAL_TWO; c++)
        {            
            if(currentCard.hasNext())
            {
                Card card = currentCard.next();

//                System.out.println("Dealing " + card.getFace() + " of " + 
//                                    card.getSuit() + " to player " + 
//                                    table.get(playerIdx).getName());
                
                // add card to a player's hand
               table.get(playerIdx).getHand().add(card);

                // remove the card from the deck after it has been dealt
                currentCard.remove();
            }
        }       
    }
    
    private void displayHands()
    {
        for(Team team : teams)
        {
            team.outputHands();
        }
    }
    
    private void createDeck()
    {
        deck = new Deck();  
    }
    
    public void play()
    {
//        for(Player player : table)
//        {
//            player.makeTrump();
//        }
        trumpCheck();
    }
    
    public void trumpCheck()
    {
        trumpCheck = 0;
        int currentPlayer = leadIdx;
        
        while (trumpCheck < Constants.NUM_PLAYERS)
        {
            Player newPlayer = table.get(currentPlayer);
            newPlayer.makeTrump();
            
            if(newPlayer.getAcceptTrump())
            {
                for (Team team : teams)
                {
                    if (team.getTeam().contains(newPlayer))
                    {
                        trumpTeam = team;
                        JOptionPane.showMessageDialog(this.getGameUi().getFrame(), trumpTeam.getTeamName() + " has called trump");
                    }
                }
                break;
            }
            else
            {
                trumpCheck++;
            }
            
            currentPlayer++;
            if (currentPlayer > 3)
                currentPlayer = 0;
        }
    }
    
    // Getters and Setters
    public GameUi getGameUi()
    {
        return ui;
    }
    
    public void setGameUi(GameUi ui)
    {
        this.ui = ui;
    }
    
    /**
     * @return the wonTrick
     */
    public Player getWonTrick() 
    {
        return wonTrick;
    }

    /**
     * @param wonTrick the wonTrick to set
     */
    public void setWonTrick(Player wonTrick) 
    {
        this.wonTrick = wonTrick;
    }

    /**
     * @return the round
     */
    public int getRound() 
    {
        return round;
    }

    /**
     * @param round the round to set
     */
    public void setRound(int round) 
    {
        this.round = round;
    }

    /**
     * @return the leadPlayer
     */
    public Player getLeadPlayer() 
    {
        return leadPlayer;
    }

    /**
     * @param leadPlayer the leadPlayer to set
     */
    public void setLeadPlayer(Player leadPlayer) 
    {
        this.leadPlayer = leadPlayer;
    }

    /**
     * @return the dealer
     */
    public Player getDealer() 
    {
        return dealer;
    }

    /**
     * @param dealer the dealer to set
     */
    public void setDealer(Player dealer) 
    {
        this.dealer = dealer;
    }

    /**
     * @return the table
     */
    public ArrayList<Player> getTable() 
    {
        return table;
    }
    
    public ArrayList<Team> getTeams()
    {
        return teams;
    }

    public int getTrumpCheck() {
        return trumpCheck;
    }
    
    
}
