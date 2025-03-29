package com.farmacia.pharma_manager.backend.venda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    public Venda criarVenda(Venda venda) {
        return vendaRepository.save(venda);
    }

    public List<Venda> listarVendas() {
        return vendaRepository.findAll();
    }

    public Optional<Venda> obterVendaPorId(Integer id) {
        return vendaRepository.findById(id);
    }

    public Venda atualizarVenda(Integer id, Venda vendaAtualizada) {
        return vendaRepository.findById(id).map(venda -> {
            venda.setNomeProduto(vendaAtualizada.getNomeProduto());
            venda.setQuantidade(vendaAtualizada.getQuantidade());
            venda.setValor(vendaAtualizada.getValor());
            venda.setData(vendaAtualizada.getData());
            venda.setFarmaceutico(vendaAtualizada.getFarmaceutico());
            venda.setCliente(vendaAtualizada.getCliente());
            return vendaRepository.save(venda);
        }).orElse(null);
    }

    public boolean deletarVenda(Integer id) {
        if (vendaRepository.existsById(id)) {
            vendaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
