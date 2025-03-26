package com.farmacia.pharma_manager.backend.despesa;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DespesaService {

    private final DespesaRepository despesaRepository;

    public DespesaService(DespesaRepository despesaRepository) {
        this.despesaRepository = despesaRepository;
    }

    public List<Despesa> listarTodas() {
        return despesaRepository.findAll();
    }

    public Optional<Despesa> buscarPorId(Integer id) {
        return despesaRepository.findById(id);
    }

    public Despesa salvar(Despesa despesa) {
        return despesaRepository.save(despesa);
    }

    public Despesa atualizar(Integer id, Despesa despesaAtualizada) {
        return despesaRepository.findById(id).map(despesa -> {
            despesa.setDescricao(despesaAtualizada.getDescricao());
            despesa.setData(despesaAtualizada.getData());
            despesa.setValor(despesaAtualizada.getValor());
            despesa.setGerente(despesaAtualizada.getGerente());
            return despesaRepository.save(despesa);
        }).orElseThrow(() -> new RuntimeException("Despesa não encontrada"));
    }

    public void deletar(Integer id) {
        despesaRepository.deleteById(id);
    }
}
