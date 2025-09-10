import org.junit.Test;

import junit.*;

public class CarTest {

    @Test
    public static void test1() {
        APScar car = new APScar();
        CarState carState = car.getCarState();
        boolean isParked = carState.getIsParked();
        assertFalse(isParked);
    }
    
}