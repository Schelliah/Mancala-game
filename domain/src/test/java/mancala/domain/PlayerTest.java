package mancala.domain;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    @Test 
    public void player1Starts() {
        Player player1 = new Player();
        Player player2=player1.getOtherPlayer();
        assertTrue(player1.hasTurn());
        assertFalse(player2.hasTurn());
    }

    @Test 
    public void player1SwitchesToPlayer2() {
        Player player1 = new Player();
        Player player2=player1.getOtherPlayer();
        player1.switchTurn();
        assertFalse(player1.hasTurn());
        assertTrue( player2.hasTurn());
    }

    @Test 
    public void switchPlayer2Times() {
        Player player1 = new Player();
        Player player2=player1.getOtherPlayer();
        player1.switchTurn();
        player2.switchTurn();
        assertTrue( player1.hasTurn());
        assertFalse( player2.hasTurn());
    }

    @Test 
    public void switchPlayer3Times() {
        Player player1 = new Player();
        Player player2=player1.getOtherPlayer();
        player1.switchTurn();
        player1.switchTurn();
        player1.switchTurn();
        assertFalse( player1.hasTurn());
        assertTrue( player2.hasTurn());
    }  
    
}
