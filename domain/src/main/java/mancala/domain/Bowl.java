package mancala.domain;

public class Bowl extends Pit {
    final static int  STARTING_STONES_BOWL =4;
    final static int  AMOUNT_OF_BOWLS_SIDE =6;

    Bowl(){
        this.stones=STARTING_STONES_BOWL; 
        Player player1 = new Player();
        this.player=player1;
        this.next= (Pit) new Bowl(player1,1);
        this.getClosestCala().getNextPit().getClosestCala().next=(Pit) this;
    }

    Bowl(Player player, int pitsLeft){
        this.stones=STARTING_STONES_BOWL; 
        this.player=player;
        if(pitsLeft<(AMOUNT_OF_BOWLS_SIDE+1)*2-1 && (AMOUNT_OF_BOWLS_SIDE-1==pitsLeft || AMOUNT_OF_BOWLS_SIDE*2==pitsLeft)){
            this.next = (Pit) new Cala(player, pitsLeft+1);
        }
        else if(pitsLeft<(AMOUNT_OF_BOWLS_SIDE+1)*2-1){
            this.next = (Pit) new Bowl(player, pitsLeft+1);
        }
    }

    protected Bowl(Player player){
        this.stones=STARTING_STONES_BOWL; 
        this.player=player;
    }

    protected void empty(){
        this.stones=0;
    }

    protected boolean canSteal(){
        return player.hasTurn() && this.stones==1;
    }

    protected int getStolenStones(){
        int result=this.stones;
        this.empty();
        return result;
    }

    protected void steal(){
        Cala cala = (Cala) getClosestCala();
        Bowl opposite = (Bowl) getOpposite();

        int stolenStones = opposite.getStolenStones();
        cala.add(this.stones  + stolenStones);
        
        this.empty();
    }

    @Override
    void checkIfPlayerRowEmpty(){
        if(isEmpty()){
            getNextPit().checkIfPlayerRowEmpty();
        }
    }

    @Override
    int dropOffStones(int stones){
        this.add(1);
        return stones-1;
    }

    @Override
    void endTurn(){
        if(canSteal()) steal();
        player.switchTurn();
        getPitOtherPlayer().getClosestCala().getNextPit().checkIfPlayerRowEmpty();
    }

    public void play(Player currentPlayer){
        if(player.hasTurn() && !(this.isEmpty()) && currentPlayer.hasTurn()){
            int tempStones =this.stones;
            this.empty();
            getNextPit().moveStonesThrough(tempStones);
        } 
    }
}
