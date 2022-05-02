//COP 3330
//Assignment 7
//Bruno Pittini

package core;

import java.util.ArrayList;

public class Team 
{
    // member variables
    private ArrayList<Player> team;
    private int teamScore;
    private int teamTricks;
    private String teamName;
    
    public Team()
    {
        team = new ArrayList();
    }

    public void outputTeam()
    {
        System.out.println(teamName + " includes players: ");
        
        for(Player player : team)
        {
            System.out.println(player.getName());
        }
    }
    
    public void outputHands()
    {
//        System.out.println("*************************");
//        System.out.println("        " + teamName.toUpperCase());
//        System.out.println("*************************");      

        for(Player player : team)
        {
            player.sortBySuit();
            
            if(player instanceof HumanPlayer)
                player.displayHand();
        }
    }
    
    /**
     * @return the teamScore
     */
    public int getTeamScore() 
    {
        return teamScore;
    }

    /**
     * @param teamScore the teamScore to set
     */
    public void setTeamScore(int teamScore) 
    {
        this.teamScore = teamScore;
    }

    /**
     * @return the teamTricks
     */
    public int getTeamTricks() 
    {
        return teamTricks;
    }

    /**
     * @param teamTricks the teamTricks to set
     */
    public void setTeamTricks(int teamTricks) 
    {
        this.teamTricks = teamTricks;
    }
    /**
     * @return the teamOne
     */
    public ArrayList<Player> getTeam() 
    {
        return team;
    }

    /**
     * @param teamOne the teamOne to set
     */
    public void setTeam(ArrayList<Player> teamOne) 
    {
        this.team = teamOne;
    }

    /**
     * @return the teamName
     */
    public String getTeamName() 
    {
        return teamName;
    }

    /**
     * @param teamName the teamName to set
     */
    public void setTeamName(String teamName) 
    {
        this.teamName = teamName;
    }
}
