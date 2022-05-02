//COP 3330
//Assignment 7
//Bruno Pittini

package core;

import constants.Constants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Deck
{
    private Set<Card> deck;
    private List<Card> cardList;
    
    public Deck()
    {
        System.out.println("****************************");
        System.out.println("Generating the deck of cards");
        System.out.println("****************************");
        generateDeck();
        
        System.out.println("****************************");
        System.out.println("Displaying the deck of cards");
        System.out.println("****************************");
        displayDeck();
        
        System.out.println("***************************");
        System.out.println("Shuffling the deck of cards");
        System.out.println("***************************");
        shuffleDeck();
        
        System.out.println("*************************************");
        System.out.println("Displaying the shuffled deck of cards");
        System.out.println("*************************************");
        displayDeck();

        System.out.println("***************************");
        System.out.println("Shuffling the deck of cards again");
        System.out.println("***************************");
        shuffleDeck();
        
        System.out.println("*************************************");
        System.out.println("Displaying the shuffled deck of cards");
        System.out.println("*************************************");
        displayCardList();
    }
    
    private void generateDeck()
    {
        deck = new HashSet<Card>(Constants.NUM_CARDS);
        
        for(Constants.Face face : Constants.Face.values())
        {
            for(Constants.Suit suit : Constants.Suit.values())
            {
                Card card = new Card();
                card.setFace(face);
                card.setSuit(suit);

                if(suit == Constants.Suit.DIAMONDS || suit == Constants.Suit.HEARTS)
                    card.setColor(Constants.Color.RED);
                else
                    card.setColor(Constants.Color.BLACK);

                if(!deck.contains(card))
                    deck.add(card);
            }
        }
    }
    
    private void displayDeck()
    {
        System.out.println("Deck size:" + deck.size() + " cards");
        System.out.println("Deck includes:");
        
        for(Card card : deck)
        {           
            System.out.println("Card: " + card.getFace() + " of " + 
                               card.getSuit() + " is color " + card.getColor());
        }
    }
    
    private void displayCardList()
    {
        System.out.println("Deck size:" + cardList.size() + " cards");
        System.out.println("Deck includes:");
        
        for(Card card : cardList)
        {           
            System.out.println("Card: " + card.getFace() + " of " + 
                               card.getSuit() + " is color " + card.getColor());
        }
    }

    private void shuffleDeck()
    {
        // make an ArrayList out of the HashSet
        cardList = new ArrayList<Card>(deck);
        // shuffle the deck of cards using the ArrayList
        Collections.shuffle(getCardList());
        // convert it back to a HashSet
        deck = new HashSet<Card>(getCardList());
    }

    /**
     * @return the deck
     */
    public Set<Card> getDeck() 
    {
        return deck;
    }

    /**
     * @param deck the deck to set
     */
    public void setDeck(Set<Card> deck) 
    {
        this.deck = deck;
    }

    /**
     * @return the cardList
     */
    public List<Card> getCardList() 
    {
        return cardList;
    }

    /**
     * @param cardList the cardList to set
     */
    public void setCardList(List<Card> cardList) 
    {
        this.cardList = cardList;
    }
 
}
