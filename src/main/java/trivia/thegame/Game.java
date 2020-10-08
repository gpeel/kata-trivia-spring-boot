package trivia.thegame;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

/**
 * The Game class responsability is defining the RULES of the Game.
 * It contains basically only the public API and those Rules.
 * => addPlayer(), roll(), rightAnswer(), wrongAnswer()
 * and initialization.
 * Subclasses do not access properties.
 * Properties trickle down from application.properties to {@link GameProperties}
 * and to this {@link Game} and then  to {@link Board} and {@link QuestionDeck}
 */
@Component
@Scope(SCOPE_PROTOTYPE)
public class Game {

    // those variables are in application.properties
    //    private static int MAX_PLAYERS = 5;
    //    private static int NUMBER_OF_COINS_TO_WIN = 6;
    //    private static int NUMBER_OF_QUESTIONS = 50;
    //    private static String[] CELLS_CATEGORY = new String[]
    //            {"Pop", "Science", "Sports", "Rock", "Pop", "Science",
    //                    "Sports", "Rock", "Pop", "Science", "Sports", "Rock"};

    // Spring @Component
    private final Board board;
    private final Console console;
    private final GameProperties gameProperties;

    // local
    Player currentPlayer;

    boolean hasTheRightToGetOutOfThePenaltyBoxAndAskedAQuestion;

    public Game(Board board, Console console, GameProperties gameProperties) {
        this.board = board;
        this.console = console;
        this.gameProperties = gameProperties;
        System.err.println("NEW instance of GAME!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }

    @PostConstruct
    public void initialize() {
        board.initializeTheBoard(gameProperties.getNumberOfQuestions(), gameProperties.getCellsCategory());
    }

    public void addPlayer(String playerName) {
        // this is a RULE, so it's here in the Game class
        if (board.getNumberOfPlayers() >= gameProperties.getMaxPlayers()) {
            throw new RuntimeException("The limit MAX of Players of "
                    + gameProperties.getMaxPlayers() + " is already reached!");
        }
        board.addPlayerForName(playerName);
    }

    public void roll(int roll) {
        currentPlayer = board.nextCurrentPlayer();
        System.out.println("They have rolled a " + roll);

        if (currentPlayer.isInPenaltyBox()) {
            if (roll % 2 == 0) {
                console.printNotGettingOutOfPenaltyBox(currentPlayer.getName());
                hasTheRightToGetOutOfThePenaltyBoxAndAskedAQuestion = false;
            } else {
                console.printGettingOutOfPenaltyBox(currentPlayer.getName());
                hasTheRightToGetOutOfThePenaltyBoxAndAskedAQuestion = true;
                board.movePlayer(currentPlayer, roll);
                board.askQuestion();
            }
        } else {
            board.movePlayer(currentPlayer, roll);
            board.askQuestion();
        }
    }

    public boolean rightAnswer() {
        if (currentPlayer.isInPenaltyBox()) {
            if (hasTheRightToGetOutOfThePenaltyBoxAndAskedAQuestion) {
                console.printCorrectAnswer();
                currentPlayer.incrementGold();
                currentPlayer.printPlayerBankAccount();
                currentPlayer.getOutOfPenaltyBox();
            }
        } else {
            console.printCorrectAnswer();
            currentPlayer.incrementGold();
            currentPlayer.printPlayerBankAccount();
        }
        return !currentPlayer.isWinner(gameProperties.getNumberOfCoinsToWin());
    }

    public boolean wrongAnswer() {
        console.printIncorrectAnswer();
        currentPlayer.goToPenalTyBox();
        return true;
    }

}