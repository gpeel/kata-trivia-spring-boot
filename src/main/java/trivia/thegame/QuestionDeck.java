package trivia.thegame;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

/**
 * A new fresh deck, for each new Game => new Board => new QuestionDeck
 */
@Component
@Scope(SCOPE_PROTOTYPE)
public class QuestionDeck {
    public QuestionDeck() {
        System.err.println("NEW instance of QuestionDeck !!!!!!!!!!!!!!!!!!!!!!!!!!");
    }
}
