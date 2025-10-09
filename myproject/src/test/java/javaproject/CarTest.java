package javaproject;

import org.junit.*;
import org.junit.runner.RunWith;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.ArrayList;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javaproject.controller.Sensor;
import javaproject.model.APScar;
import javaproject.model.CarState;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class CarTest {
    APScar car;
    APScar mockedCar = mock(APScar.class);
    // APScar spyCar = spy(this.car);
    int ROAD_LENGTH = 500;

    @Before // before each test
    public void setUp() {
        this.car = new APScar();

        // pre conditions
        this.car.getCarState().setPosition(0);
        this.car.getCarState().setParked(false);
    }

    @Test
    public void sanity() {
        assertNotNull(car);
    }

    @Test
    public void IntegrationTestStartUnparked() {
        // start at beginning of road
        
        // scan for free parking space with spy map and car
        APScar spyCar = spy(this.car);
        when(spyCar.isEmpty()).thenReturn(
            false,
            true,
            true,
            true,
            true,
            true,
            true,
            false,
            false,
            false,
            true,
            true,
            true,
            true,
            true,
            false,
            false,
            true,
            false, 
            false,
            true,
            true,
            true,
            true,
            true,
            false,
            false,
            false,
            false,
            false
        );

        // park 
        // move back until most efficient parking space
        spyCar.Park();

        // unpark and drive to end of street
        spyCar.UnPark();
        int currentPOS = spyCar.WhereIs().getPosition();
        while(currentPOS < 499){
            spyCar.MoveForward();
            currentPOS = spyCar.WhereIs().getPosition();
            
        }
        assertEquals(499, spyCar.WhereIs().getPosition());
    }

    // @Test
    // public void randomTest() {
    // when(this.mockedCar.isEmpty()).thenReturn(false);
    // }

    @Test
    public void isNotParked() {
        boolean isParked = car.WhereIs().getIsParked();
        assertEquals(false, isParked);
    }

    @Test
    public void isNotEmpty() {
        APScar spyCar = spy(this.car);
        when(spyCar.isEmpty()).thenReturn(false);
        boolean isEmpty = spyCar.isEmpty();
        assertEquals(false, isEmpty);
    }

    @Test
    public void isEmpty() {
        APScar spyCar = spy(this.car);
        when(spyCar.isEmpty()).thenReturn(true);
        boolean isEmpty = spyCar.isEmpty();
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
        APScar spyCar = spy(this.car);
        when(spyCar.isEmpty()).thenReturn(false);

        // start somewhere on road
        spyCar.getCarState().setPosition(15);

        // fake a saved parking space and park
        spyCar.getCarState().setFreeParkingSpaceCounter(5);
        spyCar.getCarState().saveFreeParkingSpaceIndex(10);
        spyCar.Park();

        assertEquals(6, spyCar.getCarState().getPosition());
        assertEquals(true, spyCar.getCarState().getIsParked());
    }

    @Test
    public void searchForParkingSpaceAndPark() {
        APScar spyCar = spy(this.car);
        when(spyCar.isEmpty()).thenReturn(true);
        spyCar.Park();
        assertEquals(1, spyCar.getCarState().getPosition());
        assertEquals(true, spyCar.getCarState().getIsParked());
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
        APScar spyCar = spy(this.car);
        when(spyCar.isEmpty()).thenReturn(true);
        spyCar.getCarState().setParked(true);
        int originalPos = spyCar.getCarState().getPosition();
        spyCar.UnPark();
        assertEquals(false, spyCar.getCarState().getIsParked());
        assertEquals(originalPos + 4, spyCar.getCarState().getPosition());
    }

    @Test
    public void carWhereIs() {
        CarState currentPos = car.WhereIs();

        int position = car.getCarState().getPosition();
        boolean parked = car.getCarState().getIsParked();
        // int parkIndex = car.getCarState().getFreeParkingSpaceIndex();
        int parkCounter = car.getCarState().getFreeParkingSpaceCounter();
        ArrayList<Integer> indexParkList = car.getCarState().getFreeParkingSpaceIndexList();
        ArrayList<Integer> indexParkListSize = car.getCarState().getfreeParkingSpaceSizeList();

        assertEquals(position, currentPos.getPosition());
        assertEquals(parked, currentPos.getIsParked());
        assertEquals(parkCounter, currentPos.getFreeParkingSpaceCounter());
        // assertEquals(parkIndex, currentPos.getFreeParkingSpaceIndex());
        assertEquals(indexParkList, currentPos.getFreeParkingSpaceIndexList());
        assertEquals(indexParkListSize, currentPos.getfreeParkingSpaceSizeList());
    }

    // phase 2 TDD
    @Test
    public void sensorsReturnValidData() {
        int[][] sensorData = this.car.getSensor().querySensors();
        for (int i = 0; i < sensorData.length; i++) {
            for (int j = 0; j < sensorData[0].length; j++) {
                int data = sensorData[i][j];
                assertTrue(data >= 0 && data <= 200);
            }
        }
    }

    @Test
    public void sensorDataIsProcessed() {
        APScar spyCar = spy(this.car);
        int mockedSensorData[][] = {
                { 100, 100, 100, 100, 100 },
                { 200, 200, 200, 200, 200 }
        };

        Sensor spySensor = spy(car.getSensor());
        car.setSensor(spySensor);
        doReturn(mockedSensorData).when(spySensor).querySensors(); // stub

        int result = car.getSensor().getProcessedSensorData();
        assertEquals(150, result);
    }

    // TODO test with failing sensor halfway trough scenario. Add a scenario where we feed it
    // sensordata by mocking querySensors() method and feed it sensordata through a file.


    @Test
    public void parkNoSpaceAvailable(){
        APScar spyCar = spy(this.car);
        when(spyCar.isEmpty()).thenReturn(false);
        spyCar.Park();
        assertTrue(spyCar.WhereIs().getPosition() == 499);

    }

    @Test
    public void testActuatorOutOfBounds(){
        APScar spyCar = spy(this.car);
        int currentPOS = spyCar.WhereIs().getPosition();
        spyCar.getActuator().moveCar(spyCar.WhereIs(), -2);
        assertEquals(currentPOS, spyCar.WhereIs().getPosition());
    }
}