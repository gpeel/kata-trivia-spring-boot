package transfo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GameShould {

    private final Game game;

    GameShould(Game game) {this.game = game;}


    @Test
    public void test() {
        System.out.println("vvv");

    }
}