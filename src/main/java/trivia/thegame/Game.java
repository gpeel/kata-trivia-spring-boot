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
//    int currentPlayerIndex = 0;

    LinkedList popQuestions = new LinkedList();
    LinkedList scienceQuestions = new LinkedList();
    LinkedList sportsQuestions = new LinkedList();
    LinkedList rockQuestions = new LinkedList();

    boolean isGettingOutOfPenaltyBox;

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
        players.add(new Player(playerName));
        if (players.size() == 1) {
            currentPlayer = players.get(0);
        }

        System.out.println(playerName + " was added");
        System.out.println("They are player number " + players.size());
    }

    public void roll(int roll) {
        System.out.println(currentPlayer.getName() + " is the current player");
        System.out.println("They have rolled a " + roll);
        int indexOfCurrentPlayer = players.indexOf(currentPlayer);

        if (currentPlayer.isInPenaltyBox()) {
            if (roll % 2 == 0) {
                System.out.println(currentPlayer.getName() + " is not getting out of the penalty box");
                isGettingOutOfPenaltyBox = false;
            } else {
                isGettingOutOfPenaltyBox = true;

                System.out.println(currentPlayer.getName() + " is getting out of the penalty box");
                board.movePlayerWithRoll(indexOfCurrentPlayer, (String) currentPlayer.getName(), roll);

                int cellNumber = board.getPlaceOfPlayer(indexOfCurrentPlayer);
                String cat = board.getCellCategory(cellNumber);

                System.out.println("The category is " + cat);

                askQuestion();
            }

        } else {
            board.movePlayerWithRoll(indexOfCurrentPlayer, (String) currentPlayer.getName(), roll);
            int cellNumber = board.getPlaceOfPlayer(indexOfCurrentPlayer);
            String cat = board.getCellCategory(cellNumber);

            System.out.println("The category is " + cat);
            askQuestion();
        }

    }


    private void askQuestion() {
        int indexOfCurrentPlayer = players.indexOf(currentPlayer);
        int cellNumber = board.getPlaceOfPlayer(indexOfCurrentPlayer);
        String cat = board.getCellCategory(cellNumber);
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
            if (isGettingOutOfPenaltyBox) {

                System.out.println("Answer was correct!!!!");

                currentPlayer.incrementGold();

                System.out.println(currentPlayer.getName()
                        + " now has "
                        + currentPlayer.getPurse()
                        + " Gold Coins.");

                currentPlayer.getOutOfPenalTyBox();
            }
        } else {
            System.out.println("Answer was correct!!!!");

            currentPlayer.incrementGold();

            System.out.println(currentPlayer.getName()
                    + " now has "
                    + currentPlayer.getPurse()
                    + " Gold Coins.");
        }
        boolean winner = !currentPlayer.isWinner(NUMBER_OF_COINS_TO_WIN);
        nextPlayer();
        return winner;
    }

    public boolean wrongAnswer() {
        System.out.println("Question was incorrectly answered");
        System.out.println(currentPlayer.getName() + " was sent to the penalty box");

        currentPlayer.goToPenalTyBox();
        nextPlayer();
        return true;
    }

    public void nextPlayer() {
        currentPlayer = players.get((players.indexOf(currentPlayer) + 1) % players.size());
    }


}