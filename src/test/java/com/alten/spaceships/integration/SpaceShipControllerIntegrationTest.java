package com.alten.spaceships.integration;

import com.alten.spaceships.SpaceshipsApplication;
import com.alten.spaceships.controller.SpaceShipController;
import com.alten.spaceships.entity.SpaceShip;
import com.alten.spaceships.repository.SpaceShipRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
public class SpaceShipControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private SpaceShipRepository spaceShipRepository;

    private Long spaceShipId1;
    private Long spaceShipId2;

    @BeforeEach
    public void setUp() {
        spaceShipRepository.deleteAll();

        SpaceShip spaceShip1 = new SpaceShip();
        spaceShip1.setName("X-Wing");
        spaceShip1.setSerie("Star Wars");

        SpaceShip spaceShip2 = new SpaceShip();
        spaceShip2.setName("Millennium Falcon");
        spaceShip2.setSerie("Star Wars");

        spaceShipRepository.save(spaceShip1);
        spaceShipRepository.save(spaceShip2);

        spaceShipId1 = spaceShip1.getId();
        spaceShipId2 = spaceShip2.getId();
    }

    @Test
    public void givenSpaceships_whenGetSpaceShips_thenStatus200() throws Exception {
        mvc.perform(get("/api/spaceships/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("X-Wing")))
                .andExpect(jsonPath("$[1].name", is("Millennium Falcon")));
    }

    @Test
    public void givenSpaceships_whenGetSpaceShip_thenStatus200() throws Exception {
        mvc.perform(get("/api/spaceships/" + spaceShipId1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is("X-Wing")));
    }

    @Test
    public void givenNonExistingSpaceShip_whenGetSpaceShip_thenStatus404() throws Exception {
        mvc.perform(get("/api/spaceship/999")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void givenInvalidUrl_whenGetRequest_thenStatus404() throws Exception{
        mvc.perform(get("/api/invalidURL")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void givenInvalidSpaceShip_whenCreateSpaceShip_thenStatus400() throws Exception {
        String invalidSpaceShipJson = "{ \"name\": \"\", \"serie\": }";

        mvc.perform(post("/api/spaceships/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidSpaceShipJson))
                .andExpect(status().isBadRequest());
    }


}
