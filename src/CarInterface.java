
public interface CarInterface {

    /* desc: Move the car forward by 1 and check isEmpty(); 
    returns carState struct of detected parking places up 
    to now*/
    /*Pre cond: assume its on the road unparked */
    /*Post cond: moved forward by 1 meter*/
    /*test cases:  */
    public CarState MoveForward();

    /*desc: query the 2 sensors to check the distance until object on right */
    /*Pre cond:  inputs 5 sensor values*/
    /*Post cond: calculates if there is empty space on right*/
    /*test cases:  */
    public boolean isEmpty();

    /*desc: Move car backwards by 1 and check isEmpty()
    returns carState struct of detected parking places up to now */
    /*Pre cond:  assume its on the road unparked*/
    /*Post cond: move backwards by 1 meter */
    /*test cases:  */
    public CarState MoveBackward();

    /*desc: go park at saved parking index furthest back, otherwise look for one*/
    /*Pre cond:  not parked and look if preexisting park index*/
    /*Post cond: parked car */
    /*test cases:  */
    public void Park();

    /*desc: unparks the car from a parking space and moves to the end of that stretch*/
    /*Pre cond:  check if parked */
    /*Post cond: unparked at end of parking stretch*/
    /*test cases:  */
    public void UnPark();

    /*desc: get state of where car is*/
    /*Pre cond:  any state */
    /*Post cond: receives carState */
    /*test cases:  */
    public CarState WhereIs();

}