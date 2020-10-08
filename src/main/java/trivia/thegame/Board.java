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

    private Map<Player, Integer> cellNumberForPlayerMap = new HashMap<>();

    public Board() {
        System.err.println("NEW instance of BOARD !!!!!!!!!!!!!!!!!!!!!!!!!!");
    }

    public void addPlayer(Player player) {
        cellNumberForPlayerMap.put(player, new Integer(0));
        System.out.println(player.getName() + " was added");
        System.out.println("They are player number " + cellNumberForPlayerMap.size());
    }

    public String getCellCategory(int cellNumber) {
        return cellsCategory[cellNumber];
    }

    public String getCategoryForPlayer(Player player) {
        return cellsCategory[cellNumberForPlayerMap.get(player)];
    }

    public int getCellNumberForPlayer(Player player) {
        return cellNumberForPlayerMap.get(player);
    }

    public void movePlayerWithRoll(Player player, int roll) {
        int newCellForPlayer = (cellNumberForPlayerMap.get(player) + roll) % 12;
        cellNumberForPlayerMap.put(player, new Integer(newCellForPlayer));
        System.out.println(player.getName() + "'s new location is " + cellNumberForPlayerMap.get(player));
        System.out.println("The category is " + getCategoryForPlayer(player));
    }

}
