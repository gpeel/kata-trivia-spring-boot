package trivia.thegame;

import gauthier.OutContent;
import gauthier.SystemBufferTestExecutionListener;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;

import java.io.ByteArrayOutputStream;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static trivia.thegame.GameTestData.*;

@SpringBootTest
@TestExecutionListeners(SystemBufferTestExecutionListener.class)
class GameShould {

    private final Game aGame;
    private boolean notAWinner;

    @Autowired
    public GameShould(Game game) {this.aGame = game;}

    @OutContent
    private ByteArrayOutputStream outContent;

    // Optional
    // stderr is not used in this test
    //    @ErrContent
    //    private ByteArrayOutputStream errContent;

    @Test
    public void test_game_5_players_MAX() {
        boolean hasException = false;
        aGame.addPlayer("Maxime");
        aGame.addPlayer("Alexandre");
        aGame.addPlayer("Alexandre");
        aGame.addPlayer("Alexandre");
        aGame.addPlayer("Alexandre");

        try {
            aGame.addPlayer("Alexandre");
            fail("ONLY 5 players max!");
        } catch (Exception e) {
            // execution must arrive here
            hasException = true;
        }
        Assertions.assertThat(hasException).isEqualTo(true);

    }

    /**
     * After a wrong answer => the player is in Penalty Box.
     * After an odd roll => the player should be out,
     * and could answer a question and get a Gold coins.
     * But the startging point leaves the Player in Penalty, so only ODD roll could give him coins.
     * Let's prove it with Max !
     * <p>
     * Bugged
     * <pre>
     *      Max is the current player
     *      They have rolled a 2
     *      Max is not getting out of the penalty box
     * </pre>
     * Corrected
     * <pre>
     *      Max is the current player
     *      They have rolled a 2
     *      Max's new location is 5
     *      The category is Science
     *      Science Question 0
     *      Answer was corrent!!!!
     *      Max now has 2 Gold Coins.
     * </pre>
     */
    @Test
    @Disabled
    public void test_cannot_leave_penalty_box() {
        aGame.addPlayer("Max");
        // ACT
        aGame.roll(2);
        aGame.wrongAnswer();
        // now Max is in PenaltyBox
        aGame.roll(1);
        aGame.rightAnswer(); // ok + 1 Gold
        // now Max should be out, and a correct answer should score
        aGame.roll(2); // even or odd should score + 1 in Gold
        aGame.rightAnswer(); // KO no more Gold !

        // ASSERT
        assertThat(outContent.toString().replaceAll("\r\n", "\n"))
                .isEqualTo(REPONSE_IF_PENALTY_VISITED_NO_MORE_GOLD_ON_EVEN_ROLL.replaceAll("\r\n", "\n"));
    }

    @Test
    public void test_penalty_box_corrected() {
        aGame.addPlayer("Max");
        // ACT
        aGame.roll(2);
        aGame.wrongAnswer();
        // now Max is in PenaltyBox
        aGame.roll(1);
        aGame.rightAnswer(); // ok + 1 Gold
        // now Max should be out, and a correct answer should score
        aGame.roll(2); // even or odd should score + 1 in Gold
        aGame.rightAnswer(); // KO no more Gold !

        // ASSERT
        assertThat(outContent.toString().replaceAll("\r\n", "\n"))
                .isEqualTo(REPONSE_CORRECTED_IF_PENALTY_VISITED_NO_MORE_GOLD_ON_EVEN_ROLL.replaceAll("\r\n", "\n"));
    }

    @Test
    public void test_game_2_players() {
        aGame.addPlayer("Maxime");
        aGame.addPlayer("Alexandre");
        Random rand = new Random(10);

        do {
            aGame.roll(rand.nextInt(5) + 1);

            if (rand.nextInt(9) == 7) {
                aGame.wrongAnswer();
            } else {
                notAWinner = aGame.rightAnswer();
            }
        } while (notAWinner);
        assertThat(outContent.toString().replaceAll("\r\n", "\n"))
                .isEqualTo(REPONSE_GAME_2_PLAYERS.replaceAll("\r\n", "\n"));
    }

    @Test
    public void test_game_3_players() {
        aGame.addPlayer("Maxime");
        aGame.addPlayer("Alexandre");
        aGame.addPlayer("Geoffrey");

        Random rand = new Random(5);

        do {
            aGame.roll(rand.nextInt(5) + 1);
            notAWinner = aGame.rightAnswer();
        } while (notAWinner);

        assertThat(outContent.toString().replaceAll("\r\n", "\n"))
                .isEqualTo(REPONSE_GAME_3_PLAYERS.replaceAll("\r\n", "\n"));
    }

    @Test
    public void test_game_5_players() {
        aGame.addPlayer("Maxime");
        aGame.addPlayer("Alexandre");
        aGame.addPlayer("Geoffrey");
        aGame.addPlayer("Gauthier");
        aGame.addPlayer("Arnaud");
        Random rand = new Random(177777);

        do {
            aGame.roll(rand.nextInt(5) + 1);

            if (rand.nextInt(3) == 2) {
                aGame.wrongAnswer();
            } else {
                notAWinner = aGame.rightAnswer();
            }
        } while (notAWinner);
        assertThat(outContent.toString().replaceAll("\r\n", "\n"))
                .isEqualTo(REPONSE_CORRECTED_GAME_5_PLAYERS.replaceAll("\r\n", "\n"));

    }

}