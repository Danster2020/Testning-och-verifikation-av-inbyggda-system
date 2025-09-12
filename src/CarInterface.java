
public interface CarInterface {

    /* desc: Move the car forward by 1 increment by quering isEmpty(); 
    and returns current position of car in a struct and situation 
    of detected parking places up to now, cant move out of bounds*/
    /*Pre cond: Check if its parked or not cant move while parked and its current position*/
    /*Post cond: Checked if moved */
    /*test cases:  */
    public void MoveForward();

    /*desc: query the 2 sensors to check the distance until object on right */
    /*Pre cond:  */
    /*Post cond: */
    /*test cases:  */
    public boolean isEmpty();

    /*desc: */
    /*Pre cond:  */
    /*Post cond: */
    /*test cases:  */
    public void MoveBackward();

    /*desc: */
    /*Pre cond:  */
    /*Post cond: */
    /*test cases:  */
    public void Park();

    /*desc: */
    /*Pre cond:  */
    /*Post cond: */
    /*test cases:  */
    public void UnPark();

    /*desc: */
    /*Pre cond:  */
    /*Post cond: */
    /*test cases:  */
    public CarState WhereIs();

}