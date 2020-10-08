package trivia.thegame;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Component
@Scope(SCOPE_PROTOTYPE)
public class Board {
    private final int MAX_PLAYERS;
    // 12 cases
    private String[] cellsCategory = new String[]
            {"Pop", "Science", "Sports", "Rock", "Pop", "Science",
                    "Sports", "Rock", "Pop", "Science", "Sports", "Rock"};

    private int[] cellNumberOfPlayers;

    public Board() {
        MAX_PLAYERS = 5;
        cellNumberOfPlayers = new int[MAX_PLAYERS];
    }

    public String getCellCategory(int cellNumber) {
        return cellsCategory[cellNumber];
    }


    public int getPlaceOfPlayer(int player) {
        return cellNumberOfPlayers[player];
    }

    public void movePlayerWithRoll(int player, String playerName, int roll) {
        cellNumberOfPlayers[player] = (cellNumberOfPlayers[player] + roll) % 12;
        System.out.println(playerName + "'s new location is " + cellNumberOfPlayers[player]);
    }

}
