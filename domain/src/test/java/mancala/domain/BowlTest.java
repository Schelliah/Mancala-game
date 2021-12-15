package mancala.domain;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.beans.Transient;


public class BowlTest {
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
    public void aNormalBowlStartsWith4Stones() {
        init();
        Bowl bowl = new Bowl();
        assertEquals(4, bowl.getAmountOfStones());
    }

    @Test 
    public void aBowlHasANeighbor() {
        Bowl bowl = new Bowl();
        assertNotNull(bowl.getNextPit());
    }

    @Test
    public void secondMancalaConnectstoFirstBowl(){
        init();
        assertEquals(pits[0],pits[13].getNextPit());
    }
    

    @Test
    public void emptyBowl() {
        init();
        Bowl bowl = new Bowl();
        bowl.empty();
        assertEquals(0, bowl.getAmountOfStones());
    }

    @Test
    public void player1SideGetsOppositeCorrect() {
        init();
        assertEquals(pits[12], pits[0].getOpposite());
    }

    @Test
    public void player2SideGetsOppositeCorrect() {
        init();
        assertEquals(pits[5], pits[7].getOpposite());
    }




    @Test
    public void endGameNotWhenPlayable() {
        init();
        getBowl(pits,0).play(player1);
        assertFalse(player1.hasGameEnded());
        assertFalse(player2.hasGameEnded());
    }

    @Test
    public void endGameCorrectlyInCala() {
        init();
        for (int i = 0;i<5;i++){
            pits[i].setAmountOfStones(0);
        }
        pits[5].setAmountOfStones(1); //player 1 bowls are all empty except the last one
        getBowl(pits, 5).play(player1);
        assertTrue(player1.hasGameEnded());
        assertTrue(player2.hasGameEnded());
    }


    @Test
    public void endGameCollectingCorrectlyKalahaEnd() {
        init();
        for (int i = 0;i<5;i++){
            pits[i].setAmountOfStones(0);
        }
        pits[5].setAmountOfStones(1); //player 1 bowls are all empty except the last one
        getBowl(pits, 5).play(player1);
        assertEquals(24,pits[13].getAmountOfStones()); //cala player 2 has 24 stones now, if collecting stones worked.
    }

    @Test
    public void endGameSetWinnerCorrectlyKalahaEnd() {
        init();
        for (int i = 0;i<5;i++){
            pits[i].setAmountOfStones(0);
        }
        pits[5].setAmountOfStones(1); //player 1 bowls are all empty except the last one
        getBowl(pits, 5).play(player1);
        assertTrue(player2.hasWonGame());
        assertFalse(player1.hasWonGame());
            //cala player 2 has 24 stones now, if collecting stones worked. and player 1, 1 stone
    }

    @Test
    public void endGameCorrectlyNotInCala() {
        init();
        for (int i = 7;i<13;i++){
            pits[i].setAmountOfStones(0);
        }
        getBowl(pits,0).play(player1);
        assertTrue(player1.hasGameEnded());
        assertTrue(player2.hasGameEnded());
    }

    @Test
    public void PlayOneTurnTakingFirstBowFromPlayer1() {
        init();
        getBowl(pits, 0).play(player1);

        assertEquals(0, pits[0].getAmountOfStones());
        assertEquals(5, pits[1].getAmountOfStones());
        assertEquals(5, pits[2].getAmountOfStones());
        assertEquals(5, pits[3].getAmountOfStones());
        assertEquals(5, pits[4].getAmountOfStones());
        assertEquals(4, pits[5].getAmountOfStones());
        assertEquals(0, pits[6].getAmountOfStones());
    }

    @Test
    public void Player1TriesToPlayAgainAfterTurn() {
        init();
        getBowl(pits, 0).play(player1);
        getBowl(pits, 3).play(player1);
        getBowl(pits, 4).play(player1);
        getBowl(pits, 5).play(player1);
        getBowl(pits, 10).play(player1);

        assertEquals(0, pits[0].getAmountOfStones());
        assertEquals(5, pits[1].getAmountOfStones());
        assertEquals(5, pits[2].getAmountOfStones());
        assertEquals(5, pits[3].getAmountOfStones());
        assertEquals(5, pits[4].getAmountOfStones());
        assertEquals(4, pits[5].getAmountOfStones());
        assertEquals(0, pits[6].getAmountOfStones());
    }


    @Test
    public void BothplayersTakeTurnFirstBowl() {
        init();
        getBowl(pits, 0).play(player1);
        getBowl(pits, 7).play(player2);

        assertEquals(0, pits[0].getAmountOfStones());
        assertEquals(5, pits[1].getAmountOfStones());
        assertEquals(5, pits[2].getAmountOfStones());
        assertEquals(5, pits[3].getAmountOfStones());
        assertEquals(5, pits[4].getAmountOfStones());
        assertEquals(4, pits[5].getAmountOfStones());
        assertEquals(0, pits[6].getAmountOfStones());
        assertEquals(0, pits[7].getAmountOfStones());
        assertEquals(5, pits[8].getAmountOfStones());
        assertEquals(5, pits[9].getAmountOfStones());
        assertEquals(5, pits[10].getAmountOfStones());
        assertEquals(5, pits[11].getAmountOfStones());
        assertEquals(4, pits[12].getAmountOfStones());
        assertEquals(0, pits[13].getAmountOfStones());
    }

    @Test
    public void Player1StealsonSecondTurn() {
        init();
        getBowl(pits, 4).play(player1);
        getBowl(pits, 7).play(player2);
        getBowl(pits, 0).play(player1);

        assertEquals(0, pits[0].getAmountOfStones());
        assertEquals(5, pits[1].getAmountOfStones());
        assertEquals(5, pits[2].getAmountOfStones());
        assertEquals(5, pits[3].getAmountOfStones());
        assertEquals(0, pits[4].getAmountOfStones());
        assertEquals(5, pits[5].getAmountOfStones());
        assertEquals(8, pits[6].getAmountOfStones());
        assertEquals(0, pits[7].getAmountOfStones());
        assertEquals(0, pits[8].getAmountOfStones());
        assertEquals(5, pits[9].getAmountOfStones());
        assertEquals(5, pits[10].getAmountOfStones());
        assertEquals(5, pits[11].getAmountOfStones());
        assertEquals(5, pits[12].getAmountOfStones());
        assertEquals(0, pits[13].getAmountOfStones());
    }
    @Test
    public void Player2StealsonFirstTurn() {
        init();
        getBowl(pits, 0).play(player1);

        pits[11].setAmountOfStones(0);
        getBowl(pits, 7).play(player2);
        assertEquals(0, pits[0].getAmountOfStones());
        assertEquals(0, pits[1].getAmountOfStones());
        assertEquals(5, pits[2].getAmountOfStones());
        assertEquals(5, pits[3].getAmountOfStones());
        assertEquals(5, pits[4].getAmountOfStones());
        assertEquals(4, pits[5].getAmountOfStones());
        assertEquals(0, pits[6].getAmountOfStones());
        assertEquals(0, pits[7].getAmountOfStones());
        assertEquals(5, pits[8].getAmountOfStones());
        assertEquals(5, pits[9].getAmountOfStones());
        assertEquals(5, pits[10].getAmountOfStones());
        assertEquals(0, pits[11].getAmountOfStones());
        assertEquals(4, pits[12].getAmountOfStones());
        assertEquals(6, pits[13].getAmountOfStones());
    }

    





    
    
}
