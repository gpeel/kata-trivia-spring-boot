package trivia.thegame;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "trivia")
public class GameProperties {

    private int maxPlayers;
    private int numberOfCoinsToWin;
    private int numberOfQuestions;
    private String[] cellsCategory;

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public int getNumberOfCoinsToWin() {
        return numberOfCoinsToWin;
    }

    public void setNumberOfCoinsToWin(int numberOfCoinsToWin) {
        this.numberOfCoinsToWin = numberOfCoinsToWin;
    }

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }

    public String[] getCellsCategory() {
        return cellsCategory;
    }

    public void setCellsCategory(String[] cellsCategory) {
        this.cellsCategory = cellsCategory;
    }
}
