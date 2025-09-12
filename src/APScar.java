
public class APScar implements CarInterface {
    CarState car;
    int sensor1; // range sensor from 0-200 for next empty space
    int sensor2;

    public APScar() {
        this.car = new CarState();
    }

    public void MoveForward() {
        // Move car by 1 meter max 500
        this.car.setPosition(car.getPosition() + 1);

        // check for isEmpty
        if (isEmpty()) {
            this.car.setFreeParkingSpace(car.getFreeParkingSpace() + 1);
        } else {
            this.car.setFreeParkingSpace(0);
        }
        // return struct with carState

    }

    public int[][] querySensor() {
        int[] s1 = { 143, 177, 187, 199, 201 };
        int[] s2 = { 176, 186, 187, 200, 201 };
        int[][] sDataArray = { s1, s2 };
        return sDataArray;
    }

    public int processSensorData() {
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
        for (int value: processedSensorData) {
            totalSum += value;
        }
        int sensorValue = totalSum / nrOfSensors;
        System.out.println(sensorValue);
        return sensorValue;
    }

    public boolean isEmpty() {
        // check the sensors
        int sensorDistance = processSensorData();
        int minimumFreeSpace = 3;

        if(sensorDistance < minimumFreeSpace){
            return false;
        }
        else{
            return true;
        }
        
    }

    public void MoveBackward() {
        if (this.car.getPosition() == 0) {

        } else {
            this.car.setPosition(car.getPosition() - 1);

            if (isEmpty()) {
                this.car.setFreeParkingSpace(car.getFreeParkingSpace() + 1);
            } else {
                this.car.setFreeParkingSpace(0);
            }
        }

    }

    // park drift style
    public void Park() {

    }

    // unpark like a king
    public void UnPark() {

    }

    // return both position and isPark state
    public CarState WhereIs() {
        return this.car.getCarState();
    }

    // public CarState getCarState()
    // {
    // return this.carState;
    // }

}
