
package javaproject.controller;

import javaproject.model.SensorInterface;
import java.lang.Math;

public class Sensor implements SensorInterface {

    private int randomInt(int min, int max) {
        int range = max - min + 1;
        int randomInt = (int) (Math.random() * range) + min;
        return randomInt;
    }

    public int[][] querySensors() {
        // int[] s1 = { 143, 177, 187, 199, 184 };
        // int[] s2 = { 176, 186, 187, 200, 199 };
        int[] s1 = new int[5];
        int[] s2 = new int[5];
        for (int i = 0; i < 5; i++) {
            s1[i] = randomInt(0, 200);
            s2[i] = randomInt(0, 200);
        }
        int[][] sDataArray = { s1, s2 };
        return sDataArray;
    }

    // not in use phase 1
    public int getProcessedSensorData() {
        int[][] sensorData = querySensors();
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
}