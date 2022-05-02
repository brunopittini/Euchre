//COP 3330
//Assignment 7
//Bruno Pittini

package userinterface;

import constants.Constants;
import constants.Constants.Face;
import constants.Constants.Suit;
import core.Card;
import core.Game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GameUi 
{
    private Game game;
    
    private JFrame frame;
    private JPanel aiOnePanel;
    private JPanel tablePanel;
    private JPanel aiTwoPanel;
    private JPanel hpPanel;
    private JPanel aiThreePanel;
    private JPanel northPanel;
    private JPanel scorePanel;
    private JPanel trumpPanel;
    private JLabel teamOneScoreLbl;
    private JLabel teamOneScore;
    private JLabel teamTwoScoreLbl;
    private JLabel teamTwoScore;
    private JLabel trumpCard;
    
    // the menu
    private JMenuBar menuBar;
    private JMenu gameMenu;
    private JMenu helpMenu;
    private JMenuItem newGameMenuItem;
    private JMenuItem exitMenuItem;
    private JMenuItem aboutMenuItem;
    private JMenuItem rulesMenuItem;

    public GameUi(Game game)
    {
        this.game = game;
        
        initComponents();
        
        game.setGameUi(this);
        game.play();
    }
    
    private void initComponents()
    {
        initMenuBar();
        layoutTable();  
    }
    
    private void initMenuBar()
    {
        menuBar = new JMenuBar();
        
        gameMenu = new JMenu("Game");
        newGameMenuItem = new JMenuItem("New Game");
        newGameMenuItem.addActionListener(new NewGameListener());
        exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(new ExitListener());
        
        helpMenu = new JMenu("Help");
        aboutMenuItem = new JMenuItem("About");
        aboutMenuItem.addActionListener(new AboutListener());   
        rulesMenuItem = new JMenuItem("Game Rules");
        rulesMenuItem.addActionListener(new RulesListener());  
        
        gameMenu.add(newGameMenuItem);
        gameMenu.add(exitMenuItem);
        
        helpMenu.add(aboutMenuItem);
        helpMenu.add(rulesMenuItem);
        
        menuBar.add(gameMenu);
        menuBar.add(helpMenu);       
    }
    
    private void layoutTable()
    {
        frame = new JFrame("Euchre");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 700);
        
        aiOnePanel = new AiPlayerUi(game.getTable().get(Constants.POSITION_2), Constants.POSITION_2, this);
        aiTwoPanel = new AiPlayerUi(game.getTable().get(Constants.POSITION_3), Constants.POSITION_3, this);        
        aiThreePanel = new AiPlayerUi(game.getTable().get(Constants.POSITION_4), Constants.POSITION_4, this);
        hpPanel = new HumanPlayerUi(game.getTable().get(Constants.POSITION_1), this);
        
        game.getTable().get(Constants.POSITION_1).setUi(hpPanel);
        game.getTable().get(Constants.POSITION_2).setUi(aiOnePanel);
        game.getTable().get(Constants.POSITION_3).setUi(aiTwoPanel);
        game.getTable().get(Constants.POSITION_4).setUi(aiThreePanel);
        
        initNorthPanel();
        initTablePanel();
        
        frame.add(aiOnePanel, BorderLayout.WEST);
        frame.add(northPanel, BorderLayout.NORTH);
        frame.add(aiThreePanel, BorderLayout.EAST);
        frame.add(hpPanel, BorderLayout.SOUTH);
        frame.add(tablePanel, BorderLayout.CENTER);
        
        frame.setJMenuBar(menuBar);
        frame.setVisible(true);
    }
    
    private void initNorthPanel()
    {
        northPanel = new JPanel();
        northPanel.setMinimumSize(new Dimension(680, 170));
        northPanel.setPreferredSize(new Dimension(680, 170));  
        
        initScorePanel();
        initTrumpPanel();
       
        aiTwoPanel.setMinimumSize(new Dimension(350, 160));
        aiTwoPanel.setPreferredSize(new Dimension(350, 160));
        
        northPanel.add(scorePanel);
        northPanel.add(aiTwoPanel);
        northPanel.add(trumpPanel);
    }
    
    private void initTablePanel()
    {
        tablePanel = new JPanel();
        tablePanel.setBorder(BorderFactory.createTitledBorder("EUCHRE"));
        tablePanel.setMaximumSize(new Dimension(300,200));
        tablePanel.setMinimumSize(new Dimension(300,200));
        tablePanel.setPreferredSize(new Dimension(300,200));        
    }
    
    
    private void initScorePanel()
    {
        scorePanel = new JPanel();
        scorePanel.setBorder(BorderFactory.createTitledBorder("Scores"));
        scorePanel.setMinimumSize(new Dimension(130, 160));
        scorePanel.setPreferredSize(new Dimension(130, 160));
        scorePanel.setLayout(new GridLayout(2,2));
        teamOneScoreLbl = new JLabel("Team One");
        teamOneScore = new JLabel(" " + game.getTeams().get(Constants.ONE).getTeamScore());
        teamTwoScoreLbl = new JLabel("Team Two");
        teamTwoScore = new JLabel(" " + game.getTeams().get(Constants.TWO).getTeamScore());
        scorePanel.add(teamOneScoreLbl);
        scorePanel.add(teamOneScore);
        scorePanel.add(teamTwoScoreLbl);
        scorePanel.add(teamTwoScore);
    }

    private void initTrumpPanel()
    {
        trumpPanel = new JPanel();
        trumpPanel.setBorder(BorderFactory.createTitledBorder("Trump"));
        trumpPanel.setMinimumSize(new Dimension(130, 160));
        trumpPanel.setPreferredSize(new Dimension(130, 160));
        trumpCard = new JLabel(); 
        CardUi cardUi = new CardUi(trumpCard, game.getTrump().getFace(), game.getTrump().getSuit());
        trumpCard = cardUi.getLabel();
        trumpCard.setMinimumSize(new Dimension(100,120));
        trumpCard.setPreferredSize(new Dimension(100,120));
        trumpCard.setMaximumSize(new Dimension(100,120));
        trumpCard.putClientProperty("face", game.getTrump().getFace());
        trumpCard.putClientProperty("suit", game.getTrump().getSuit());
        trumpPanel.add(trumpCard);
    }
    
    public JFrame getFrame() {
        return frame;
    }
    
    private class NewGameListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent ae) 
        {
        }

    }
    
    private class ExitListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ae) 
        {
            int response = JOptionPane.showConfirmDialog(frame, "Confirm to exit Euchre?", 
                    "Exit?", JOptionPane.YES_NO_OPTION);
            
            if (response == JOptionPane.YES_OPTION)
                System.exit(0);	        
        }
    
    }
    
    private class AboutListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ae) 
        {
            String message = "Euchre version 1.0\nBruno Pittini\nSummer 2018";
            JOptionPane.showMessageDialog(frame, message);
        }
    
    }
    
    private class RulesListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ae) 
        {
        }
    }              
}
