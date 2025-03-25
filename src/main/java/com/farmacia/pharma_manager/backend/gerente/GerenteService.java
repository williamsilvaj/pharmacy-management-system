package com.farmacia.pharma_manager.backend.gerente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GerenteService {

    @Autowired
    private GerenteRepository gerenteRepository;

    /**
     * Updates an existing Gerente (Manager) by its ID.
     *
     * @param id      the ID of the Gerente to update
     * @param gerente the updated Gerente object
     * @return the updated Gerente object, or null if the Gerente is not found
     */
    public Gerente atualizarGerente(Integer id, Gerente gerente) {
        // Check if the Gerente with the given id exists in the database
        Optional<Gerente> gerenteExistente = gerenteRepository.findById(id);

        if (gerenteExistente.isPresent()) {
            // If the Gerente exists, update its fields
            Gerente gerenteAtualizado = gerenteExistente.get();
            gerenteAtualizado.setNivel(gerente.getNivel());  // Update the 'nivel'
            gerenteAtualizado.setFuncionariosSupervisionados(gerente.getFuncionariosSupervisionados());  // Update 'funcionariosSupervisionados'

            // Save the updated Gerente back to the database
            return gerenteRepository.save(gerenteAtualizado);
        } else {
            // If the Gerente doesn't exist, return null (or you could throw an exception)
            return null;
        }
    }

    /**
     * Creates a new Gerente (Manager) in the database.
     *
     * @param gerente the Gerente to be created
     * @return the created Gerente object
     */
    public Gerente criarGerente(Gerente gerente) {
        return gerenteRepository.save(gerente);
    }

    /**
     * Retrieves a Gerente by its ID.
     *
     * @param id the ID of the Gerente
     * @return the Gerente object, or null if not found
     */
    public Optional<Gerente> obterGerentePorId(Integer id) {
        return gerenteRepository.findById(id);
    }

    /**
     * Deletes a Gerente by its ID.
     *
     * @param id the ID of the Gerente to delete
     */
    public void deletarGerente(Integer id) {
        gerenteRepository.deleteById(id);
    }
}
