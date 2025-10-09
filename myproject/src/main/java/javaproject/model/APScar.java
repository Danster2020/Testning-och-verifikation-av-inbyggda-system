package javaproject.model;

import java.util.ArrayList;

import javaproject.controller.Actuator;
import javaproject.controller.Sensor;

public class APScar implements CarInterface {
    private CarState carState;
    private Sensor sensor;
    private Actuator actuator;

    // public boolean isEmptyReturn = false; // used for testing
    // ArrayList parkingMap;

    public APScar() {
        this.carState = new CarState();
        this.sensor = new Sensor();
        this.actuator = new Actuator();
        // parkingMap = new ArrayList<String>();
    }

    public CarState MoveForward() {

        CarState newCarstate = this.actuator.moveCar(carState, 1);
        // the parking index and counter updates
        if (isEmpty()) {
            carState.setFreeParkingSpaceCounter(carState.getFreeParkingSpaceCounter() + 1);
        } else {
            // if just passed a free parking space (5 free spots)
            if (carState.getFreeParkingSpaceCounter() >= 5) {
                carState.saveFreeParkingSpaceIndex(carState.getPosition());
            }
            carState.setFreeParkingSpaceCounter(0);
        }


        return newCarstate;
    }


    public boolean isEmpty() {
        // for future use
        int sensorDistance = this.sensor.getProcessedSensorData();
        int minimumFreeSpace = 100;

        if (sensorDistance < minimumFreeSpace) {
            return false;
        } else {
            return true;
        }

    }

    public CarState MoveBackward() {
        CarState newCarstate = this.actuator.moveCar(carState, -1);

        if (isEmpty()) {
            carState.setFreeParkingSpaceCounter(carState.getFreeParkingSpaceCounter() + 1);
        } else {
            // if just passed a free parking space (5 free spots)
            if (carState.getFreeParkingSpaceCounter() >= 5) {
                carState.saveFreeParkingSpaceIndex(carState.getPosition());
            }
            carState.setFreeParkingSpaceCounter(0);
        }

        return newCarstate;
    }

    private void parallelReverseParkingManeuver() {
        // auto park function that parkes at current park index when at correct index
        // called from park()
        for (int i = 0; i < 4; i++) {
            MoveBackward();
        }
        this.WhereIs().setParked(true);
        return;
    }

    private int getMostEfficientParkingIndex() {
        ArrayList<Integer> spaceIndexList = this.carState.getFreeParkingSpaceIndexList();
        ArrayList<Integer> spaceSizeList = this.carState.getfreeParkingSpaceSizeList();

        // if no parking spaces have been found
        if (spaceIndexList.size() == 0) {
            return -1;
        }

        int minFreeSpaceIndex = spaceIndexList.get(0);
        int minFreeSpaceSize = spaceSizeList.get(0);
        for (int i = 0; i < spaceIndexList.size(); i++) {
            int currSize = spaceSizeList.get(i);
            if (currSize <= minFreeSpaceSize) {
                minFreeSpaceSize = currSize;
                minFreeSpaceIndex = spaceIndexList.get(i);
            }
        }
        return minFreeSpaceIndex;
    }

    // park drift style
    public void Park() {
        // if car is already parked
        // test from carParkWhileParked()
        if (getCarState().getIsParked() == true) {
            return;
        }

        // scenario 1: park at latest found parking space.
        // test from parkAtLatestFoundParkingSpace()
        int carSpaceIndex = getMostEfficientParkingIndex();
        if (carSpaceIndex != -1) {
            while (WhereIs().getPosition() > carSpaceIndex) {
                MoveBackward();
            }
            parallelReverseParkingManeuver();
            return;
        }

        // scenario 2: find parking space and park.
        // test from searchForParkingSpaceAndPark()
        while (WhereIs().getFreeParkingSpaceCounter() < 5) {
            MoveForward();
            // if end of road and no parking space found
            if (WhereIs().getPosition() == 499) {
                return;
            }
        }
        parallelReverseParkingManeuver();

    }

    // unpark like a king
    public void UnPark() {
        // if car is already unparked
        // test from carUnParkWhileUnParked()
        if (WhereIs().getIsParked() == false) {
            return;
        }

        // car unparks while it is parked.
        // test from carUnparkWhileParked()
        this.WhereIs().setParked(false);
        // very important set 1
        this.WhereIs().setFreeParkingSpaceCounter(1);
        for (int i = 0; i < 4; i++) {
            MoveForward();
        }
    }

    // return both position and isPark state
    // from test carWhereIs()
    public CarState WhereIs() {
        // System.out.println(this.carState.getPosition());
        return this.carState;
    }

    public CarState getCarState() {
        return this.carState;
    }

    public Sensor getSensor() {
        return this.sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public Actuator getActuator() {
        return this.actuator;
    }

}
