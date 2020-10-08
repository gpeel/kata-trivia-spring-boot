package trivia.thegame;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Component
@Scope(SCOPE_PROTOTYPE)
public class Board {
    // 12 cases
    private String[] cellsCategory = new String[]
            {"Pop", "Science", "Sports", "Rock", "Pop", "Science",
                    "Sports", "Rock", "Pop", "Science", "Sports", "Rock"};

    private Map<Player, Integer> cellNumberOfPlayers = new HashMap<>();

    public Board() {
        System.err.println("NEW instance of BOARD !!!!!!!!!!!!!!!!!!!!!!!!!!");
    }

    public void addPlayer(Player player) {
        cellNumberOfPlayers.put(player, new Integer(0));
    }

    public String getCellCategory(int cellNumber) {
        return cellsCategory[cellNumber];
    }

    public String getCategoryForPlayer(Player player) {
        return cellsCategory[cellNumberOfPlayers.get(player)];
    }

    public int getCellNumberForPlayer(Player player) {
        return cellNumberOfPlayers.get(player);
    }

    public void movePlayerWithRoll(Player player, int roll) {
        int newCellForPlayer = (cellNumberOfPlayers.get(player) + roll) % 12;
        cellNumberOfPlayers.put(player, new Integer(newCellForPlayer));
        System.out.println(player.getName() + "'s new location is " + cellNumberOfPlayers.get(player));
    }

}
