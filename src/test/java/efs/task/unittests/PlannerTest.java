package efs.task.unittests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlannerTest {

    @ParameterizedTest
    @EnumSource(ActivityLevel.class)
    void shouldCalculateCorrectCalories(ActivityLevel level) {
        //given
        Planner planner = new Planner();
        User testUser = TestConstants.TEST_USER;
        int expectedCalories = TestConstants.CALORIES_ON_ACTIVITY_LEVEL.get(level);
        //when
        int actualCalories = planner.calculateDailyCaloriesDemand(testUser, level);
        //then
        assertEquals(expectedCalories, actualCalories, "Calculated daily calories do not match expected value");
    }

    @Test
    void shouldCalculateCorrectDailyIntake() {
        //given
        Planner planner = new Planner();
        User testUser = TestConstants.TEST_USER;
        DailyIntake expectedDailyIntake = TestConstants.TEST_USER_DAILY_INTAKE;
        //when
        DailyIntake actualDailyIntake = planner.calculateDailyIntake(testUser);
        //then
        assertEquals(expectedDailyIntake.getCalories(), actualDailyIntake.getCalories());
        assertEquals(expectedDailyIntake.getProtein(), actualDailyIntake.getProtein());
        assertEquals(expectedDailyIntake.getFat(), actualDailyIntake.getFat());
        assertEquals(expectedDailyIntake.getCarbohydrate(), actualDailyIntake.getCarbohydrate());
    }
}