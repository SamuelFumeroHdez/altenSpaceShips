package com.alten.spaceships.repository;

import com.alten.spaceships.entity.SpaceShip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Repository
public interface SpaceShipRepository extends JpaRepository<SpaceShip, Long> {
    List<SpaceShip> findByNameContaining(String name);
    SpaceShip findByName(String name);
}
