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

}
