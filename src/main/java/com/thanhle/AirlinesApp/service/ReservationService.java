package com.thanhle.AirlinesApp.service;

import com.thanhle.AirlinesApp.domain.Reservation;
import java.util.List;

public interface ReservationService {
    List<Reservation> findAll();
    Reservation findById(Long id);
    void save(Reservation reservation);
    void deleteById(Long id);
}
