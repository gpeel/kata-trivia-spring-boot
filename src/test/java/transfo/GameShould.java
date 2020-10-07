package transfo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GameShould {
    private @Autowired Game game;

//    public GameShould(Game game) {this.game = game;}


    @Test
    public void test_game() {
        System.out.println("gg");
    }


}