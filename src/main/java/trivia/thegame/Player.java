package trivia.thegame;

public class Player {

    private String name;
    private int purse;
    private boolean inPenaltyBox;

    public Player(String name) {
        this.name = name;
        this.purse = 0;
        this.inPenaltyBox = false;
    }


    public void incrementGold() {
        purse++;
    }

    public boolean isWinner(int coinsToHaveToBeWinner) {
        return this.purse == coinsToHaveToBeWinner;
    }

    public String getName() {
        return name;
    }

    public boolean isInPenaltyBox() {
        return inPenaltyBox;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPurse() {
        return purse;
    }

    public void goToPenalTyBox() {
        this.inPenaltyBox = true;
    }

    public void getOutOfPenalTyBox() {
        this.inPenaltyBox = false;
    }
}
