import org.junit.*;

import static org.junit.Assert.assertEquals;

public class CarTest {

    APScar car;
    int ROAD_LENGTH = 500;

    @Before // before each test
    public void setUp() {
        this.car = new APScar();

        // pre conditions
        this.car.getCarState().setPosition(0);
        this.car.getCarState().setParked(false);
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
    public void carMovesForward() {
        int orgPos = car.getCarState().getPosition();
        car.MoveForward();

        int newPos = car.getCarState().getPosition();
        int deltaPos = newPos - orgPos;
        assertEquals(1, deltaPos);
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
    public void carMovesBackwardAtStartOfStreet() {
        car.MoveBackward();
        int newPos = car.getCarState().getPosition();
        assertEquals(0, newPos);
    }

    @Test
    public void searchForParkingSpace(){
        
    }

    // TODO
    // @Test
    // public void hasParked(){
    //     int orgPosition = car.getCarState().getPosition();
    //     int deltaPos = orgPosition - 4;
        
    //     car.Park();
    //     assertEquals(car.getCarState().getPosition(), deltaPos);
    //     assertEquals(true, car.getCarState().getIsParked());
    // }

    // @Test
    // public void carUnparkWhileParked(){
    //     car.getCarState().setParked(true);
    //     car.UnPark();
    //     CarState newCarState = car.getCarState();
    //     assertEquals(carState.getPosition());
    // }

    @Test
    public void carWhereIs(){
        CarState currentPos = car.WhereIs();
        
        int position = car.getCarState().getPosition();
        boolean parked = car.getCarState().getIsParked();
        int parkIndex = car.getCarState().getFreeParkingSpaceIndex();
        int parkCounter = car.getCarState().getFreeParkingSpaceCounter();

        assertEquals(currentPos.getPosition(), position);
        assertEquals(currentPos.getIsParked(), parked);
        assertEquals(currentPos.getFreeParkingSpaceCounter(), parkCounter);
        assertEquals(currentPos.getFreeParkingSpaceIndex(), parkIndex);
    }
}