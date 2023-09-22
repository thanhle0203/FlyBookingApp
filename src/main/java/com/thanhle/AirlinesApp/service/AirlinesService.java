package com.thanhle.AirlinesApp.service;

import java.util.List;
import java.util.Optional;

import com.thanhle.AirlinesApp.domain.Airlines;

public interface AirlinesService {
	public List<Airlines> getAllAirlines();
	Optional<Airlines> getAirlinesById(Long id);
	public Airlines saveAirlines(Airlines airlines);
	public void deleteAirlines(Long id);

}
