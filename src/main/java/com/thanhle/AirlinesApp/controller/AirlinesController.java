package com.thanhle.AirlinesApp.controller;

import com.thanhle.AirlinesApp.domain.Airlines;
import com.thanhle.AirlinesApp.service.AirlinesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airlines")
public class AirlinesController {

    private final AirlinesService airlinesService;

    @Autowired
    public AirlinesController(AirlinesService airlinesService) {
        this.airlinesService = airlinesService;
    }

    @GetMapping
    public List<Airlines> getAllAirlines() {
        return airlinesService.getAllAirlines();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Airlines> getAirlinesById(@PathVariable Long id) {
        return airlinesService.getAirlinesById(id)
                .map(airlines -> ResponseEntity.ok(airlines))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Airlines createAirlines(@RequestBody Airlines airlines) {
        return airlinesService.saveAirlines(airlines);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Airlines> updateAirlines(@PathVariable Long id, @RequestBody Airlines updatedAirlines) {
        if (!airlinesService.getAirlinesById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        updatedAirlines.setAirlineId(id); // Ensure the ID is set to the path variable ID
        return ResponseEntity.ok(airlinesService.saveAirlines(updatedAirlines));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAirlines(@PathVariable Long id) {
        if (!airlinesService.getAirlinesById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        airlinesService.deleteAirlines(id);
        return ResponseEntity.noContent().build();
    }
}
