package mancala.domain;

public class Player {
    private boolean hasTurnBoolean;
    private boolean hasWon;
    private Player other;

    Player(){
        this.other = new Player(this);
        this.hasTurnBoolean=true;
        this.hasWon=false;
    }

    Player(Player player){
        this.other=player;
        this.hasTurnBoolean=false;
        this.hasWon=false;
    }

    public Player getOtherPlayer(){
        return this.other;
    }

    public boolean hasTurn(){
        return this.hasTurnBoolean;
    }

    void switchTurn(){
        this.hasTurnBoolean=!(this.hasTurn());
        this.other.hasTurnBoolean=!(this.getOtherPlayer().hasTurn());  
    }

    public boolean hasGameEnded(){
        return !this.hasTurnBoolean  && !this.other.hasTurnBoolean;
    }

    void setwinner(int ownPoints, int pointsOtherPlayer){
        if(ownPoints==pointsOtherPlayer){
            this.hasWon=true;
            getOtherPlayer().hasWon=true;
        } else if(ownPoints>pointsOtherPlayer){
            this.hasWon=true;
        }else if(ownPoints<pointsOtherPlayer) {
            getOtherPlayer().hasWon=true;
        }
    }

    public boolean hasWonGame(){
        return hasWon;
    }

    public void endGame(){
        this.hasTurnBoolean=false;
        this.other.hasTurnBoolean=false;
    }
}
