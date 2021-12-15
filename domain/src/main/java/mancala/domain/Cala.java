package mancala.domain;

public class Cala extends Pit {

    Cala(){
        this.stones = 0;
    }

    Cala(Player player){
        this.stones= 0;
        this.player=player;
    }

    Cala(Player player, int pitsLeft){
        this.stones= 0;
        this.player=player;
        if(pitsLeft<(Bowl.AMOUNT_OF_BOWLS_SIDE+1)*2-1){
            Player otherPlayer=player.getOtherPlayer();
            this.next = (Pit) new Bowl(otherPlayer, pitsLeft+1);
        }
    }

    @Override
    int dropOffStones(int stones){
        if(player.hasTurn()){
            this.add(1);
            return stones-1;
        }
        return stones;
    }

    @Override
    void collectAllUntilCala(int stones){
        add(stones);
        return;
    }

    void checkIfPlayerRowEmpty(){
        getNextPit().collectAllUntilCala(0);
        player.endGame();
        setScores();
    }

    @Override
    void endTurn(){
        getNextPit().getClosestCala().getNextPit().checkIfPlayerRowEmpty();
    }

    @Override
    Pit getClosestCala(){
        return this;
    }
    
    @Override
    Pit getOpposite(){
        return this;
    }
}
