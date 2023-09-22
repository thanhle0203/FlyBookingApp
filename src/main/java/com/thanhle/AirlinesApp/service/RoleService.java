package com.thanhle.AirlinesApp.service;

import com.thanhle.AirlinesApp.domain.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    List<Role> findAll();
    Optional<Role> findById(Long id);
    Role save(Role role);
    void deleteById(Long id);
}
