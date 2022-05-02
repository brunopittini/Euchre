//COP 3330
//Assignment 7
//Bruno Pittini

package euchre;

import core.Game;
import userinterface.GameUi;
import javax.swing.JOptionPane;

public class Euchre 
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        // outputting to the console
        System.out.println("Welcome to Euchre!");

        JOptionPane.showMessageDialog(null, "Let's Play Euchre!"); 
        
        Game game = new Game();
        GameUi ui = new GameUi(game);
       
    }    
}
