package com.travel.ctictravel.servicios.Impl;


import com.travel.ctictravel.dao.JwtAuthenticationResponse;
import com.travel.ctictravel.dao.request.SignUpRequest;
import com.travel.ctictravel.dao.request.SigninRequest;
import com.travel.ctictravel.modelos.Role;
import com.travel.ctictravel.modelos.Usuario;
import com.travel.ctictravel.repositorios.RoleRepository;
import com.travel.ctictravel.repositorios.UsuarioRepository;
import com.travel.ctictravel.servicios.AuthenticationService;
import com.travel.ctictravel.servicios.JwtService;
import com.travel.ctictravel.servicios.RoleService;
import lombok.RequiredArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService{

    private final UsuarioRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;



    @Override
    public JwtAuthenticationResponse signup(SignUpRequest request) {
      // Verificar si el correo electrónico ya existe en la base de datos
      if (userRepository.existsByEmail(request.getEmail())) {
        throw new RuntimeException("Error al registrar el usuario. El correo electrónico ya está en uso.");
      }

      Role role = roleService.findRoleByNombre("ADMIN").orElseThrow(() -> new RuntimeException("No se pudo encontrar el rol con el nombre 'ADMIN' en la base de datos."));
      var user = Usuario.builder()
              .nombre(request.getNombre())
              .email(request.getEmail())
              .password(passwordEncoder.encode(request.getPassword()))
              .roles(Collections.singleton(role)).build();
      userRepository.save(user);
      var jwt = jwtService.generateToken(user);
      return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signin(SigninRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalido correo o contraseña."));
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}
