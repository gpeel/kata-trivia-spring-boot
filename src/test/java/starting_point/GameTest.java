package starting_point;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

public class GameTest {

    private static boolean notAWinner;
    private Game aGame;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    public void setUpStreams() {
        aGame = new Game();
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void test_game_5_players_MAX() {
        boolean hasException = false;
        aGame.add("Maxime");
        aGame.add("Alexandre");
        aGame.add("Alexandre");
        aGame.add("Alexandre");
        aGame.add("Alexandre");

        try {
            aGame.add("Alexandre");
            fail("ONly 6 players max");
        } catch (ArrayIndexOutOfBoundsException e) {
            // je dois passer ICI
            hasException = true;
        }
        Assertions.assertThat(hasException).isEqualTo(true);

    }

    @Test
    public void test_game_2_players() {
        aGame.add("Maxime");
        aGame.add("Alexandre");
        Random rand = new Random(10);

        do {
            aGame.roll(rand.nextInt(5) + 1);

            if (rand.nextInt(9) == 7) {
                notAWinner = aGame.wrongAnswer();
            } else {
                notAWinner = aGame.wasCorrectlyAnswered();
            }
        } while (notAWinner);
        assertThat(outContent.toString()).isEqualTo(REPONSE_GAME_2_PLAYERS);
    }

    @Test
    public void test_game_3_players() {
        aGame.add("Maxime");
        aGame.add("Alexandre");
        aGame.add("Geoffrey");

        Random rand = new Random(5);

        do {
            aGame.roll(rand.nextInt(5) + 1);
            notAWinner = aGame.wasCorrectlyAnswered();
        } while (notAWinner);
        assertThat(outContent.toString()).isEqualTo(REPONSE_GAME_3_PLAYERS);
    }

    @Test
    public void test_game_5_players() {
        aGame.add("Maxime");
        aGame.add("Alexandre");
        aGame.add("Geoffrey");
        aGame.add("Gauthier");
        aGame.add("Arnaud");

        Random rand = new Random(177777);

        do {
            aGame.roll(rand.nextInt(5) + 1);

            if (rand.nextInt(3) == 2) {
                notAWinner = aGame.wrongAnswer();
            } else {
                notAWinner = aGame.wasCorrectlyAnswered();
            }
        } while (notAWinner);
        assertThat(outContent.toString()).isEqualTo(REPONSE_GAME_5_PLAYERS);
    }

    private static final String REPONSE_GAME_5_PLAYERS =
            "Maxime was added\r\n" +
                    "They are player number 1\r\n" +
                    "Alexandre was added\r\n" +
                    "They are player number 2\r\n" +
                    "Geoffrey was added\r\n" +
                    "They are player number 3\r\n" +
                    "Gauthier was added\r\n" +
                    "They are player number 4\r\n" +
                    "Arnaud was added\r\n" +
                    "They are player number 5\r\n" +
                    "Maxime is the current player\r\n" +
                    "They have rolled a 3\r\n" +
                    "Maxime's new location is 3\r\n" +
                    "The category is Rock\r\n" +
                    "Rock Question 0\r\n" +
                    "Answer was corrent!!!!\r\n" +
                    "Maxime now has 1 Gold Coins.\r\n" +
                    "Alexandre is the current player\r\n" +
                    "They have rolled a 1\r\n" +
                    "Alexandre's new location is 1\r\n" +
                    "The category is Science\r\n" +
                    "Science Question 0\r\n" +
                    "Question was incorrectly answered\r\n" +
                    "Alexandre was sent to the penalty box\r\n" +
                    "Geoffrey is the current player\r\n" +
                    "They have rolled a 5\r\n" +
                    "Geoffrey's new location is 5\r\n" +
                    "The category is Science\r\n" +
                    "Science Question 1\r\n" +
                    "Answer was corrent!!!!\r\n" +
                    "Geoffrey now has 1 Gold Coins.\r\n" +
                    "Gauthier is the current player\r\n" +
                    "They have rolled a 1\r\n" +
                    "Gauthier's new location is 1\r\n" +
                    "The category is Science\r\n" +
                    "Science Question 2\r\n" +
                    "Answer was corrent!!!!\r\n" +
                    "Gauthier now has 1 Gold Coins.\r\n" +
                    "Arnaud is the current player\r\n" +
                    "They have rolled a 5\r\n" +
                    "Arnaud's new location is 5\r\n" +
                    "The category is Science\r\n" +
                    "Science Question 3\r\n" +
                    "Answer was corrent!!!!\r\n" +
                    "Arnaud now has 1 Gold Coins.\r\n" +
                    "Maxime is the current player\r\n" +
                    "They have rolled a 5\r\n" +
                    "Maxime's new location is 8\r\n" +
                    "The category is Pop\r\n" +
                    "Pop Question 0\r\n" +
                    "Answer was corrent!!!!\r\n" +
                    "Maxime now has 2 Gold Coins.\r\n" +
                    "Alexandre is the current player\r\n" +
                    "They have rolled a 4\r\n" +
                    "Alexandre is not getting out of the penalty box\r\n" +
                    "Geoffrey is the current player\r\n" +
                    "They have rolled a 4\r\n" +
                    "Geoffrey's new location is 9\r\n" +
                    "The category is Science\r\n" +
                    "Science Question 4\r\n" +
                    "Answer was corrent!!!!\r\n" +
                    "Geoffrey now has 2 Gold Coins.\r\n" +
                    "Gauthier is the current player\r\n" +
                    "They have rolled a 1\r\n" +
                    "Gauthier's new location is 2\r\n" +
                    "The category is Sports\r\n" +
                    "Sports Question 0\r\n" +
                    "Answer was corrent!!!!\r\n" +
                    "Gauthier now has 2 Gold Coins.\r\n" +
                    "Arnaud is the current player\r\n" +
                    "They have rolled a 3\r\n" +
                    "Arnaud's new location is 8\r\n" +
                    "The category is Pop\r\n" +
                    "Pop Question 1\r\n" +
                    "Answer was corrent!!!!\r\n" +
                    "Arnaud now has 2 Gold Coins.\r\n" +
                    "Maxime is the current player\r\n" +
                    "They have rolled a 2\r\n" +
                    "Maxime's new location is 10\r\n" +
                    "The category is Sports\r\n" +
                    "Sports Question 1\r\n" +
                    "Question was incorrectly answered\r\n" +
                    "Maxime was sent to the penalty box\r\n" +
                    "Alexandre is the current player\r\n" +
                    "They have rolled a 1\r\n" +
                    "Alexandre is getting out of the penalty box\r\n" +
                    "Alexandre's new location is 2\r\n" +
                    "The category is Sports\r\n" +
                    "Sports Question 2\r\n" +
                    "Answer was correct!!!!\r\n" +
                    "Alexandre now has 1 Gold Coins.\r\n" +
                    "Geoffrey is the current player\r\n" +
                    "They have rolled a 2\r\n" +
                    "Geoffrey's new location is 11\r\n" +
                    "The category is Rock\r\n" +
                    "Rock Question 1\r\n" +
                    "Answer was corrent!!!!\r\n" +
                    "Geoffrey now has 3 Gold Coins.\r\n" +
                    "Gauthier is the current player\r\n" +
                    "They have rolled a 4\r\n" +
                    "Gauthier's new location is 6\r\n" +
                    "The category is Sports\r\n" +
                    "Sports Question 3\r\n" +
                    "Question was incorrectly answered\r\n" +
                    "Gauthier was sent to the penalty box\r\n" +
                    "Arnaud is the current player\r\n" +
                    "They have rolled a 3\r\n" +
                    "Arnaud's new location is 11\r\n" +
                    "The category is Rock\r\n" +
                    "Rock Question 2\r\n" +
                    "Answer was corrent!!!!\r\n" +
                    "Arnaud now has 3 Gold Coins.\r\n" +
                    "Maxime is the current player\r\n" +
                    "They have rolled a 4\r\n" +
                    "Maxime is not getting out of the penalty box\r\n" +
                    "Question was incorrectly answered\r\n" +
                    "Maxime was sent to the penalty box\r\n" +
                    "Alexandre is the current player\r\n" +
                    "They have rolled a 5\r\n" +
                    "Alexandre is getting out of the penalty box\r\n" +
                    "Alexandre's new location is 7\r\n" +
                    "The category is Rock\r\n" +
                    "Rock Question 3\r\n" +
                    "Question was incorrectly answered\r\n" +
                    "Alexandre was sent to the penalty box\r\n" +
                    "Geoffrey is the current player\r\n" +
                    "They have rolled a 4\r\n" +
                    "Geoffrey's new location is 3\r\n" +
                    "The category is Rock\r\n" +
                    "Rock Question 4\r\n" +
                    "Question was incorrectly answered\r\n" +
                    "Geoffrey was sent to the penalty box\r\n" +
                    "Gauthier is the current player\r\n" +
                    "They have rolled a 3\r\n" +
                    "Gauthier is getting out of the penalty box\r\n" +
                    "Gauthier's new location is 9\r\n" +
                    "The category is Science\r\n" +
                    "Science Question 5\r\n" +
                    "Answer was correct!!!!\r\n" +
                    "Gauthier now has 3 Gold Coins.\r\n" +
                    "Arnaud is the current player\r\n" +
                    "They have rolled a 1\r\n" +
                    "Arnaud's new location is 0\r\n" +
                    "The category is Pop\r\n" +
                    "Pop Question 2\r\n" +
                    "Answer was corrent!!!!\r\n" +
                    "Arnaud now has 4 Gold Coins.\r\n" +
                    "Maxime is the current player\r\n" +
                    "They have rolled a 2\r\n" +
                    "Maxime is not getting out of the penalty box\r\n" +
                    "Alexandre is the current player\r\n" +
                    "They have rolled a 2\r\n" +
                    "Alexandre is not getting out of the penalty box\r\n" +
                    "Geoffrey is the current player\r\n" +
                    "They have rolled a 3\r\n" +
                    "Geoffrey is getting out of the penalty box\r\n" +
                    "Geoffrey's new location is 6\r\n" +
                    "The category is Sports\r\n" +
                    "Sports Question 4\r\n" +
                    "Answer was correct!!!!\r\n" +
                    "Geoffrey now has 4 Gold Coins.\r\n" +
                    "Gauthier is the current player\r\n" +
                    "They have rolled a 1\r\n" +
                    "Gauthier is getting out of the penalty box\r\n" +
                    "Gauthier's new location is 10\r\n" +
                    "The category is Sports\r\n" +
                    "Sports Question 5\r\n" +
                    "Answer was correct!!!!\r\n" +
                    "Gauthier now has 4 Gold Coins.\r\n" +
                    "Arnaud is the current player\r\n" +
                    "They have rolled a 5\r\n" +
                    "Arnaud's new location is 5\r\n" +
                    "The category is Science\r\n" +
                    "Science Question 6\r\n" +
                    "Answer was corrent!!!!\r\n" +
                    "Arnaud now has 5 Gold Coins.\r\n" +
                    "Maxime is the current player\r\n" +
                    "They have rolled a 1\r\n" +
                    "Maxime is getting out of the penalty box\r\n" +
                    "Maxime's new location is 11\r\n" +
                    "The category is Rock\r\n" +
                    "Rock Question 5\r\n" +
                    "Question was incorrectly answered\r\n" +
                    "Maxime was sent to the penalty box\r\n" +
                    "Alexandre is the current player\r\n" +
                    "They have rolled a 1\r\n" +
                    "Alexandre is getting out of the penalty box\r\n" +
                    "Alexandre's new location is 8\r\n" +
                    "The category is Pop\r\n" +
                    "Pop Question 3\r\n" +
                    "Answer was correct!!!!\r\n" +
                    "Alexandre now has 2 Gold Coins.\r\n" +
                    "Geoffrey is the current player\r\n" +
                    "They have rolled a 5\r\n" +
                    "Geoffrey is getting out of the penalty box\r\n" +
                    "Geoffrey's new location is 11\r\n" +
                    "The category is Rock\r\n" +
                    "Rock Question 6\r\n" +
                    "Question was incorrectly answered\r\n" +
                    "Geoffrey was sent to the penalty box\r\n" +
                    "Gauthier is the current player\r\n" +
                    "They have rolled a 1\r\n" +
                    "Gauthier is getting out of the penalty box\r\n" +
                    "Gauthier's new location is 11\r\n" +
                    "The category is Rock\r\n" +
                    "Rock Question 7\r\n" +
                    "Answer was correct!!!!\r\n" +
                    "Gauthier now has 5 Gold Coins.\r\n" +
                    "Arnaud is the current player\r\n" +
                    "They have rolled a 5\r\n" +
                    "Arnaud's new location is 10\r\n" +
                    "The category is Sports\r\n" +
                    "Sports Question 6\r\n" +
                    "Answer was corrent!!!!\r\n" +
                    "Arnaud now has 6 Gold Coins.\r\n";


    private static final String REPONSE_GAME_3_PLAYERS =
            "Maxime was added\r\n" +
                    "They are player number 1\r\n" +
                    "Alexandre was added\r\n" +
                    "They are player number 2\r\n" +
                    "Geoffrey was added\r\n" +
                    "They are player number 3\r\n" +
                    "Maxime is the current player\r\n" +
                    "They have rolled a 3\r\n" +
                    "Maxime's new location is 3\r\n" +
                    "The category is Rock\r\n" +
                    "Rock Question 0\r\n" +
                    "Answer was corrent!!!!\r\n" +
                    "Maxime now has 1 Gold Coins.\r\n" +
                    "Alexandre is the current player\r\n" +
                    "They have rolled a 3\r\n" +
                    "Alexandre's new location is 3\r\n" +
                    "The category is Rock\r\n" +
                    "Rock Question 1\r\n" +
                    "Answer was corrent!!!!\r\n" +
                    "Alexandre now has 1 Gold Coins.\r\n" +
                    "Geoffrey is the current player\r\n" +
                    "They have rolled a 5\r\n" +
                    "Geoffrey's new location is 5\r\n" +
                    "The category is Science\r\n" +
                    "Science Question 0\r\n" +
                    "Answer was corrent!!!!\r\n" +
                    "Geoffrey now has 1 Gold Coins.\r\n" +
                    "Maxime is the current player\r\n" +
                    "They have rolled a 5\r\n" +
                    "Maxime's new location is 8\r\n" +
                    "The category is Pop\r\n" +
                    "Pop Question 0\r\n" +
                    "Answer was corrent!!!!\r\n" +
                    "Maxime now has 2 Gold Coins.\r\n" +
                    "Alexandre is the current player\r\n" +
                    "They have rolled a 2\r\n" +
                    "Alexandre's new location is 5\r\n" +
                    "The category is Science\r\n" +
                    "Science Question 1\r\n" +
                    "Answer was corrent!!!!\r\n" +
                    "Alexandre now has 2 Gold Coins.\r\n" +
                    "Geoffrey is the current player\r\n" +
                    "They have rolled a 1\r\n" +
                    "Geoffrey's new location is 6\r\n" +
                    "The category is Sports\r\n" +
                    "Sports Question 0\r\n" +
                    "Answer was corrent!!!!\r\n" +
                    "Geoffrey now has 2 Gold Coins.\r\n" +
                    "Maxime is the current player\r\n" +
                    "They have rolled a 5\r\n" +
                    "Maxime's new location is 1\r\n" +
                    "The category is Science\r\n" +
                    "Science Question 2\r\n" +
                    "Answer was corrent!!!!\r\n" +
                    "Maxime now has 3 Gold Coins.\r\n" +
                    "Alexandre is the current player\r\n" +
                    "They have rolled a 2\r\n" +
                    "Alexandre's new location is 7\r\n" +
                    "The category is Rock\r\n" +
                    "Rock Question 2\r\n" +
                    "Answer was corrent!!!!\r\n" +
                    "Alexandre now has 3 Gold Coins.\r\n" +
                    "Geoffrey is the current player\r\n" +
                    "They have rolled a 3\r\n" +
                    "Geoffrey's new location is 9\r\n" +
                    "The category is Science\r\n" +
                    "Science Question 3\r\n" +
                    "Answer was corrent!!!!\r\n" +
                    "Geoffrey now has 3 Gold Coins.\r\n" +
                    "Maxime is the current player\r\n" +
                    "They have rolled a 2\r\n" +
                    "Maxime's new location is 3\r\n" +
                    "The category is Rock\r\n" +
                    "Rock Question 3\r\n" +
                    "Answer was corrent!!!!\r\n" +
                    "Maxime now has 4 Gold Coins.\r\n" +
                    "Alexandre is the current player\r\n" +
                    "They have rolled a 2\r\n" +
                    "Alexandre's new location is 9\r\n" +
                    "The category is Science\r\n" +
                    "Science Question 4\r\n" +
                    "Answer was corrent!!!!\r\n" +
                    "Alexandre now has 4 Gold Coins.\r\n" +
                    "Geoffrey is the current player\r\n" +
                    "They have rolled a 4\r\n" +
                    "Geoffrey's new location is 1\r\n" +
                    "The category is Science\r\n" +
                    "Science Question 5\r\n" +
                    "Answer was corrent!!!!\r\n" +
                    "Geoffrey now has 4 Gold Coins.\r\n" +
                    "Maxime is the current player\r\n" +
                    "They have rolled a 3\r\n" +
                    "Maxime's new location is 6\r\n" +
                    "The category is Sports\r\n" +
                    "Sports Question 1\r\n" +
                    "Answer was corrent!!!!\r\n" +
                    "Maxime now has 5 Gold Coins.\r\n" +
                    "Alexandre is the current player\r\n" +
                    "They have rolled a 1\r\n" +
                    "Alexandre's new location is 10\r\n" +
                    "The category is Sports\r\n" +
                    "Sports Question 2\r\n" +
                    "Answer was corrent!!!!\r\n" +
                    "Alexandre now has 5 Gold Coins.\r\n" +
                    "Geoffrey is the current player\r\n" +
                    "They have rolled a 4\r\n" +
                    "Geoffrey's new location is 5\r\n" +
                    "The category is Science\r\n" +
                    "Science Question 6\r\n" +
                    "Answer was corrent!!!!\r\n" +
                    "Geoffrey now has 5 Gold Coins.\r\n" +
                    "Maxime is the current player\r\n" +
                    "They have rolled a 1\r\n" +
                    "Maxime's new location is 7\r\n" +
                    "The category is Rock\r\n" +
                    "Rock Question 4\r\n" +
                    "Answer was corrent!!!!\r\n" +
                    "Maxime now has 6 Gold Coins.\r\n";

    private static final String REPONSE_GAME_2_PLAYERS = "Maxime was added\r\n" +
            "They are player number 1\r\n" +
            "Alexandre was added\r\n" +
            "They are player number 2\r\n" +
            "Maxime is the current player\r\n" +
            "They have rolled a 4\r\n" +
            "Maxime's new location is 4\r\n" +
            "The category is Pop\r\n" +
            "Pop Question 0\r\n" +
            "Answer was corrent!!!!\r\n" +
            "Maxime now has 1 Gold Coins.\r\n" +
            "Alexandre is the current player\r\n" +
            "They have rolled a 4\r\n" +
            "Alexandre's new location is 4\r\n" +
            "The category is Pop\r\n" +
            "Pop Question 1\r\n" +
            "Answer was corrent!!!!\r\n" +
            "Alexandre now has 1 Gold Coins.\r\n" +
            "Maxime is the current player\r\n" +
            "They have rolled a 2\r\n" +
            "Maxime's new location is 6\r\n" +
            "The category is Sports\r\n" +
            "Sports Question 0\r\n" +
            "Answer was corrent!!!!\r\n" +
            "Maxime now has 2 Gold Coins.\r\n" +
            "Alexandre is the current player\r\n" +
            "They have rolled a 3\r\n" +
            "Alexandre's new location is 7\r\n" +
            "The category is Rock\r\n" +
            "Rock Question 0\r\n" +
            "Answer was corrent!!!!\r\n" +
            "Alexandre now has 2 Gold Coins.\r\n" +
            "Maxime is the current player\r\n" +
            "They have rolled a 2\r\n" +
            "Maxime's new location is 8\r\n" +
            "The category is Pop\r\n" +
            "Pop Question 2\r\n" +
            "Answer was corrent!!!!\r\n" +
            "Maxime now has 3 Gold Coins.\r\n" +
            "Alexandre is the current player\r\n" +
            "They have rolled a 4\r\n" +
            "Alexandre's new location is 11\r\n" +
            "The category is Rock\r\n" +
            "Rock Question 1\r\n" +
            "Answer was corrent!!!!\r\n" +
            "Alexandre now has 3 Gold Coins.\r\n" +
            "Maxime is the current player\r\n" +
            "They have rolled a 2\r\n" +
            "Maxime's new location is 10\r\n" +
            "The category is Sports\r\n" +
            "Sports Question 1\r\n" +
            "Answer was corrent!!!!\r\n" +
            "Maxime now has 4 Gold Coins.\r\n" +
            "Alexandre is the current player\r\n" +
            "They have rolled a 1\r\n" +
            "Alexandre's new location is 0\r\n" +
            "The category is Pop\r\n" +
            "Pop Question 3\r\n" +
            "Answer was corrent!!!!\r\n" +
            "Alexandre now has 4 Gold Coins.\r\n" +
            "Maxime is the current player\r\n" +
            "They have rolled a 2\r\n" +
            "Maxime's new location is 0\r\n" +
            "The category is Pop\r\n" +
            "Pop Question 4\r\n" +
            "Answer was corrent!!!!\r\n" +
            "Maxime now has 5 Gold Coins.\r\n" +
            "Alexandre is the current player\r\n" +
            "They have rolled a 4\r\n" +
            "Alexandre's new location is 4\r\n" +
            "The category is Pop\r\n" +
            "Pop Question 5\r\n" +
            "Question was incorrectly answered\r\n" +
            "Alexandre was sent to the penalty box\r\n" +
            "Maxime is the current player\r\n" +
            "They have rolled a 4\r\n" +
            "Maxime's new location is 4\r\n" +
            "The category is Pop\r\n" +
            "Pop Question 6\r\n" +
            "Question was incorrectly answered\r\n" +
            "Maxime was sent to the penalty box\r\n" +
            "Alexandre is the current player\r\n" +
            "They have rolled a 1\r\n" +
            "Alexandre is getting out of the penalty box\r\n" +
            "Alexandre's new location is 5\r\n" +
            "The category is Science\r\n" +
            "Science Question 0\r\n" +
            "Answer was correct!!!!\r\n" +
            "Alexandre now has 5 Gold Coins.\r\n" +
            "Maxime is the current player\r\n" +
            "They have rolled a 1\r\n" +
            "Maxime is getting out of the penalty box\r\n" +
            "Maxime's new location is 5\r\n" +
            "The category is Science\r\n" +
            "Science Question 1\r\n" +
            "Answer was correct!!!!\r\n" +
            "Maxime now has 6 Gold Coins.\r\n";
}