package mancala.domain;

import java.util.ArrayList;

public abstract class Pit {
    protected int stones;
    protected Pit next;
    protected Player player;

    Pit(){
        this.stones= 0;
    }

    void add(int stones){
        this.stones= this.stones + stones;
    }

    void moveStonesToNextPit(int stones){
        this.next.add(stones);
    }

    void setNextPit(Pit pit){
        this.next=pit;
    }

    Pit getNextPit(){
        return this.next;
    }

    int getAmountOfStones(){
        return this.stones;
    }

    boolean isEmpty(){
        return this.stones==0;
    }

    protected void setAmountOfStones(int stones){//only used for tests
        this.stones=stones;
    } 

    Pit getPitOtherPlayer(){
        if(!player.hasTurn()){
            return this;
        }
        return getNextPit().getPitOtherPlayer();
    }

    Pit getClosestCala(){
        return getNextPit().getClosestCala();
    }

    Pit getOpposite(){
        return getNextPit().getOpposite().getNextPit();
    }

    abstract void checkIfPlayerRowEmpty();

    void collectAllUntilCala(int stones){
        stones=stones+getAmountOfStones();
        this.stones=0;
        getNextPit().collectAllUntilCala(stones);
    }

    public Player getPlayer(){
        return player;
    }

    abstract int dropOffStones(int stones);
    abstract void endTurn();

    void moveStonesThrough(int stones){
        stones=dropOffStones(stones);
        if(stones==0){
            endTurn();
            return;
        }
        this.getNextPit().moveStonesThrough(stones);
    }

    void setScores(){
        Pit temp = getClosestCala();
        int pointsOtherPlayer= temp.getAmountOfStones();
        temp=temp.getNextPit().getClosestCala();
        temp.getAmountOfStones();
        temp.getPlayer().setwinner(temp.getAmountOfStones(),pointsOtherPlayer);
    }

    public Pit[] getArrayPits(){ //useful to easily choose  pits/bowls to play for tests, also for UI
        Pit[] result = new Pit[Bowl.AMOUNT_OF_BOWLS_SIDE*2+2];
        int i =0;
        result[i]=this;
        System.out.println(i);
        Pit temp = (Pit) this.getNextPit();
        while(!this.equals(temp)){
            i++;
            System.out.println(i);
            result[i]=temp;
            temp=temp.getNextPit();
        }
        return result;
    }
}
