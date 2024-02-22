package com.comsystem.homework.robot;


import com.comsystem.homework.model.RobotAction;
import com.comsystem.homework.model.RobotPlan;
import java.util.ArrayList;
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
        int lastDay = days - 1;

        for (int day = 0; day < days; day++) {
            if (day == lastDay) {
                numberOfStones += (int) Math.pow(BASE, day);
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
     */
    public RobotPlan daysRequiredToCollectStones(int numberOfStones) {
        List<RobotAction> robotActions = new ArrayList<>();
        int initialNumberOfStones = numberOfStones;
        int numberOfTotalDays = 0;
        int oldExponent = 0;

        while (numberOfStones > 0) {
            int currentExponent = getCurrentExponent(numberOfStones);
            numberOfStones -= (int) Math.pow(BASE, currentExponent);
            robotActions.add(RobotAction.DIG);
            numberOfTotalDays++;

            if (numberOfStones >= 0) {
                oldExponent = currentExponent;
                currentExponent = getCurrentExponent(numberOfStones);

                int numberOfCloneDays = oldExponent - currentExponent;
                for (int i = 0; i < numberOfCloneDays; i++) {
                    robotActions.add(RobotAction.CLONE);
                    numberOfTotalDays++;
                }
            }
        }

        return new RobotPlan(numberOfTotalDays, initialNumberOfStones, robotActions.reversed());
    }

    private int getCurrentExponent(int numberOfStones) {
        if (numberOfStones == 0) {
            return 0;
        }

        return (int) (Math.log(numberOfStones) / Math.log(BASE));
    }

}
