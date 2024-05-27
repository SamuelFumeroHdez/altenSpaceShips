package com.alten.spaceships.entity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class SpaceShipTest {

    @Test
    public void givenSpaceShipWithValidFields_whenGettingFields_thenCorrectValues() {
        SpaceShip spaceShip = new SpaceShip();
        spaceShip.setName("Millennium Falcon");
        spaceShip.setSerie("Star Wars");

        assertEquals("Millennium Falcon", spaceShip.getName());
        assertEquals("Star Wars", spaceShip.getSerie());
    }
}
