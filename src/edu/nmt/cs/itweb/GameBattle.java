
package edu.nmt.cs.itweb;

/**
 *
 * @author ddumas
 */
public class GameBattle {
    private boolean pvp;
    private GameCharacter player1;
    private GameCharacter player2;
    private int turn;
    //this tells us whose turn it is, player1 is true, player2 false
    private Integer whoseTurn;

    private String battleMessage;

    public GameBattle(GameCharacter p1, GameCharacter p2, boolean isPVP)
    {
        this.player1 = p1;
        this.player2 = p2;
        this.pvp = isPVP;
        this.turn = 0;
        this.whoseTurn = player1.getId();
        this.battleMessage = "none";
    }

    public void setPlayer1(GameCharacter p1)
    {
        this.player1 = p1;
    }
    public GameCharacter getPlayer1()
    {
        return this.player1;
    }

    public void setPlayer2(GameCharacter p2)
    {
        this.player2 = p2;
    }
    public GameCharacter getPlayer2()
    {
        return this.player2;
    }

    public void incrementTurn()
    {
        this.turn+=1;
        if(this.whoseTurn == player1.getId())
        {
            this.whoseTurn = player2.getId();
        }
        else
        {
            this.whoseTurn = player1.getId();
        }
    }
    public int getTurn()
    {
        return this.turn;
    }
    public void setTurn(int newTurn)
    {
        this.turn = newTurn;
    }

    public boolean isPVP()
    {
        return this.pvp;
    }

    public Integer whoseTurn()
    {
        return this.whoseTurn;
    }

    public Integer whoseNotTurn()
    {
        if (player1.getId() == whoseTurn)
        {
            return player2.getId();
        }
        else
        {
            return player1.getId();
        }
    }

    public void setWhoseTurn(int p1sTurn)
    {
        this.whoseTurn = p1sTurn;
    }

    public String getBattleMessage()
    {
        return this.battleMessage;
    }

    public void setBattleMessage(String newMessage)
    {
        this.battleMessage = newMessage;
    }
}
