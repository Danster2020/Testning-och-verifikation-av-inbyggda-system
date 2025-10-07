
package javaproject.controller;

import javaproject.model.APScar;
import javaproject.model.ActuatorInterface;
import javaproject.model.CarState;

public class Actuator implements ActuatorInterface{
    int ROAD_LENGTH = 500;

    public CarState moveCar(APScar car, int carCommand){
        if(carCommand == 1){
            // do not move forward if parked
            //from test carMovesForwardWhileParked()
            if (car.WhereIs().getIsParked() == true) {
                return car.WhereIs();
            }

            // do not move forward if end of road
            //test from carMovesForwardAtEndOfRoad()
            int currPos = car.WhereIs().getPosition();
            if (currPos == ROAD_LENGTH - 1) {
                return car.WhereIs();
            }

            // Move car forward 1 meter, max 500, update parking pace index and parking space counter
            //test from carMovesForward()
            car.WhereIs().setPosition(car.WhereIs().getPosition() + 1);

            //the parking index and counter updates
            if (car.isEmpty()) {
                car.WhereIs().setFreeParkingSpaceCounter(car.WhereIs().getFreeParkingSpaceCounter() + 1);
            } else {
                car.WhereIs().setFreeParkingSpaceCounter(0);
            }

            if (car.WhereIs().getFreeParkingSpaceCounter() >= 5) {
                car.WhereIs().setFreeParkingSpaceIndex(car.WhereIs().getPosition());
            }            
        }
        else if(carCommand == -1){
            // do not move backward if parked
            //from test carMovesBackwardWhileParked()
            if (car.WhereIs().getIsParked() == true) {
                return car.WhereIs();
            }

            // if start of street do not move backwards 
            //test from carMovesBackwardAtStartOfStreet()
            if (car.WhereIs().getPosition() == 0) {
                return car.WhereIs();
            }   

            // Move car back 1 meter, max 500, update parking pace index and parking space counter
            //test from carMovesBackward()
            car.WhereIs().setPosition(car.WhereIs().getPosition() - 1);

            //the parking index and counter updates
            if (car.isEmpty()) {
                car.WhereIs().setFreeParkingSpaceCounter(car.WhereIs().getFreeParkingSpaceCounter() + 1);
            } else {
                car.WhereIs().setFreeParkingSpaceCounter(0);
            }

            if (car.WhereIs().getFreeParkingSpaceCounter() >= 5) {
                car.WhereIs().setFreeParkingSpaceIndex(car.WhereIs().getPosition());
            }
        }
        
        return car.WhereIs();
    }

    
}