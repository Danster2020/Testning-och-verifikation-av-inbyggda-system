import org.junit.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CarTest {

    APScar car;

    @Before // before each test
    public void setUp() {
        this.car = new APScar();
    }

    @Test
    public void checkIfParked() {
        boolean isParked = car.WhereIs().getIsParked();
        assertEquals(false, isParked);
    }

    @Test
    public void checkIsEmpty() {
        boolean isEmpty = this.car.isEmpty();
        assertEquals(false, isEmpty);
    }
}