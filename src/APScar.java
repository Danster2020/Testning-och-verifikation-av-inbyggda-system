
public class APScar implements CarInterface {
    CarState carState;
    int sensor; // range sensor from 0-200 for next empty space

    public APScar() {
        CarState car = new CarState();
    }

    public void MoveForward() {

    }

    public boolean isEmpty() {
        return false;

    }

    public void MoveBackward() {

    }

    // park drift style
    public void Park() {

    }

    // unpark like a king
    public void UnPark() {

    }

    // return both position and isPark state
    public void WhereIs() {

    }

    public CarState getCarState()
    {
        return this.carState;
    }

}
