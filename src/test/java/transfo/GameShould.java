package transfo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static transfo.GameTestData.*;

@SpringBootTest
class GameShould {
    private final Game game;

    @Autowired
    public GameShould(Game game) {this.game = game;}


    private static boolean notAWinner;
    private Game aGame;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    public void setUpStreams() {
        aGame = new Game();
        aGame.initialize();
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