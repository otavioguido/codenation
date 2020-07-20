package com.challenge.endpoints;

import com.challenge.entity.Challenge;
import com.challenge.service.interfaces.ChallengeServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/challenge")
public class ChallengeController {

    @Autowired
    private ChallengeServiceInterface challengeServiceInterface;

    @GetMapping
    public List<Challenge> findByAll(
            @RequestParam(name = "accelerationId") Long accelerationId,
            @RequestParam(name = "userId") Long userId){
        return challengeServiceInterface.findByAccelerationIdAndUserId(accelerationId, userId);
    }
}
