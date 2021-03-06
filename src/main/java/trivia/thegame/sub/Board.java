package trivia.thegame.sub;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

/**
 * The Board class responsability is the layout and position of the board
 * ie what a cell category is, and where the Player(s) are.
 * No Game Rules here.
 */
@Component
@Scope(SCOPE_PROTOTYPE)
public class Board {

    private final Console console;
    private final QuestionDeck questionDeck;

    // 12 cells
    private String[] cellsCategory;

    private Map<Player, Integer> cellNumberForPlayerMap = new HashMap<>();
    private List<Player> players = new ArrayList<>();
    private Player currentPlayer;

    public Board(Console console, QuestionDeck questionDeck) {
        this.console = console;
        this.questionDeck = questionDeck;
        System.err.println("NEW instance of BOARD !!!!!!!!!!!!!!!!!!!!!!!!!!");
    }

    public void initializeTheBoard(int numberOfQuestions, String[] cellsCategory ) {
        this.cellsCategory = cellsCategory;
        questionDeck.initializeTheDeck(numberOfQuestions);
    }

    public void askQuestion() {
        String category = getCategoryForPlayer(currentPlayer);
        questionDeck.askQuestion(category);
    }

    public void addPlayerForName(String playerName) {
        Player player = new Player(playerName);
        players.add(player);
        cellNumberForPlayerMap.put(player, 0);
        console.printAddPlayer(playerName, players.size());
    }

    public int getNumberOfPlayers() {
        return players.size();
    }

    public Player nextCurrentPlayer() {
        if (currentPlayer == null) {
            currentPlayer = players.get(0);
        } else {
            currentPlayer = players.get((players.indexOf(currentPlayer) + 1) % players.size());
        }
        console.printCurrentPlayer(currentPlayer);
        return currentPlayer;
    }

    public String getCellCategory(int cellNumber) {
        return cellsCategory[cellNumber];
    }

    private String getCategoryForPlayer(Player player) {
        return cellsCategory[cellNumberForPlayerMap.get(currentPlayer)];
    }

    public int getCellNumberForPlayer(Player player) {
        return cellNumberForPlayerMap.get(player);
    }

    public void movePlayer(Player player, int roll) {
        int newCellForPlayer = (cellNumberForPlayerMap.get(player) + roll) % 12;
        cellNumberForPlayerMap.put(player, newCellForPlayer);
        console.printPlayerNewLocation(player, cellNumberForPlayerMap.get(player));
        console.printCategory(getCategoryForPlayer(player));
    }

}
