package com.farmacia.pharma_manager.backend.gerente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class GerenteService {

    @Autowired
    private GerenteRepository gerenteRepository;

    public Gerente criarGerente(Gerente gerente) {
        return gerenteRepository.save(gerente);
    }

    public List<Gerente> obterTodosGerentes() {
        return gerenteRepository.findAll();
    }

    public Optional<Gerente> obterGerentePorId(Integer id) {
        return gerenteRepository.findById(id);
    }

    public Gerente atualizarGerente(Integer id, Gerente gerenteAtualizado) {
        Optional<Gerente> gerenteExistente = gerenteRepository.findById(id);
        if (gerenteExistente.isPresent()) {
            Gerente gerente = gerenteExistente.get();
            gerente.setNome(gerenteAtualizado.getNome());
            gerente.setTelefone(gerenteAtualizado.getTelefone());
            gerente.setCpf(gerenteAtualizado.getCpf());
            gerente.setCargo(gerenteAtualizado.getCargo());
            gerente.setEndereco(gerenteAtualizado.getEndereco());
            gerente.setFuncionariosSupervisionados(gerenteAtualizado.getFuncionariosSupervisionados());
            return gerenteRepository.save(gerente);
        } else {
            throw new IllegalArgumentException("Gerente não encontrado para o id: " + id);
        }
    }

    public void deletarGerente(Integer id) {
        gerenteRepository.deleteById(id);
    }
}
