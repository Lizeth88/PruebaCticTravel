package com.travel.ctictravel.servicios;

import com.travel.ctictravel.modelos.Role;
import com.travel.ctictravel.repositorios.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Optional<Role> findRoleByNombre(String nombre){ return roleRepository.findRoleByNombre(nombre);}



}
