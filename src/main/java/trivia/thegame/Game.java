package trivia.thegame;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

/**
 * The Game class responsability is defining the RULES of the Game
 */
@Component
@Scope(SCOPE_PROTOTYPE)
public class Game {

    private static int MAX_PLAYERS = 5;
    private static int NUMBER_OF_COINS_TO_WIN = 6;
    private static int NUMBER_QUESTION = 50;

    // Spring @Component
    private final Board board;
    private final Console console;

    // local
    Player currentPlayer;

    boolean hasTheRightToGetOutOfThePenaltyBoxAndAskedAQuestion;

    public Game(Board board, Console console) {
        this.board = board;
        this.console = console;
        System.err.println("NEW instance of GAME!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }

    @PostConstruct
    public void initialize() {
        board.initializeTheBoard(NUMBER_QUESTION);
    }


    public void add(String playerName) {
        // this is a RULE, so it's here in the Game class
        if (board.getNumberOfPlayers() >= MAX_PLAYERS) {
            throw new RuntimeException("The limit MAX of Player of "
                    + MAX_PLAYERS + " is already reached!");
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

    public boolean wasCorrectlyAnswered() {
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
        return !currentPlayer.isWinner(NUMBER_OF_COINS_TO_WIN);
    }

    public boolean wrongAnswer() {
        console.printIncorrectAnswer();
        currentPlayer.goToPenalTyBox();
        return true;
    }

}