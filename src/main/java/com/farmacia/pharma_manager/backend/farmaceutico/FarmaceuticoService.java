package com.farmacia.pharma_manager.backend.farmaceutico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FarmaceuticoService {

    private final FarmaceuticoRepository farmaceuticoRepository;

    @Autowired
    public FarmaceuticoService(FarmaceuticoRepository farmaceuticoRepository) {
        this.farmaceuticoRepository = farmaceuticoRepository;
    }

    // Salvar ou atualizar um Farmaceutico
    public Farmaceutico saveOrUpdateFarmaceutico(Farmaceutico farmaceutico) {
        return farmaceuticoRepository.save(farmaceutico);
    }

    // Listar todos os Farmaceuticos
    public List<Farmaceutico> getAllFarmaceuticos() {
        return farmaceuticoRepository.findAll();
    }

    // Buscar um Farmaceutico pelo ID
    public Optional<Farmaceutico> getFarmaceuticoById(Integer id) {
        return farmaceuticoRepository.findById(id);
    }

    // Deletar um Farmaceutico pelo ID
    public void deleteFarmaceutico(Integer id) {
        farmaceuticoRepository.deleteById(id);
    }
}
