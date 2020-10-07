package transfo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class GameTest {

    private static boolean notAWinner;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }


    @Test
    public void test_game(){
        Game aGame = new Game();

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

        assertThat("Maxime was added\r\n" +
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
                "Maxime now has 6 Gold Coins.\r\n").isEqualTo(outContent.toString());

    }

}