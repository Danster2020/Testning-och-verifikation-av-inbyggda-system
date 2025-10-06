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
        car.isEmptyReturn = false;
        boolean isEmpty = this.car.isEmpty();
        assertEquals(false, isEmpty);
    }

    @Test
    public void isEmpty() {
        car.isEmptyReturn = true;
        boolean isEmpty = this.car.isEmpty();
        assertEquals(true, isEmpty);
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
    public void carMovesForwardWhileParked() {
        car.getCarState().setParked(true);
        int orgPos = car.getCarState().getPosition();
        car.MoveForward();

        assertEquals(orgPos, car.getCarState().getPosition());
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
    public void carMovesBackwardWhileParked() {
        car.getCarState().setParked(true);
        car.getCarState().setPosition(2); // prevents start of road from triggering
        int orgPos = car.getCarState().getPosition();
        car.MoveBackward();

        assertEquals(orgPos, car.getCarState().getPosition());
    }

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

    @Test
    public void searchForParkingSpaceAndPark() {
        car.isEmptyReturn = true;
        car.Park();
        assertEquals(1, car.getCarState().getPosition());
        assertEquals(true, car.getCarState().getIsParked());
    }

    @Test
    public void carParkWhileParked() {
        car.getCarState().setParked(true);
        int orgPos = car.getCarState().getPosition();
        car.Park();
        // should not have moved and stayed as parked
        assertEquals(true, car.getCarState().getIsParked());
        assertEquals(orgPos, car.getCarState().getPosition());
    }

    @Test
    public void carUnParkWhileUnParked() {
        int orgPos = car.getCarState().getPosition();
        car.UnPark();
        assertEquals(false, car.getCarState().getIsParked());
        assertEquals(orgPos, car.getCarState().getPosition());
    }

    @Test
    public void carUnparkWhileParked() {
        car.isEmptyReturn = true;
        car.getCarState().setParked(true);
        int originalPos = car.getCarState().getPosition();
        car.UnPark();
        assertEquals(false, car.getCarState().getIsParked());
        assertEquals(originalPos + 4, car.getCarState().getPosition());
    }

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