
// LC-1279
// https://leetcode.com/problems/traffic-light-controlled-intersection/

// Input: cars = [1,3,5,2,4], directions = [2,1,2,4,3], arrivalTimes = [10,20,30,40,50]
//  Output: [
//  "Car 1 Has Passed Road A In Direction 2",    // Traffic light on road A is green, car 1 can cross the intersection.
//  "Car 3 Has Passed Road A In Direction 1",    // Car 3 crosses the intersection as the light is still green.
//  "Car 5 Has Passed Road A In Direction 2",    // Car 5 crosses the intersection as the light is still green.
//  "Traffic Light On Road B Is Green",          // Car 2 requests green light for road B.
//  "Car 2 Has Passed Road B In Direction 4",    // Car 2 crosses as the light is green on road B now.
//  "Car 4 Has Passed Road B In Direction 3"     // Car 4 crosses the intersection as the light is still green.
//  ]

// Input: cars = [1,2,3,4,5], directions = [2,4,3,3,1], arrivalTimes = [10,20,30,40,40]
//  Output: [
//  "Car 1 Has Passed Road A In Direction 2",    // Traffic light on road A is green, car 1 can cross the intersection.
//  "Traffic Light On Road B Is Green",          // Car 2 requests green light for road B.
//  "Car 2 Has Passed Road B In Direction 4",    // Car 2 crosses as the light is green on road B now.
//  "Car 3 Has Passed Road B In Direction 3",    // Car 3 crosses as the light is green on road B now.
//  "Traffic Light On Road A Is Green",          // Car 5 requests green light for road A.
//  "Car 5 Has Passed Road A In Direction 1",    // Car 5 crosses as the light is green on road A now.
//  "Traffic Light On Road B Is Green",          // Car 4 requests green light for road B. Car 4 blocked until car 5 crosses and then traffic light is green on road B.
//  "Car 4 Has Passed Road B In Direction 3"     // Car 4 crosses as the light is green on road B now.
//  ]

//  Explanation: This is a dead-lock free scenario.
//  Note that the scenario when car 4 crosses before turning light into green on road A
//  and allowing car 5 to pass is also correct and Accepted scenario.

// Constraints:
//
//  1 <= cars.length <= 20
//  cars.length = directions.length
//  cars.length = arrivalTimes.length
//  All values of cars are unique
//  1 <= directions[i] <= 4
//  arrivalTimes is non-decreasing

public class TrafficLightControlledIntersection {

  class TrafficLight {

    // Signal maintains the road which is green at the moment
    private final Signal signal;

    public TrafficLight() {
      signal = new Signal();
    }

    public void carArrived(
      int carId,           // ID of the car
      int roadId,          // ID of the road the car travels on. Can be 1 (road A) or 2 (road B)
      int direction,       // Direction of the car
      Runnable turnGreen,  // Use turnGreen.run() to turn light to green on current road
      Runnable crossCar    // Use crossCar.run() to make car cross the intersection
    ) {
      synchronized (signal) {
        if (signal.greenRoad != roadId) {
          turnGreen.run();
          signal.greenRoad = roadId;
        }
        crossCar.run();
      }
    }

    class Signal {

      // 1 corresponds to the Road A
      int greenRoad = 1;
    }
  }
}
