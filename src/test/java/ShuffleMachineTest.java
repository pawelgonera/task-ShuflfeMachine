
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShuffleMachineTest {
    private final static String TESTED_FILE = "list_of_participants.csv";
    private static final int NUMBER_OF_WINNERS = 5;
    private ShuffleMachine shuffleMachine;

    @BeforeEach
    void setUp(){

        shuffleMachine = new ShuffleMachine(TESTED_FILE, NUMBER_OF_WINNERS);
    }

    @Test
    void shouldGetSpecificNumberOfWinners(){
        final int numberOfWinnersTest = shuffleMachine.getWinners().size();

        assertEquals(NUMBER_OF_WINNERS, numberOfWinnersTest);
    }

    @Test
    void shouldGetTwiceDraw(){
        String testFileWithRecommender = "list_of_participants_test_with_recommender.csv";
        ShuffleMachine shuffleMachineForLuckyTest = new ShuffleMachine(testFileWithRecommender, 4);

        List<Participant> participantsTest = shuffleMachineForLuckyTest.getWinners();
        assertEquals(participantsTest.size(), 3);

    }

    @Test
    void shouldNotGetTwiceDraw(){
        String testFileWithOutRecommender = "list_of_participants_test_without_recommender.csv";
        ShuffleMachine shuffleMachineForLuckyTest = new ShuffleMachine(testFileWithOutRecommender, 2);

        List<Participant> participantsTest = shuffleMachineForLuckyTest.getWinners();
        System.out.println(participantsTest);
        assertEquals(participantsTest.size(), 2);
    }

}