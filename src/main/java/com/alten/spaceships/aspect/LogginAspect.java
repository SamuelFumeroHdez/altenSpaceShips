package com.alten.spaceships.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LogginAspect {
    private static final Logger logger = LoggerFactory.getLogger(LogginAspect.class);

    @Before("execution(* com.alten.spaceships.controller.SpaceShipController.getSpaceShip(Long)) && args(id)")
    public void logBeforeIfIdIsNegative(Long id){
        if(id<0){
            logger.warn("Attempted to get a spaceship with a negative ID: {}", id);
        }
    }
}
