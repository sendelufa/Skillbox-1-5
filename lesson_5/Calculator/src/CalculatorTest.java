import org.junit.*;
import org.hamcrest.*;

/**
 * Project Calculator
 * Created by Shibkov Konstantin on 20.12.2018.
 */

public class CalculatorTest {
    @Test
    public void getCircleSquare() {
        Calculator calculator = new Calculator();
        int actualSquare = (int) calculator.getCircleSquare(17.5);
        int expectedSquare = 9623;
        Assert.assertEquals(expectedSquare, actualSquare);
    }
    @Test
    public void getRectangleSquare() {
        Calculator calculator = new Calculator();
        int actualSquare = calculator.getRectangleSquare(6);
        int expectedSquare = 36;
        Assert.assertEquals(expectedSquare, actualSquare);

    }
}
