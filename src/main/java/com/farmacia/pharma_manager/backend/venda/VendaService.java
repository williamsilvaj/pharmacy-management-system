package com.farmacia.pharma_manager.backend.venda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    // Criar nova venda
    public Venda criarVenda(Venda venda) {
        return vendaRepository.save(venda);
    }

    // Listar todas as vendas
    public List<Venda> listarVendas() {
        return vendaRepository.findAll();
    }

    // Buscar venda por ID
    public Optional<Venda> obterVendaPorId(Integer id) {
        return vendaRepository.findById(id);
    }

    // Atualizar venda
    public Venda atualizarVenda(Integer id, Venda venda) {
        if (vendaRepository.existsById(id)) {
            venda.setIdVenda(id);  // Garantir que o ID correto seja usado
            return vendaRepository.save(venda);
        }
        return null;
    }

    // Deletar venda
    public boolean deletarVenda(Integer id) {
        if (vendaRepository.existsById(id)) {
            vendaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}