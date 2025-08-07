package com.neurotechR3.inventory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LaptopRepository extends JpaRepository<LapTop, String> {
    Optional<LapTop> findByPcNumber(String pcNumber);

    void deleteByPcNumber(String pcNumber);
}