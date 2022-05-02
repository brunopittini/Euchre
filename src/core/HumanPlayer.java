//COP 3330
//Assignment 7
//Bruno Pittini

package core;

import constants.Constants;
import javax.swing.JOptionPane;

public class HumanPlayer extends Player 
{
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
            JOptionPane.showMessageDialog(game.getGameUi().getFrame(), "Sorry dealer, you have to accept trump!");
            this.setAcceptTrump(true);
        }
        else
        {
            int response = JOptionPane.showConfirmDialog(game.getGameUi().getFrame(), "Do you accept the trump suit?", 
                    "Select an option", JOptionPane.YES_NO_CANCEL_OPTION);
            
            if(response == JOptionPane.YES_OPTION)
                this.setAcceptTrump(true);
            else
                this.setAcceptTrump(false);
        }
    }  
    
    // Setters and getters
    public void setGame(Game game) {
        this.game = game;
    }
    
}
