package com.comsystem.homework.robot;


import com.comsystem.homework.model.RobotAction;
import com.comsystem.homework.model.RobotPlan;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class RobotOperations {

    /**
     * Number of robots is equivalent to the result of raising 2 (the base) to the power of the
     * number of cloning operations
     */
    public static final int BASE = 2;

    /**
     * An algorithm that converts a number of days into an action plan.
     *
     * @param days the number of days that the robot can work
     * @return The action plan <em>must maximize</em> the number of stones that the robot will dig.
     * In other words, this algorithm must try to achieve the highest value of
     * {@link RobotPlan#numberOfStones} possible for the number of provided days. The value of
     * {@link RobotPlan#numberOfDays} is equal to the input days parameter
     * @see RobotPlan
     */
    public RobotPlan excavateStonesForDays(int days) {
        List<RobotAction> robotActions = new ArrayList<>();
        int numberOfStones = 0;

        for (int day = 0; day < days; day++) {
            if (day == days - 1) {
                numberOfStones += getPowOfBase(day);
                robotActions.add(RobotAction.DIG);
            } else {
                robotActions.add(RobotAction.CLONE);
            }
        }

        return new RobotPlan(days, numberOfStones, robotActions);
    }

    /**
     * An algorithm that converts a number of stones into an action plan. Essentially this algorithm
     * is the inverse of {@link #excavateStonesForDays(int)}.
     *
     * @param numberOfStones the number of stones the robot has to collect
     * @return The action plan <em>must minimize</em> the number of days necessary for the robot to
     * dig the provided number of stones. In other words, this algorithm must try to achieve the
     * lowest value of {@link RobotPlan#numberOfDays} possible for the number of provided stones.
     * The value of {@link RobotPlan#numberOfStones} is equal to the numberOfStones parameter
     * @see RobotPlan
     * <p>
     * The method works as follows: It initializes a list of RobotAction objects and variables to
     * keep track of the initial number of stones and the number of days. It then enters a loop that
     * continues until all stones have been collected. In each iteration of the loop, it calculates
     * the highest exponent of the base that is contained in the current number of stones. It
     * subtracts the corresponding power of the base from the current number of stones, adds a DIG
     * action to the action list, and increments the number of days. If there are still stones left
     * to collect, it calculates the new highest exponent of the base and determines the number of
     * days to clone. It then adds the appropriate number of CLONE actions to the action list and
     * increments the number of days accordingly. Finally, it creates a new RobotPlan object with
     * the total number of days, the initial number of stones, and the reversed action list, and
     * returns this object.
     */
    public RobotPlan daysRequiredToCollectStones(int numberOfStones) {
        List<RobotAction> robotActions = new ArrayList<>();
        int initialNumberOfStones = numberOfStones;
        int highestExponent, oldHighestExponent, numberOfDays = 0;

        while (numberOfStones > 0) {
            highestExponent = getHighestExponentOfBase(numberOfStones);
            numberOfStones -= getPowOfBase(highestExponent);
            robotActions.add(RobotAction.DIG);
            numberOfDays++;

            if (numberOfStones >= 0) {
                oldHighestExponent = highestExponent;
                highestExponent = getHighestExponentOfBase(numberOfStones);

                int numberOfDaysToClone = oldHighestExponent - highestExponent;
                robotActions.addAll(Collections.nCopies(numberOfDaysToClone, RobotAction.CLONE));
                numberOfDays += numberOfDaysToClone;
            }
        }

        Collections.reverse(robotActions);
        return new RobotPlan(numberOfDays, initialNumberOfStones, robotActions);
    }

    /**
     * This method calculates the power of a constant BASE raised to the given highestExponent. It
     * uses the Math.pow function to perform the calculation and then casts the result to an
     * integer.
     */
    private int getPowOfBase(int highestExponent) {

        return (int) Math.pow(BASE, highestExponent);
    }

    /**
     * An algorithm that calculates the highest exponent of the base which is contained in the given
     * number of stones. It uses the mathematical formula for changing the base of a logarithm and
     * effectively rounds down the result by casting the double result to an int.
     */
    private int getHighestExponentOfBase(int numberOfStones) {
        if (numberOfStones <= 0) {
            return 0;
        }

        return (int) (Math.log(numberOfStones) / Math.log(BASE));
    }

}
