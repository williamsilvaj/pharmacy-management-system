package com.farmacia.pharma_manager.backend.farmaceutico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Serviço de lógica de negócios para a entidade Farmaceutico.
 * Contém os métodos que interagem com o repositório para manipular dados de farmacêuticos.
 */
@Service
public class FarmaceuticoService {

    @Autowired
    private FarmaceuticoRepository farmaceuticoRepository;

    /**
     * Cria um novo farmacêutico no banco de dados.
     *
     * @param farmaceutico o farmacêutico a ser criado
     * @return o farmacêutico criado
     */
    public Farmaceutico criarFarmaceutico(Farmaceutico farmaceutico) {
        return farmaceuticoRepository.save(farmaceutico);
    }

    /**
     * Obtém um farmacêutico pelo seu ID.
     *
     * @param id o ID do farmacêutico
     * @return um Optional contendo o farmacêutico, caso encontrado
     */
    public Optional<Farmaceutico> obterFarmaceuticoPorId(Integer id) {
        return farmaceuticoRepository.findById(id);
    }

    /**
     * Atualiza os dados de um farmacêutico existente.
     *
     * @param id o ID do farmacêutico a ser atualizado
     * @param farmaceutico os novos dados do farmacêutico
     * @return o farmacêutico atualizado
     */
    public Farmaceutico atualizarFarmaceutico(Integer id, Farmaceutico farmaceutico) {
        if (farmaceuticoRepository.existsById(id)) {
            farmaceutico.setIdFarmaceutico(id);
            return farmaceuticoRepository.save(farmaceutico);
        }
        return null; // Retorna null caso o farmacêutico não exista
    }

    /**
     * Deleta um farmacêutico pelo seu ID.
     *
     * @param id o ID do farmacêutico a ser deletado
     */
    public void deletarFarmaceutico(Integer id) {
        farmaceuticoRepository.deleteById(id);
    }
}
