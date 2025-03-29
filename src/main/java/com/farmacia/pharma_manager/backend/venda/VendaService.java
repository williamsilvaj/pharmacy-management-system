package com.farmacia.pharma_manager.backend.venda;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendaService {

    private final VendaRepository vendaRepository;

    public VendaService(VendaRepository vendaRepository) {
        this.vendaRepository = vendaRepository;
    }

    public List<Venda> listarTodas() {
        return vendaRepository.findAll();
    }

    public Optional<Venda> buscarPorId(Integer id) {
        return vendaRepository.findById(id);
    }

    public Venda salvar(Venda venda) {
        return vendaRepository.save(venda);
    }

    public Venda atualizar(Integer id, Venda vendaAtualizada) {
        return vendaRepository.findById(id).map(venda -> {
            venda.setNomeProduto(vendaAtualizada.getNomeProduto());
            venda.setQuantidade(vendaAtualizada.getQuantidade());
            venda.setValor(vendaAtualizada.getValor());
            venda.setData(vendaAtualizada.getData());
            venda.setFarmaceutico(vendaAtualizada.getFarmaceutico());
            venda.setCliente(vendaAtualizada.getCliente());
            venda.setEndereco(vendaAtualizada.getEndereco());
            return vendaRepository.save(venda);
        }).orElseThrow(() -> new RuntimeException("Venda não encontrada"));
    }

    public void deletar(Integer id) {
        vendaRepository.deleteById(id);
    }
}
