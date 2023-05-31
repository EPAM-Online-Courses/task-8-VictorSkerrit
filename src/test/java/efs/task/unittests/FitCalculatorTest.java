package efs.task.unittests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FitCalculatorTest {

    @Test
    void shouldReturnTrue_whenDietRecommended() {
        //given
        double weight = 89.2;
        double height = 1.72;

        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        //then
        assertTrue(recommended);
    }

    @Test
    void Should_ReturnFalse_When_isBMICorrectAsFalse(){
        //given
        double weight = 69.5;
        double height = 1.72;

        //when
        boolean isBmiCorrect = FitCalculator.isBMICorrect(weight,height);

        //then
        assertFalse(isBmiCorrect);
    }
    @ParameterizedTest
    //given
    @ValueSource(doubles= {95,120,85.8})
    void Should_ReturnFalseForAllValues_When_isBMICorrectAsFalse(double weight) {
        //given
        double height = 1.83;
        //when
        boolean result = FitCalculator.isBMICorrect(weight,height);
        //then
        assertTrue(result);
    }

    @ParameterizedTest
    //given
    @CsvSource({
            "175,73.5",
            "185,79.2",
            "190,100.99"
    })
    void Should_ReturnFalseForAllPairs_When_isBMICorrectAsFalse(double height, double weight) {
        //when
        boolean result = FitCalculator.isBMICorrect(weight,height);
        //then
        assertFalse(result);
    }

    @ParameterizedTest(name = "{index} Testing for weight : {0} and height {1} {arguments} ")
    //given
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
    void Should_ReturnFalseForAllPairsCSVFile_When_isBMICorrectAsFalse(double height, double weight) {
        //when
        boolean result = FitCalculator.isBMICorrect(weight,height);
        //then
        assertFalse(result);
    }

    @Test
    void Should_ReturnTrue_when_USER_LIST_wors_BMI(){
        //given
        List<User> users = TestConstants.TEST_USERS_LIST;

        //when
        User userWithHighestBmi = FitCalculator.findUserWithTheWorstBMI(users);

        //then
        assertNotNull(userWithHighestBmi, "Returned user should not be null");
        assertEquals(97.3, userWithHighestBmi.getWeight(), "Weight does not match");
        assertEquals(1.79, userWithHighestBmi.getHeight(), "Height does not match");
    }

    @Test
    void shouldReturnNullForEmptyList() {
        //gicen
        List<User> emptyList = new ArrayList<>();

        //when
        User result = FitCalculator.findUserWithTheWorstBMI(emptyList);

        //then
        assertNull(result);
    }

    @Test
    void shouldReturnNullForEmptyList2() {
        //given
        var users = TestConstants.TEST_USERS_LIST;
        var expectedScores = TestConstants.TEST_USERS_BMI_SCORE;
        //when
        double[] actualScores = FitCalculator.calculateBMIScore(users);
        //then
        assertArrayEquals(expectedScores, actualScores, 0.01, "BMI scores do not match");
    }
}
