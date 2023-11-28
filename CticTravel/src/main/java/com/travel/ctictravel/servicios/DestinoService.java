package com.travel.ctictravel.servicios;

import com.travel.ctictravel.modelos.Destino;
import com.travel.ctictravel.repositorios.DestinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DestinoService {
    private final DestinoRepository destinoRepository;

    @Autowired
    public DestinoService(DestinoRepository destinoRepository) {
        this.destinoRepository = destinoRepository;
    }

    public List<Destino> obtenerTodosLosDestinos() {
        return destinoRepository.findAll();
    }

    public Destino obtenerDestinoPorId(Long id) {
        return destinoRepository.findById(id).orElse(null);
    }

    public Destino crearDestino(Destino destino) {
        return destinoRepository.save(destino);
    }

    public void eliminarDestino(Long id) {
        destinoRepository.deleteById(id);
    }

}
