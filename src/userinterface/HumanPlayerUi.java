//COP 3330
//Assignment 7
//Bruno Pittini

package userinterface;

import core.HumanPlayer;
import core.Player;
import constants.Constants;
import constants.Constants.Face;
import constants.Constants.Suit;
import core.Card;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class HumanPlayerUi extends JPanel
{
    private HumanPlayer human;
    private ArrayList<JButton> cards;
    private CardUi cardUi;
    private GameUi gameUi;
    private JFrame parent;
    private Suit suit;
    
    public HumanPlayerUi(Player player, GameUi gameUi)
    {
        human = (HumanPlayer)player;
        this.gameUi = gameUi;
        
        initComponents();
    }
    
    private void initComponents()
    {
        this.setBorder(BorderFactory.createTitledBorder(human.getName()));
        this.setMinimumSize(new Dimension(250, 150));
        this.setPreferredSize(new Dimension(250, 150));
        
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        displayCards();
    }
    
    private void displayCards()
    {                        
        cards = new ArrayList<JButton>();

        for(int c = 0; c < Constants.CARDS_EACH; c++)
        {
            JButton card = new JButton();
            cardUi = new CardUi(human.getHand().get(c), card);
            card = cardUi.getButton();
            card.setMinimumSize(new Dimension(60,100));
            card.setPreferredSize(new Dimension(60,100));
            card.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            cards.add(card);
            
            for(JButton button : cards)
                this.add(button);
        }
    }

}
