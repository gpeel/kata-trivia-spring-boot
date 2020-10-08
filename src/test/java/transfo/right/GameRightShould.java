package transfo.right;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import transfo.Game;

import java.io.ByteArrayOutputStream;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static transfo.right.GameRightTestData.*;

@SpringBootTest
@TestExecutionListeners(SystemBufferTestExecutionListener.class)
class GameRightShould {

    private final Game aGame;
    private boolean notAWinner;

    @Autowired
    public GameRightShould(Game game) {this.aGame = game;}

    @OutContent
    private ByteArrayOutputStream outContent;

    // Optional
    // stderr is not used in this test
    //    @ErrContent
    //    private ByteArrayOutputStream errContent;

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
            fail("ONlY 5 players max!");
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

}