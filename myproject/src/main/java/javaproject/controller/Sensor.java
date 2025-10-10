
package javaproject.controller;

import javaproject.model.SensorInterface;
import java.lang.Math;
import java.util.Arrays;
import java.util.Random;

public class Sensor implements SensorInterface {

    // generates values with a low std to get in bound values
    private int randomInt(int min, int max) {
        Random r = new Random();
        double n = r.nextGaussian(0.5, 0.1);
        int range = max - min + 1;
        int randomInt = (int) (n * range) + min;
        return randomInt;
    }

    public int[][] querySensors() {
        int[] s1 = new int[5];
        int[] s2 = new int[5];
        for (int i = 0; i < 5; i++) {
            s1[i] = randomInt(0, 200);
            s2[i] = randomInt(0, 200);
        }
        int[][] sDataArray = { s1, s2 };
        // System.out.println("sDataArray " + Arrays.toString(sDataArray[0]));
        return sDataArray;
    }

    private int getStandardDeviation(int[] dataArray) {
        int sum = 0;
        for (int value : dataArray) {
            sum += value;
        }

        int dataArrayLength = dataArray.length;
        int mean = sum / dataArrayLength;

        int standardDeviation = 0;
        for (int num : dataArray) {
            standardDeviation += Math.pow(num - mean, 2);
        }

        return (int) Math.sqrt(standardDeviation / dataArrayLength);
    }

    // Tested through sensorsReturnValidData(), sensorDataIsProcessed()
    public int getProcessedSensorData() {
        int[][] sensorData = querySensors();
        int nrOfSensors = sensorData.length;
        int[] processedSensorData = new int[nrOfSensors];

        // for every sensor
        for (int sensor = 0; sensor < sensorData.length; sensor++) {
            int valueSum = 0;
            int nrOfValues = sensorData[sensor].length;
            
            // for every sensor value
            for (int value = 0; value < nrOfValues; value++) {
                valueSum += sensorData[sensor][value];
            }
            int valueAvg = valueSum / nrOfValues;
            processedSensorData[sensor] = valueAvg;

            // check if sensor is broken
            int standardDev = this.getStandardDeviation(sensorData[sensor]);
            if (standardDev > 50) {
                // if sensor is broken ovveride the value with 0 and ignore it in avg calculation
                processedSensorData[sensor] = 0;
                nrOfSensors--;
            }
        }

        // if no sensors are working
        if (nrOfSensors == 0) {
            throw new IllegalStateException("All sensors are broken!");
        }

        int totalSum = 0;
        for (int value : processedSensorData) {
            totalSum += value;
        }
        int sensorValue = totalSum / nrOfSensors;
        System.out.println(sensorValue);
        return sensorValue;
    }

    // public static void main(String[] args) {
    //     Sensor sensor = new Sensor();
    //     // int[][] sDataArray = { {150, 150, 150, 150, 150}, {150, 150, 150, 150, 150} };
    //     int[] sDataArray = {20, 130, 43, 160, 83};
    //     System.out.println(sensor.getStandardDeviation(sDataArray));
    // }

}