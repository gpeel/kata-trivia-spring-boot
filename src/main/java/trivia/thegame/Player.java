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

    public void printPlayerBankAccount() {
        System.out.println(name + " now has " + purse + " Gold Coins.");
    }

    public String getName() {
        return name;
    }

    public boolean isInPenaltyBox() {
        return inPenaltyBox;
    }


    public void goToPenalTyBox() {
        System.out.println(name + " was sent to the penalty box");
        this.inPenaltyBox = true;
    }

    public void getOutOfPenaltyBox() {
        this.inPenaltyBox = false;
    }
}
