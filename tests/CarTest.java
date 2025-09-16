import org.junit.*;

import static org.junit.Assert.assertEquals;

public class CarTest {

    APScar car;
    int ROAD_LENGTH = 500;

    @Before // before each test
    public void setUp() {
        this.car = new APScar();

    }

    @Test
    public void isNotParked() {
        boolean isParked = car.WhereIs().getIsParked();
        assertEquals(false, isParked);
    }

    @Test
    public void isNotEmpty() {
        boolean isEmpty = this.car.isEmpty();
        assertEquals(false, isEmpty);
    }

    @Test
    public void carMovesForwardAtEndOfRoad() {
        int posEndOfRoad = ROAD_LENGTH - 1;
        car.getCarState().setPosition(posEndOfRoad); // 499 is end of road
        int orgPos = car.getCarState().getPosition();
        car.MoveForward();

        int newPos = car.getCarState().getPosition();
        assertEquals(orgPos, newPos);
    }

    @Test
    public void carMovesBackward() {
        car.getCarState().setPosition(1);
        int orgPos = car.getCarState().getPosition();
        CarState newCarState = car.MoveBackward();
        int newPos = newCarState.getPosition();

        int deltaPos = orgPos - newPos;
        assertEquals(1, deltaPos);
    }

    @Test
    public void searchForParkingSpace(){
        
    }

    // TODO
    @Test
    public void hasParked(){
        int orgPosition = car.getCarState().getPosition();
        int deltaPos = orgPosition - 5;
        
        car.Park();
        assertEquals(car.getCarState().getPosition(), deltaPos);
        assertEquals(true, car.getCarState().getIsParked());

    }

    // @Test
    // public void carUnpark(){
    //     car.UnPark();
    //     CarState carState = car.getCarState();
    //     assertEquals(carState.getPosition())
    // }
}