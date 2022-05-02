//COP 3330
//Assignment 7
//Bruno Pittini

package userinterface;

import constants.Constants.Face;
import constants.Constants.Suit;
import core.Card;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;

public class CardUi 
{
    // member variables
    private Card card;
    private ImageIcon imageIcon;
    private JButton button;
    private JLabel label;
    private int position;
    
    // Custom Constructors
    public CardUi(Card card, JButton button)
    {
        this.card = card;
        this.button = button;
        selectFrontImage(this.button);
    }
    
    public CardUi(Card card, JLabel label, int position)
    {
        this.card = card;
        this.label = label;
        this.position = position;
        
        if(position == 1 || position == 3)
        {
            selectVerticalBackImage();
        }
        else
        {
            selectHorizontalBackImage();        
        }
    }
    
    public CardUi(JLabel label) 
    {
        this.label = label;
        selectHorizontalBackImage();
    }
    
    public CardUi(JLabel label, Face face, Suit suit)
    {
        this.label = label;
        Card card = new Card();
        card.setFace(face);
        card.setSuit(suit);
        this.card = card;
        selectFrontImage(this.label);
    }
    
    // Methods used by Contructors
    public void selectFrontImage(JComponent component)
    {
        String filename = "../images/";
        
        switch (card.getFace())
        {
            case ACE:
                filename += "Ace";
                break;
            case KING:
                filename += "King";
                break;
            case QUEEN:
                filename += "Queen";
                break;
            case JACK:
                filename += "Jack";
                break;
            case TEN:
                filename += "Ten";
                break;
            case NINE:
                filename += "Nine";
                break;
        }
        
        switch (card.getSuit())
        {
            case CLUBS:
                filename += "Clubs";
                break;
            case HEARTS:
                filename += "Hearts";
                break;
            case DIAMONDS:
                filename += "Diamonds";
                break;
            case SPADES:
                filename += "Spades";
                break;
        }
        
        filename += ".png";
        
        try
        {
            URL imgURL = getClass().getResource(filename);
            
            if(imgURL != null)
            {
                imageIcon = new ImageIcon(imgURL);
            }
            
            if(component instanceof JButton)
                button = new JButton(imageIcon);
            else
                label = new JLabel(imageIcon);
        }
        catch(Exception ex)
        {
            System.err.println("Couldn't find file: " + filename);
            imageIcon = null;
        }
    }
    
    public void selectVerticalBackImage()
    {
        String filename = "../images/backVertical.jpg";
        try
        {
            URL imgURL = getClass().getResource(filename);
            
            if(imgURL != null)
            {
                imageIcon = new ImageIcon(imgURL);
                Image newImage = imageIcon.getImage().getScaledInstance(100, 75, java.awt.Image.SCALE_SMOOTH);
                imageIcon = new ImageIcon(newImage);
                label = new JLabel(imageIcon);
            }
        }
        catch(Exception ex) 
        {
            System.err.println("Couldn't find file: " + filename);
            imageIcon = null;
        }
    }
    
    public void selectHorizontalBackImage()
    {
        String filename = "../images/backHorizontal.jpg";
        try
        {
            URL imgURL = getClass().getResource(filename);
            
            if(imgURL != null)
            {
                imageIcon = new ImageIcon(imgURL);
                Image newImage = imageIcon.getImage().getScaledInstance(75, 100, java.awt.Image.SCALE_SMOOTH);
                imageIcon = new ImageIcon(newImage);
                label = new JLabel(imageIcon);
            }
        }
        catch(Exception ex) 
        {
            System.err.println("Couldn't find file: " + filename);
            imageIcon = null;
        }
    }
    
    // Getters and Setters
    public JButton getButton() {
        return button;
    }

    public void setButton(JButton button) {
        this.button = button;
    }

    public JLabel getLabel() {
        return label;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }
    
}
