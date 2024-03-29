package com.comsystem.homework.rest;

import com.comsystem.homework.constraint.PositiveNumber;
import com.comsystem.homework.model.RobotPlan;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.comsystem.homework.service.RobotOperations;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/robot/operation")
public final class RobotRestController {

    private final RobotOperations robotOperations;

    /**
     * This method exposes the functionality of {@link RobotOperations#excavateStonesForDays(int)}
     * via HTTP
     */
    @PostMapping("/excavation")
    public ResponseEntity<RobotPlan> excavateStones(
        @Valid @PositiveNumber @RequestParam Integer numberOfDays) {

        return ResponseEntity.ok(robotOperations.excavateStonesForDays(numberOfDays));
    }

    /**
     * This method exposes the functionality of
     * {@link RobotOperations#daysRequiredToCollectStones(int)} via HTTP
     */
    @PostMapping("/approximation")
    public ResponseEntity<RobotPlan> approximateDays(
        @Valid @PositiveNumber @RequestParam Integer numberOfStones) {

        return ResponseEntity.ok(robotOperations.daysRequiredToCollectStones(numberOfStones));
    }

}
