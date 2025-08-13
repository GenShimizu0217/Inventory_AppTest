package com.neurotechR3.inventory;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/LapTop")
public class LapTopController {

    @Autowired
    private LaptopRepository laptopRepo;

    // Add a new laptop
    @PostMapping
    public String addLaptop(@RequestBody LapTop newLaptop) {
        // Make sure we log the first user if it exists
        if (newLaptop.getUserID() != null && !newLaptop.getUserID().isEmpty()) {
            newLaptop.updateHistory(newLaptop.getUserID());
        }

        laptopRepo.save(newLaptop);
        return "Laptop added successfully!";
    }

    // Get all laptops
    @GetMapping
    public List<LapTop> getAllLaptops() {
        return laptopRepo.findAll();
    }

    // Get laptop by PC number
    @GetMapping("/{pcNumber}")
    public LapTop getLaptop(@PathVariable String pcNumber) {
        return laptopRepo.findByPcNumber(pcNumber).orElse(null);
    }

    // Update a laptop
    @PutMapping("/{pcNumber}")
    public String updateLaptop(@PathVariable String pcNumber, @RequestBody LapTop updatedLaptop) {
        Optional<LapTop> optional = laptopRepo.findByPcNumber(pcNumber);
        if (optional.isPresent()) {
            LapTop laptop = optional.get();

            // Copy updated info and save
            laptop.setProcured(updatedLaptop.getProcured());
            laptop.setDeviceInfo(updatedLaptop.getDeviceInfo());
            laptop.setLocation(updatedLaptop.getLocation());
            laptop.setSerialNum(updatedLaptop.getSerialNum());
            laptop.setPrice(updatedLaptop.getPrice());
            laptop.setNotes(updatedLaptop.getNotes());
            laptop.setEmail(updatedLaptop.getEmail());
            laptop.setPassword(updatedLaptop.getPassword());
            laptop.setAnyDesk(updatedLaptop.getAnyDesk());
            laptop.setR3thaVersion(updatedLaptop.getR3thaVersion());
            laptop.setLastUpdate(updatedLaptop.getLastUpdate());
            laptop.setStatus(updatedLaptop.getStatus());

            // This handles userID + history update
            laptop.updateHistory(updatedLaptop.getUserID());

            // Save with updated history
            laptopRepo.save(laptop);
            return "Laptop updated!";
        } else {
            return "Laptop not found.";
        }
    }

    // Delete a laptop
    @DeleteMapping("/{pcNumber}")
    @Transactional
    public String deleteLaptop(@PathVariable String pcNumber) {
        laptopRepo.deleteByPcNumber(pcNumber);
        return "Laptop deleted!";
    }

}
