package com.alten.spaceships.controller;

import com.alten.spaceships.entity.SpaceShip;
import com.alten.spaceships.exception.ExistingSpaceShipException;
import com.alten.spaceships.exception.SpaceShipNotFoundException;
import com.alten.spaceships.service.SpaceShipService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/spaceships")
public class SpaceShipController {

    private final SpaceShipService spaceShipService;

    @Autowired
    SpaceShipController(SpaceShipService spaceShipService){
        this.spaceShipService = spaceShipService;
    }

    @GetMapping(
            value = "/",
            produces = "application/json"
    )
    public ResponseEntity<List<SpaceShip>> getAllSpaceShips(@RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "5") int size){
        List<SpaceShip> spaceShipList = spaceShipService.getAllSpaceShips(page, size);
        return spaceShipList.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(spaceShipList);
    }

    @GetMapping(
            value = "/{id}",
            produces = "application/json"
    )
    public ResponseEntity<SpaceShip> getSpaceShip(@PathVariable("id") Long id){
        return spaceShipService.getSpaceShipById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(
            value = "/search",
            produces = "application/json"
    )
    public ResponseEntity<List<SpaceShip>> getSpaceShipsByName(@RequestParam String name) {
        List<SpaceShip> spaceShipList = spaceShipService.getSpaceShipByContainingName(name);
        return spaceShipList.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(spaceShipList);
    }

    @PostMapping(
            value = "/create",
            produces = "application/json"
    )
    public ResponseEntity<Object> createSpaceShip(@RequestBody SpaceShip spaceShip) throws ExistingSpaceShipException {
        SpaceShip spaceShipCreated = spaceShipService.createSpaceShip(spaceShip);
        return ResponseEntity.status(HttpStatus.CREATED).body(spaceShipCreated);
    }

    @PutMapping(
            value = "/{id}",
            produces = "application/json"
    )
    public ResponseEntity<SpaceShip> updateSpaceShip(@PathVariable("id") Long id, @RequestBody SpaceShip spaceShip){
        return spaceShipService.updateSpaceShip(id, spaceShip)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(
            value = "/{id}",
            produces = "application/json"
    )
    public void deleteSpaceShip(@PathVariable("id") Long id ) throws SpaceShipNotFoundException {
        spaceShipService.deleteSpaceShip(id);
    }
}
