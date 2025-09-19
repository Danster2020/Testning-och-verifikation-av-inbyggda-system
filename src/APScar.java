
public class APScar implements CarInterface {
    private CarState carState;
    int ROAD_LENGTH = 500;
    boolean isEmptyReturn = false; // used for testing

    public APScar() {
        this.carState = new CarState();
    }

    public CarState MoveForward() {

        // do not move forward if parked
        if (WhereIs().getIsParked() == true) {
            return this.WhereIs();
        }

        int currPos = this.WhereIs().getPosition();

        // do not move forward if end of road
        if (currPos == ROAD_LENGTH - 1) {
            return this.WhereIs();
        }

        // Move car by 1 meter max 500
        this.WhereIs().setPosition(WhereIs().getPosition() + 1);

        // check for isEmpty
        if (isEmpty()) {
            this.WhereIs().setFreeParkingSpaceCounter(WhereIs().getFreeParkingSpaceCounter() + 1);
        } else {
            this.WhereIs().setFreeParkingSpaceCounter(0);
        }

        if (WhereIs().getFreeParkingSpaceCounter() >= 5) {
            this.WhereIs().setFreeParkingSpaceIndex(WhereIs().getPosition());
        }

        return this.WhereIs();
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
        // for future use
        // int sensorDistance = processSensorData();
        // int minimumFreeSpace = 100;

        // if (sensorDistance < minimumFreeSpace) {
        // return false;
        // } else {
        // return true;
        // }

        return this.isEmptyReturn;
    }

    public CarState MoveBackward() {
        // if start of street do not move backwards
        if (this.WhereIs().getPosition() == 0) {
            return this.WhereIs();
        }

        // Move car backwards by 1 meter
        this.WhereIs().setPosition(WhereIs().getPosition() - 1);

        // check for isEmpty
        if (isEmpty()) {
            this.WhereIs().setFreeParkingSpaceCounter(WhereIs().getFreeParkingSpaceCounter() + 1);
        } else {
            this.WhereIs().setFreeParkingSpaceCounter(0);
        }

        if (WhereIs().getFreeParkingSpaceCounter() >= 5) {
            this.WhereIs().setFreeParkingSpaceIndex(WhereIs().getPosition());
        }

        return this.WhereIs();
    }

    private void parallelReverseParkingManeuver() {
        isEmptyReturn = true;
        for (int i = 0; i < 4; i++) {
            MoveBackward();
        }
        this.WhereIs().setParked(true);
        return;
    }

    // park drift style
    public void Park() {

        // scenario 1: park at latest found parking space.
        int carSpaceIndex = WhereIs().getFreeParkingSpaceIndex();
        if (carSpaceIndex != -1 && carSpaceIndex < WhereIs().getPosition()) {
            while (WhereIs().getPosition() > carSpaceIndex) {
                MoveBackward();
            }
            parallelReverseParkingManeuver();
            return;
        }

        // scenario 2: find parking space and park.
        while (WhereIs().getPosition() < ROAD_LENGTH) {
            MoveForward();
            if (WhereIs().getFreeParkingSpaceCounter() >= 5) {
                // park
                parallelReverseParkingManeuver();
                return;
            }
        }

    }

    // unpark like a king
    public void UnPark() {
        // if car is already unparked
        if(WhereIs().getIsParked() == false){
            return;
        }
        this.WhereIs().setParked(false);
        // very important set 1
        this.WhereIs().setFreeParkingSpaceCounter(1);
        for (int i = 0; i < 4; i++) {
            MoveForward();
        }
    }

    // return both position and isPark state
    public CarState WhereIs() {
        return this.carState;
    }

    public CarState getCarState() {
        return this.carState;
    }

}
