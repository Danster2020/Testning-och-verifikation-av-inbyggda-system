
public interface CarInterface {

    /* desc: Move the car forward by 1 increment by quering isEmpty(); 
    and returns current position of car in a struct and situation 
    of detected parking places up to now, cant move out of bounds*/
    /*Pre cond: Check if its parked or not cant move while parked and its current position*/
    /*Post cond: Checked if moved */
    /*test cases:  */
    public void MoveForward();

    public boolean isEmpty();

    public void MoveBackward();

    public void Park();

    public void UnPark();

    public void WhereIs();

}