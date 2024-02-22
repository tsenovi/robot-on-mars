package com.comsystem.homework.robot;

import static org.junit.jupiter.api.Assertions.*;

import com.comsystem.homework.model.RobotAction;
import com.comsystem.homework.model.RobotPlan;
import org.junit.jupiter.api.Test;

class RobotOperationsTest {

    private final RobotOperations robotOperations = new RobotOperations();

    @Test
    public void testExcavateStonesForDays() {
        int totalDays = 7;
        int cloneDays = totalDays - 1;
        RobotPlan plan = robotOperations.excavateStonesForDays(totalDays);

        assertEquals(totalDays, plan.numberOfDays());
        assertEquals((int) Math.pow(RobotOperations.BASE, (cloneDays)), plan.numberOfStones());
        assertEquals(cloneDays,
            plan.robotActions().stream().filter(action -> action == RobotAction.CLONE).count());
        assertTrue(plan.robotActions().contains(RobotAction.DIG));
        assertEquals(RobotAction.DIG, plan.robotActions().getLast());
    }

    @Test
    public void testDaysRequiredToCollectStones() {
        int stones = 200;
        RobotPlan plan = robotOperations.daysRequiredToCollectStones(stones);

        assertEquals(stones, plan.numberOfStones());
        assertEquals(plan.robotActions().size(), plan.numberOfDays());
        assertTrue(plan.robotActions().contains(RobotAction.DIG));
        assertTrue(plan.robotActions().contains(RobotAction.CLONE));
    }

}