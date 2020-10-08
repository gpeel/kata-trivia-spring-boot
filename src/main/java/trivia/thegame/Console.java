package trivia.thegame;

import org.springframework.stereotype.Component;

@Component
public class Console {

    public void printAddPlayer(String playerName, int numberOfPlayers) {
        System.out.println(playerName + " was added");
        System.out.println("They are player number " + numberOfPlayers);
    }

    public void printCurrentPlayer(Player currentPlayer) {
        System.out.println(currentPlayer.getName() + " is the current player");
    }

    public void printPlayerNewLocation(Player player, int cellNumber) {
        System.out.println(player.getName() + "'s new location is " + cellNumber);
    }

    public void printCategory(String category) {
        System.out.println("The category is " + category);
    }

    public void printNotGettingOutOfPenaltyBox(String playerName) {
        System.out.println(playerName + " is not getting out of the penalty box");
    }

    public void printGettingOutOfPenaltyBox(String playerName) {
        System.out.println(playerName + " is getting out of the penalty box");
    }

    public void printCorrectAnswer() {
        System.out.println("Answer was correct!!!!");
    }
    public void printIncorrectAnswer() {
        System.out.println("Question was incorrectly answered");
    }

}
