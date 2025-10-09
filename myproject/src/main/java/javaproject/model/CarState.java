package javaproject.model;

import java.util.ArrayList;

public class CarState {
    private int position;
    private boolean isParked;
    // private int freeParkingSpaceIndex;
    private int freeParkingSpaceCounter;
    private int ROAD_LENGTH = 500;

    private ArrayList<Integer> freeParkingSpaceIndexList;
    private ArrayList<Integer> freeParkingSpaceSizeList;

    public CarState() {
        this.position = 0;
        this.isParked = false;
        // freeParkingSpaceIndex = -1;
        freeParkingSpaceCounter = 0;
        this.freeParkingSpaceIndexList = new ArrayList<Integer>();
        this.freeParkingSpaceSizeList = new ArrayList<Integer>();
    }

    // Getters

    public int getPosition() {
        return position;
    }

    public boolean getIsParked() {
        return isParked;
    }

    // public int getFreeParkingSpaceIndex() {
    //     return freeParkingSpaceIndex;
    // }

    public int getFreeParkingSpaceCounter() {
        return freeParkingSpaceCounter;
    }

    public int getRoadLength() {
        return ROAD_LENGTH;
    }

    public ArrayList<Integer> getFreeParkingSpaceIndexList() {
        return this.freeParkingSpaceIndexList;
    }

    public ArrayList<Integer> getfreeParkingSpaceSizeList() {
        return this.freeParkingSpaceSizeList;
    }

    // Setters

    public void setPosition(int position) {
        this.position = position;
    }

    public void setParked(boolean isParked) {
        this.isParked = isParked;
    }

    public void saveFreeParkingSpaceIndex(int freeParkingSpace) {
        this.freeParkingSpaceIndexList.add(freeParkingSpace);
        this.freeParkingSpaceSizeList.add(this.freeParkingSpaceCounter);
    }

    // public void setFreeParkingSpaceIndex(int freeParkingSpace) {
    // // old
    // // this.freeParkingSpaceIndex = freeParkingSpace;

    // // new
    // this.freeParkingSpaceIndexList.add(freeParkingSpace);
    // this.freeParkingSpaceSizeList.add(this.freeParkingSpaceCounter);
    // }

    public void setFreeParkingSpaceCounter(int freeParkingSpaceCounter) {
        this.freeParkingSpaceCounter = freeParkingSpaceCounter;
    }
    
}