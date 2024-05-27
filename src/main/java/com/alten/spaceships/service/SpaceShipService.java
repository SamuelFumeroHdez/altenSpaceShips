package com.alten.spaceships.service;

import com.alten.spaceships.entity.SpaceShip;
import com.alten.spaceships.exception.ExistingSpaceShipException;
import com.alten.spaceships.exception.SpaceShipNotFoundException;
import com.alten.spaceships.repository.SpaceShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpaceShipService {
    private final SpaceShipRepository spaceShipRepository;

    @Autowired
    SpaceShipService(SpaceShipRepository spaceShipRepository){
        this.spaceShipRepository = spaceShipRepository;
    }

    public List<SpaceShip> getAllSpaceShips(int page, int size){
        return spaceShipRepository.findAll(PageRequest.of(page, size)).getContent();
    }

    @Cacheable("spaceships")
    public Optional<SpaceShip> getSpaceShipById(Long id){
        return spaceShipRepository.findById(id);
    }

    public List<SpaceShip> getSpaceShipByContainingName(String name){
        return spaceShipRepository.findByNameContaining(name);
    }

    public SpaceShip getSpaceShipByName(String name){
        return spaceShipRepository.findByName(name);
    }

    public SpaceShip createSpaceShip(SpaceShip spaceShip) throws ExistingSpaceShipException {
        SpaceShip spaceShipDB = getSpaceShipByName(spaceShip.getName());
        if(spaceShipDB != null){
            throw new ExistingSpaceShipException("Space ship already exists");
        }else{
            return spaceShipRepository.save(spaceShip);
        }
    }

    public Optional<SpaceShip> updateSpaceShip(Long id, SpaceShip updatedSpaceShip) {

        Optional<SpaceShip> spaceShipDB = getSpaceShipById(id);

        spaceShipDB.ifPresentOrElse(spaceShip -> {
            spaceShip.setName(updatedSpaceShip.getName());
            spaceShip.setSerie(updatedSpaceShip.getSerie());
            spaceShipRepository.save(spaceShip);
        }, () -> {
            throw new SpaceShipNotFoundException("Can't update an non-existent space ship");
        });

        return spaceShipDB;
    }

    public void deleteSpaceShip(Long id) throws SpaceShipNotFoundException {
        Optional<SpaceShip> spaceShipDB = Optional.ofNullable(getSpaceShipById(id).orElse(null));

        spaceShipDB.ifPresentOrElse(spaceShip -> {
            spaceShipRepository.deleteById(id);
        }, () -> {
            throw new SpaceShipNotFoundException("Can't delete an non-existent space ship");
        });

    }


}
