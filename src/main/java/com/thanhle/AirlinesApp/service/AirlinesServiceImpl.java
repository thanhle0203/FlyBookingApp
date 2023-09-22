package com.thanhle.AirlinesApp.service;

import com.thanhle.AirlinesApp.domain.Airlines;
import com.thanhle.AirlinesApp.repository.AirlinesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirlinesServiceImpl implements AirlinesService {

	
    @Autowired
    private AirlinesRepository airlinesRepository;

    public List<Airlines> getAllAirlines() {
        return airlinesRepository.findAll();
    }

    public Optional<Airlines> getAirlinesById(Long id) {
        return airlinesRepository.findById(id);
    }

    public Airlines saveAirlines(Airlines airlines) {
        return airlinesRepository.save(airlines);
    }

    public void deleteAirlines(Long id) {
        airlinesRepository.deleteById(id);
    }
}
