//COP 3330
//Assignment 7
//Bruno Pittini

package core;

import java.util.ArrayList;
import javax.swing.JPanel;

public abstract class Player implements IPlayer
{
    // member variables
    private String name;
    private int tricks;
    private int score;
    private ArrayList<Card> hand;
    private JPanel ui;
    private boolean acceptTrump;
    
    // abstract method from IPlayer
    public abstract Card playCard();
    public abstract void makeTrump();

    public Player()
    {
        hand = new ArrayList();
    }
    
    public void setUi(JPanel ui)
    {
        this.ui = ui;
    }
    
    public JPanel getUi()
    {
        return ui;
}

        public void sortBySuit()
    {    
        /**
         * Sorts the cards in the hand so that cards are sorted into
         * order of increasing value.  Cards with the same value 
         * are sorted by suit. Note that aces are considered
         * to have the highest value.
         */
        
        ArrayList<Card> sortedHand = new ArrayList<Card>();
        
        while (hand.size() > 0) 
        {
            int position = 0;  // Position of minimal card.
            Card firstCard = hand.get(0);  // Minimal card.
            
            for (int i = 1; i < hand.size(); i++) 
            {
                Card nextCard = hand.get(i);
                
                if (nextCard.getSuit().getRank() < firstCard.getSuit().getRank() ||
                   (nextCard.getSuit() == firstCard.getSuit() && 
                    nextCard.getFace().getValue() < firstCard.getFace().getValue())) 
                {
                    position = i;
                    firstCard = nextCard;
                }
            }
            
            hand.remove(position);
            sortedHand.add(firstCard);
        }
        
        hand = sortedHand;
    }
        
    /**
     * @return the name
     */
    public String getName() 
    {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) 
    {
        this.name = name;
    }

    /**
     * @return the tricks
     */
    public int getTricks() 
    {
        return tricks;
    }

    /**
     * @param tricks the tricks to set
     */
    public void setTricks(int tricks) 
    {
        this.tricks = tricks;
    }

    /**
     * @return the score
     */
    public int getScore() 
    {
        return score;
    }

    /**
     * @param score the score to set
     */
    public void setScore(int score) 
    {
        this.score = score;
    }

    /**
     * @return the hand
     */
    public ArrayList<Card> getHand() 
    {
        return hand;
    }

    /**
     * @param hand the hand to set
     */
    public void setHand(ArrayList<Card> hand) 
    {
        this.hand = hand;
    }

    public boolean getAcceptTrump() {
        return acceptTrump;
    }

    public void setAcceptTrump(boolean acceptTrump) {
        this.acceptTrump = acceptTrump;
    }
    
    public void displayHand()
    {
        System.out.println("*************************");
        System.out.println("Player " + name + " hand is ");
        System.out.println("*************************");
                
        for(Card card : hand)
        {
            System.out.println(card.getFace() + " of " + card.getSuit());
        }
    }
}
