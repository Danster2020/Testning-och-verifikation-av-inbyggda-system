package javaproject.model;

public class CarState {
    private int position;
    private boolean isParked;
    private int freeParkingSpaceIndex;
    private int freeParkingSpaceCounter;

    public CarState(){
        position = 0;
        isParked = false;
        freeParkingSpaceIndex = -1;
        freeParkingSpaceCounter = 0;
    }

    // Getters
    
    public int getPosition() {
        return position;
    }

    public boolean getIsParked() {
        return isParked;
    }

    public int getFreeParkingSpaceIndex() {
        return freeParkingSpaceIndex;
    }

    public int getFreeParkingSpaceCounter() {
        return freeParkingSpaceCounter;
    }
    
    // Setters

    public void setPosition(int position) {
        this.position = position;
    }

    public void setParked(boolean isParked) {
        this.isParked = isParked;
    }

    public void setFreeParkingSpaceIndex(int freeParkingSpace) {
        this.freeParkingSpaceIndex = freeParkingSpace;
    }

    public void setFreeParkingSpaceCounter(int freeParkingSpaceCounter) {
        this.freeParkingSpaceCounter = freeParkingSpaceCounter;
    }
}