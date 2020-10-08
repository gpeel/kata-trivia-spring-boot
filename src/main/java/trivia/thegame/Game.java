package trivia.thegame;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.LinkedList;

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


    private final Board board;
    Player currentPlayer;

    LinkedList popQuestions = new LinkedList();
    LinkedList scienceQuestions = new LinkedList();
    LinkedList sportsQuestions = new LinkedList();
    LinkedList rockQuestions = new LinkedList();

    boolean hasTheRightToGetOutOfThePenaltyBoxAndAskedAQuestion;

    public Game(Board board) {
        this.board = board;
        System.err.println("NEW instance of GAME!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }

    @PostConstruct
    public void initialize() {
        for (int i = 0; i < NUMBER_QUESTION; i++) {
            popQuestions.addLast("Pop Question " + i);
            scienceQuestions.addLast(("Science Question " + i));
            sportsQuestions.addLast(("Sports Question " + i));
            rockQuestions.addLast(createRockQuestion(i));
        }
    }

    public String createRockQuestion(int index) {
        return "Rock Question " + index;
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
                System.out.println(currentPlayer.getName() + " is not getting out of the penalty box");
                hasTheRightToGetOutOfThePenaltyBoxAndAskedAQuestion = false;
            } else {
                System.out.println(currentPlayer.getName() + " is getting out of the penalty box");
                hasTheRightToGetOutOfThePenaltyBoxAndAskedAQuestion = true;

                board.movePlayerWithRoll(currentPlayer, roll);
                askQuestion();
            }
        } else {
            board.movePlayerWithRoll(currentPlayer, roll);
            askQuestion();
        }
    }


    private void askQuestion() {
        String categoryOfAskedQuestion = board.getCategoryForPlayer(currentPlayer);
        switch (categoryOfAskedQuestion) {
            case "Pop":
                System.out.println(popQuestions.removeFirst());
                break;
            case "Science":
                System.out.println(scienceQuestions.removeFirst());
                break;
            case "Sports":
                System.out.println(sportsQuestions.removeFirst());
                break;
            case "Rock":
                System.out.println(rockQuestions.removeFirst());
                break;
        }
    }


    public boolean wasCorrectlyAnswered() {
        if (currentPlayer.isInPenaltyBox()) {
            if (hasTheRightToGetOutOfThePenaltyBoxAndAskedAQuestion) {
                System.out.println("Answer was correct!!!!");
                currentPlayer.incrementGold();
                currentPlayer.printPlayerBankAccount();
                currentPlayer.getOutOfPenaltyBox();
            }
        } else {
            System.out.println("Answer was correct!!!!");
            currentPlayer.incrementGold();
            currentPlayer.printPlayerBankAccount();
        }
        boolean winner = !currentPlayer.isWinner(NUMBER_OF_COINS_TO_WIN);
        return winner;
    }

    public boolean wrongAnswer() {
        System.out.println("Question was incorrectly answered");
        currentPlayer.goToPenalTyBox();
        return true;
    }

}