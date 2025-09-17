
public class APScar implements CarInterface {
    private CarState carState;
    int ROAD_LENGTH = 500;

    public APScar() {
        this.carState = new CarState();
    }

    public CarState MoveForward() {
        int currPos = this.carState.getPosition();

        // do not move forward if end of road
        if (currPos == ROAD_LENGTH - 1) {
            return this.carState;
        }

        // Move car by 1 meter max 500
        this.carState.setPosition(carState.getPosition() + 1);

        // check for isEmpty
        if (isEmpty()) {
            this.carState.setFreeParkingSpaceCounter(carState.getFreeParkingSpaceCounter() + 1);
        } else {
            this.carState.setFreeParkingSpaceCounter(0);
        }

        if (carState.getFreeParkingSpaceCounter() >= 5) {
            this.carState.setFreeParkingSpaceIndex(carState.getPosition());
        }

        return this.carState;
    }

    // range sensor from 0-200 for next empty space
    private int[][] querySensor() {
        int[] s1 = { 143, 177, 187, 199, 184 };
        int[] s2 = { 176, 186, 187, 200, 199 };
        int[][] sDataArray = { s1, s2 };
        return sDataArray;
    }

    private int processSensorData() {
        int[][] sensorData = querySensor();
        int nrOfSensors = sensorData.length;
        int[] processedSensorData = new int[nrOfSensors];

        for (int sensor = 0; sensor < nrOfSensors; sensor++) {
            int valueSum = 0;
            int nrOfValues = sensorData[sensor].length;
            for (int value = 0; value < nrOfValues; value++) {
                valueSum += value;
            }
            int valueAvg = valueSum / nrOfValues;
            processedSensorData[sensor] = valueAvg;
        }

        int totalSum = 0;
        for (int value : processedSensorData) {
            totalSum += value;
        }
        int sensorValue = totalSum / nrOfSensors;
        System.out.println(sensorValue);
        return sensorValue;
    }

    public boolean isEmpty() {
        int sensorDistance = processSensorData();
        int minimumFreeSpace = 50;

        if (sensorDistance < minimumFreeSpace) {
            return false;
        } else {
            return true;
        }
    }

    public CarState MoveBackward() {
        // if start of street do not move backwards
        if (this.carState.getPosition() == 0) {
            return this.carState;
        }

        // Move car backwards by 1 meter
        this.carState.setPosition(carState.getPosition() - 1);

        // check for isEmpty
        if (isEmpty()) {
            this.carState.setFreeParkingSpaceCounter(carState.getFreeParkingSpaceCounter() + 1);
        } else {
            this.carState.setFreeParkingSpaceCounter(0);
        }

        if (carState.getFreeParkingSpaceCounter() >= 5) {
            this.carState.setFreeParkingSpaceIndex(carState.getPosition());
        }

        return this.carState;
    }

    // park drift style
    public void Park() {

        // scenario 1: find parking space and park.
        while (carState.getPosition() < ROAD_LENGTH) {
            MoveForward();
            if (carState.getFreeParkingSpaceCounter() >= 5) {
                // park
                for (int i = 0; i < 4; i++) {
                    MoveBackward();
                }
                this.carState.setParked(true);
                return;
            }
        }

        // scenario 2: park at latest found parking space.

    }

    // unpark like a king
    public void UnPark() {
        //very important set 1
        this.carState.setFreeParkingSpaceCounter(1);
        for (int i = 0; i < 4; i++) {
            MoveForward();
        }
        this.carState.setParked(false);
    }

    // return both position and isPark state
    public CarState WhereIs() {
        return this.carState;
    }

    public CarState getCarState() {
        return this.carState;

    }

}
