package trivia.thegame;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Component
@Scope(SCOPE_PROTOTYPE)
public class Game {

    private static int MAX_PLAYERS = 5;
    private static int NUMBER_OF_COINS_TO_WIN = 6;
    private static int NUMBER_QUESTION = 50;


    private final Board board;

    List<Player> players = new ArrayList();
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

    public boolean isPlayable() {
        return (players.size() >= 2);
    }

    public void add(String playerName) {
        if (players.size() >= MAX_PLAYERS) {
            throw new RuntimeException("The limit MAX of Player is already reached!");
        }
        Player player = new Player(playerName);
        players.add(player);
        board.addPlayer(player);
        if (players.size() == 1) {
            currentPlayer = players.get(0);
        }

    }

    public void roll(int roll) {
        System.out.println(currentPlayer.getName() + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (currentPlayer.isInPenaltyBox()) {
            if (roll % 2 == 0) {
                System.out.println(currentPlayer.getName() + " is not getting out of the penalty box");
                hasTheRightToGetOutOfThePenaltyBoxAndAskedAQuestion = false;
            } else {
                hasTheRightToGetOutOfThePenaltyBoxAndAskedAQuestion = true;

                System.out.println(currentPlayer.getName() + " is getting out of the penalty box");

                board.movePlayerWithRoll(currentPlayer, roll);

                askQuestion();
            }

        } else {
            board.movePlayerWithRoll(currentPlayer, roll);
            askQuestion();
        }

    }


    private void askQuestion() {
        String cat = board.getCategoryForPlayer(currentPlayer);
        if (cat.equals("Pop"))
            System.out.println(popQuestions.removeFirst());
        if (cat.equals("Science"))
            System.out.println(scienceQuestions.removeFirst());
        if (cat.equals("Sports"))
            System.out.println(sportsQuestions.removeFirst());
        if (cat.equals("Rock"))
            System.out.println(rockQuestions.removeFirst());
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
        nextPlayer();
        return winner;
    }

    public boolean wrongAnswer() {
        System.out.println("Question was incorrectly answered");
        currentPlayer.goToPenalTyBox();
        nextPlayer();
        return true;
    }

    public void nextPlayer() {
        currentPlayer = players.get((players.indexOf(currentPlayer) + 1) % players.size());
    }


}