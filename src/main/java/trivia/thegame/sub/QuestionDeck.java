package trivia.thegame.sub;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.LinkedList;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

/**
 * A new fresh deck, for each new Game => new Board => new QuestionDeck
 */
@Component
@Scope(SCOPE_PROTOTYPE)
public class QuestionDeck {

    LinkedList popQuestions = new LinkedList();
    LinkedList scienceQuestions = new LinkedList();
    LinkedList sportsQuestions = new LinkedList();
    LinkedList rockQuestions = new LinkedList();

    public QuestionDeck() {
        System.err.println("NEW instance of QuestionDeck !!!!!!!!!!!!!!!!!!!!!!!!!!");
    }

    public void initializeTheDeck(int numberOfQuestions) {
        for (int i = 0; i < numberOfQuestions; i++) {
            popQuestions.addLast("Pop Question " + i);
            scienceQuestions.addLast(("Science Question " + i));
            sportsQuestions.addLast(("Sports Question " + i));
            rockQuestions.addLast("Rock Question " + i);
        }
    }

    public void askQuestion(String category) {

        switch (category) {
            case "Pop":
                System.out.println(popQuestions.removeFirst());
                break;
            case "Science":
                System.out.println(scienceQuestions.removeFirst());
                break;
            case "Sports":
                System.out.println(sportsQuestions.removeFirst());
                break;
            case "Rock":
                System.out.println(rockQuestions.removeFirst());
                break;
        }
    }
}
