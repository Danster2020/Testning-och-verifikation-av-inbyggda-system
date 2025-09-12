
public class CarState {
    private int position;
    private boolean isParked;
    private int freeParkingSpace;

    public CarState(){
        position = 0;
        isParked = false;
        freeParkingSpace = 0;
    }

    public CarState getCarState() {
        return this;
    }

    public int getPosition() {
        return position;
    }

    public boolean getIsParked() {
        return isParked;
    }

    public int getFreeParkingSpace() {
        return freeParkingSpace;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setParked(boolean isParked) {
        this.isParked = isParked;
    }

    public void setFreeParkingSpace(int freeParkingSpace) {
        this.freeParkingSpace = freeParkingSpace;
    }
    
}