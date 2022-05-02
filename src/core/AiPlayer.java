//COP 3330
//Assignment 7
//Bruno Pittini

package core;

import constants.Constants;
import javax.swing.JOptionPane;
import userinterface.AiPlayerUi;

public class AiPlayer extends Player
{
    private AiPlayerUi aiUi;
    private Game game;
    
    @Override
    public Card playCard() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void makeTrump() 
    {
        if(game.getTrumpCheck() == Constants.MAX_PASSES)
        {
            JOptionPane.showMessageDialog(game.getGameUi().getFrame(), "Sorry dealer, you have to accept the trump!");
            this.setAcceptTrump(true);
        }
        else
        {
            int trumpCounter = 0;
            for(Card hand: this.getHand())
            {
                if(hand.getSuit() == game.getTrump().getSuit())
                    trumpCounter++;
            }
            
            if (trumpCounter >= Constants.MIN_TRUMP)
            {
                this.setAcceptTrump(true);
                JOptionPane.showMessageDialog(game.getGameUi().getFrame(), this.getName() + " says \"Pick it Up!\"");
            }
            else
            {
                this.setAcceptTrump(false);
                JOptionPane.showMessageDialog(game.getGameUi().getFrame(), this.getName() + " says \"Pass\"");
            }
        }
    }
    
    // Getters and Setters

    public void setGame(Game game) {
        this.game = game;
    }
    
}
