package transfo;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.LinkedList;

@Component
public class Game {
    private static int MAX_PLAYERS = 5;
    private static int NUMBER_OF_COINS_TO_WIN = 6;
    private static int NUMBER_QUESTION = 50;

    Board board = new Board();

    ArrayList players = new ArrayList();
    int[] places = new int[MAX_PLAYERS];
    int[] purses = new int[MAX_PLAYERS];
    boolean[] inPenaltyBox = new boolean[MAX_PLAYERS];

    LinkedList popQuestions = new LinkedList();
    LinkedList scienceQuestions = new LinkedList();
    LinkedList sportsQuestions = new LinkedList();
    LinkedList rockQuestions = new LinkedList();

    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;

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

    public boolean add(String playerName) {
        players.add(playerName);
        int iP = players.size() - 1;
        places[iP] = 0;
        purses[iP] = 0;
        inPenaltyBox[iP] = false;

        System.out.println(playerName + " was added");
        System.out.println("They are player number " + players.size());
        return true;
    }

    public void roll(int roll) {
        System.out.println(players.get(currentPlayer) + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (inPenaltyBox[currentPlayer]) {
            if (roll % 2 != 0) {
                isGettingOutOfPenaltyBox = true;

                System.out.println(players.get(currentPlayer) + " is getting out of the penalty box");
                places[currentPlayer] = places[currentPlayer] + roll;
                if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12;

                System.out.println(players.get(currentPlayer)
                        + "'s new location is "
                        + places[currentPlayer]);
                System.out.println("The category is " + board.getCategory(getPlaceOfCurrentPlayer()));
                askQuestion();
            } else {
                System.out.println(players.get(currentPlayer) + " is not getting out of the penalty box");
                isGettingOutOfPenaltyBox = false;
            }

        } else {
            places[currentPlayer] = places[currentPlayer] + roll;
            if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12;

            System.out.println(players.get(currentPlayer)
                    + "'s new location is "
                    + places[currentPlayer]);
            System.out.println("The category is " + board.getCategory(getPlaceOfCurrentPlayer()));
            askQuestion();
        }

    }

    private void askQuestion() {
        if (board.getCategory(getPlaceOfCurrentPlayer()).equals("Pop"))
            System.out.println(popQuestions.removeFirst());
        if (board.getCategory(getPlaceOfCurrentPlayer()).equals("Science"))
            System.out.println(scienceQuestions.removeFirst());
        if (board.getCategory(getPlaceOfCurrentPlayer()).equals("Sports"))
            System.out.println(sportsQuestions.removeFirst());
        if (board.getCategory(getPlaceOfCurrentPlayer()).equals("Rock"))
            System.out.println(rockQuestions.removeFirst());
    }

    private int getPlaceOfCurrentPlayer() {
        return places[currentPlayer];
    }

//    private String board.getCategory(() {
//        if (places[currentPlayer] == 0) return "Pop";
//        if (places[currentPlayer] == 4) return "Pop";
//        if (places[currentPlayer] == 8) return "Pop";
//        if (places[currentPlayer] == 1) return "Science";
//        if (places[currentPlayer] == 5) return "Science";
//        if (places[currentPlayer] == 9) return "Science";
//        if (places[currentPlayer] == 2) return "Sports";
//        if (places[currentPlayer] == 6) return "Sports";
//        if (places[currentPlayer] == 10) return "Sports";
//        return "Rock";
//    }

    public boolean wasCorrectlyAnswered() {
        if (inPenaltyBox[currentPlayer]) {
            if (isGettingOutOfPenaltyBox) {
                System.out.println("Answer was correct!!!!");
                purses[currentPlayer]++;
                System.out.println(players.get(currentPlayer)
                        + " now has "
                        + purses[currentPlayer]
                        + " Gold Coins.");

                boolean winner = didPlayerWin();
                currentPlayer++;
                if (currentPlayer == players.size()) currentPlayer = 0;

                return winner;
            } else {
                currentPlayer++;
                if (currentPlayer == players.size()) currentPlayer = 0;
                return true;
            }

        } else {

            System.out.println("Answer was corrent!!!!");
            purses[currentPlayer]++;
            System.out.println(players.get(currentPlayer)
                    + " now has "
                    + purses[currentPlayer]
                    + " Gold Coins.");

            boolean winner = didPlayerWin();
            currentPlayer++;
            if (currentPlayer == players.size()) currentPlayer = 0;

            return winner;
        }
    }

    public boolean wrongAnswer() {
        System.out.println("Question was incorrectly answered");
        System.out.println(players.get(currentPlayer) + " was sent to the penalty box");
        inPenaltyBox[currentPlayer] = true;

        currentPlayer++;
        if (currentPlayer == players.size()) currentPlayer = 0;
        return true;
    }


    private boolean didPlayerWin() {
        return !(purses[currentPlayer] == NUMBER_OF_COINS_TO_WIN);
    }
}