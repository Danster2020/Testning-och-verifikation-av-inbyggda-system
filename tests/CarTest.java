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

    // tests that the default state of the car is in an unparked state.
    @Test
    public void isNotParked() {
        boolean isParked = car.WhereIs().getIsParked();
        assertEquals(false, isParked);
    }

    // tests that the isEmpty method returns false when mocked as false.
    @Test
    public void isNotEmpty() {
        car.isEmptyReturn = false;
        boolean isEmpty = this.car.isEmpty();
        assertEquals(false, isEmpty);
    }

    // tests that the isEmpty method returns true when mocked as true.
    @Test
    public void isEmpty() {
        car.isEmptyReturn = true;
        boolean isEmpty = this.car.isEmpty();
        assertEquals(true, isEmpty);
    }

    // tests that the car can move forward and that its position
    // updades accordingly.
    @Test
    public void carMovesForward() {
        int orgPos = car.getCarState().getPosition();
        car.MoveForward();

        int newPos = car.getCarState().getPosition();
        int deltaPos = newPos - orgPos;
        assertEquals(1, deltaPos);
    }

    // test that the car cant move forward while parked.
    @Test
    public void carMovesForwardWhileParked() {
        car.getCarState().setParked(true);
        int orgPos = car.getCarState().getPosition();
        car.MoveForward();

        assertEquals(orgPos, car.getCarState().getPosition());
    }

    // tests that the car doesnt move forward at end of road
    @Test
    public void carMovesForwardAtEndOfRoad() {
        int posEndOfRoad = ROAD_LENGTH - 1;
        car.getCarState().setPosition(posEndOfRoad); // 499 is end of road
        int orgPos = car.getCarState().getPosition();
        car.MoveForward();

        int newPos = car.getCarState().getPosition();
        assertEquals(orgPos, newPos);
    }

    // tests that the car can move backward and that its position
    // updades accordingly.
    @Test
    public void carMovesBackward() {
        car.getCarState().setPosition(1);
        int orgPos = car.getCarState().getPosition();
        CarState newCarState = car.MoveBackward();
        int newPos = newCarState.getPosition();

        int deltaPos = orgPos - newPos;
        assertEquals(1, deltaPos);
    }

    // tests that the car doesnt move backward at start of road
    @Test
    public void carMovesBackwardAtStartOfStreet() {
        car.MoveBackward();
        int newPos = car.getCarState().getPosition();
        assertEquals(0, newPos);
    }

    // tests that the car cant move backward while parked
    @Test
    public void carMovesBackwardWhileParked() {
        car.getCarState().setParked(true);
        car.getCarState().setPosition(2); // prevents start of road from triggering
        int orgPos = car.getCarState().getPosition();
        car.MoveBackward();

        assertEquals(orgPos, car.getCarState().getPosition());
    }

    // tests that the car can park at the latest found parking space
    // and that it parks in the right index.
    @Test
    public void parkAtLatestFoundParkingSpace() {
        // assume start somewhere on road
        car.isEmptyReturn = false;
        car.getCarState().setFreeParkingSpaceIndex(10);
        car.getCarState().setPosition(15);
        car.Park();

        assertEquals(6, car.getCarState().getPosition());
        assertEquals(true, car.getCarState().getIsParked());
    }

    // tests that the car can search for a parking space while
    // traversing the street and then park.
    @Test
    public void searchForParkingSpaceAndPark() {
        car.isEmptyReturn = true;
        car.Park();
        assertEquals(1, car.getCarState().getPosition());
        assertEquals(true, car.getCarState().getIsParked());
    }

    // tests that the park cant park while already parked
    @Test
    public void carParkWhileParked() {
        car.getCarState().setParked(true);
        int orgPos = car.getCarState().getPosition();
        car.Park();
        // should not have moved and stayed as parked
        assertEquals(true, car.getCarState().getIsParked());
        assertEquals(orgPos, car.getCarState().getPosition());
    }

    // tests that the park cant unpark while already unparked
    @Test
    public void carUnParkWhileUnParked() {
        int orgPos = car.getCarState().getPosition();
        car.UnPark();
        assertEquals(false, car.getCarState().getIsParked());
        assertEquals(orgPos, car.getCarState().getPosition());
    }

    // tests that the car can unpark while parked.
    @Test
    public void carUnparkWhileParked() {
        car.isEmptyReturn = true;
        car.getCarState().setParked(true);
        int originalPos = car.getCarState().getPosition();
        car.UnPark();
        assertEquals(false, car.getCarState().getIsParked());
        assertEquals(originalPos + 4, car.getCarState().getPosition());
    }

    // tests that the carWhereIs method returns the cars status properties
    // correctly.
    @Test
    public void carWhereIs() {
        CarState currentPos = car.WhereIs();

        int position = car.getCarState().getPosition();
        boolean parked = car.getCarState().getIsParked();
        int parkIndex = car.getCarState().getFreeParkingSpaceIndex();
        int parkCounter = car.getCarState().getFreeParkingSpaceCounter();

        assertEquals(position, currentPos.getPosition());
        assertEquals(parked, currentPos.getIsParked());
        assertEquals(parkCounter, currentPos.getFreeParkingSpaceCounter());
        assertEquals(parkIndex, currentPos.getFreeParkingSpaceIndex());
    }
}