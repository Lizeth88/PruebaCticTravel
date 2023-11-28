package com.travel.ctictravel.repositorios;

import com.travel.ctictravel.modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//Esto ayuda a Spring a reconocerlo como un componente de repositorio y proporciona una mejor integración con Spring
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Consulta personalizada para encontrar usuarios por nombre
    List<Usuario> findByNombre(String nombre);
    // `findByNombre` generará una consulta que busca usuarios por su nombre

    Boolean existsByEmail(String email);

    Optional<Usuario> findByEmail(String email);
}
