
package javaproject.model;

public interface SensorInterface {

    // range sensor from 0-200 for next empty space
    public int[][] querySensors();

    public int getProcessedSensorData();
}