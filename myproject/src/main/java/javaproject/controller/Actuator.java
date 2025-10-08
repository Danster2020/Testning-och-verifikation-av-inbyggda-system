
package javaproject.controller;

import javaproject.model.APScar;
import javaproject.model.ActuatorInterface;
import javaproject.model.CarState;

public class Actuator implements ActuatorInterface{

    public CarState moveCar(CarState carState, int carCommand){
        if(carCommand == 1){
            // do not move forward if parked
            //from test carMovesForwardWhileParked()
            if (carState.getIsParked() == true) {
                return carState;
            }

            // do not move forward if end of road
            //test from carMovesForwardAtEndOfRoad()
            int currPos = carState.getPosition();
            if (currPos == carState.getRoadLength() - 1) {
                return carState;
            }

            // Move car forward 1 meter, max 500, update parking pace index and parking space counter
            //test from carMovesForward()
            carState.setPosition(carState.getPosition() + 1);

        }
        else if(carCommand == -1){
            // do not move backward if parked
            //from test carMovesBackwardWhileParked()
            if (carState.getIsParked() == true) {
                return carState;
            }

            // if start of street do not move backwards 
            //test from carMovesBackwardAtStartOfStreet()
            if (carState.getPosition() == 0) {
                return carState;
            }   

            // Move car back 1 meter, max 500, update parking pace index and parking space counter
            //test from carMovesBackward()
            carState.setPosition(carState.getPosition() - 1);
        }
        
        return carState;
    }

    
}