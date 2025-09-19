
public interface CarInterface {

    /* desc: Move the car forward by 1 and check isEmpty(); 
    returns carState struct of detected parking places up 
    to now*/
    /*Pre cond: assume its on the road unparked */
    /*Post cond: moved forward by 1 meter*/
    /*test cases: beggining of road, end of road, somewhere in between, when parked */
    public CarState MoveForward();

    /*desc: query the 2 sensors to check the distance to objects on the right */
    /*Pre cond:  input values from sensors */
    /*Post cond: calculates if there is empty space on right*/
    /*test cases:  when parked, when not parked*/
    public boolean isEmpty();

    /*desc: Move car backwards by 1 and check isEmpty()
    returns carState struct of detected parking places up to now */
    /*Pre cond:  assume its on the road unparked*/
    /*Post cond: move backwards by 1 meter */
    /*test cases:  beggining of road, end of road, somewhere in between, when parked*/
    public CarState MoveBackward();

    /*desc: Park at latest saved parking strip index, otherwise look for one*/
    /*Pre cond:  not parked and preexisting parking strip exists or not parked and no preexisting
    car strip exists */
    /*Post cond: parked car */
    /*test cases:  beggining of road, end of road, somewhere in between, when parked*/
    public void Park();

    /*desc: unparks the car from a parking space and moves to the end of that stretch*/
    /*Pre cond:  check if parked */
    /*Post cond: unparked at end of parking stretch*/
    /*test cases:  when not parked, when parked*/
    public void UnPark();

    /*desc: get state of where car is*/
    /*Pre cond:  any state */
    /*Post cond: receives carState */
    /*test cases:  when any state*/
    public CarState WhereIs();

}