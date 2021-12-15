package mancala.domain;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class CalaTest {
    Bowl bowl;
    Player player1;
    Player player2;
    Pit[] pits;

    void init(){
        bowl = new Bowl();
        player1=bowl.getPlayer();
        player2=player1.getOtherPlayer();
        pits = bowl.getArrayPits();
    }

    Bowl getBowl(Pit[] pits, int i){
        return (Bowl) pits[i];
    }

    @Test 
    public void aNormalCalaisEmptyStart() {
        Cala cala = new Cala();
        assertEquals(0, cala.getAmountOfStones());
        assertTrue(cala.isEmpty());
    }

    @Test 
    public void CalaLocationsCorrection() {
        init();

        assertTrue(pits[6] instanceof Cala);
        assertTrue(pits[13] instanceof Cala);
    }

    @Test 
    public void endingInOwnCalaGetsAnotherTurn() {
        init();
        getBowl(pits, 2).play(player1);
        getBowl(pits, 3).play(player1);

        assertEquals(2, pits[6].getAmountOfStones());
    }

    @Test 
    public void skipEnemyCala() {
        init();
        pits[5].setAmountOfStones(20);

        getBowl(pits, 5).play(player1);

        assertEquals(0, pits[13].getAmountOfStones());
    }

    @Test 
    public void dontEndInEnemyCala() {
        init();
        pits[5].setAmountOfStones(8);

        getBowl(pits, 5).play(player1);

        assertEquals(0, pits[13].getAmountOfStones());
        assertEquals(5, pits[0].getAmountOfStones());
        assertEquals(4, pits[1].getAmountOfStones());
    }
}
