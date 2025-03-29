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
    public Optional<Venda> buscarVendaPorId(Integer id) {
        return vendaRepository.findById(id);
    }

    // Atualizar venda
    public Venda atualizarVenda(Integer id, Venda venda) {
        if (vendaRepository.existsById(id)) {
            venda.setIdVenda(id);
            return vendaRepository.save(venda);
        }
        return null; // Ou lançar uma exceção se não encontrado
    }

    // Deletar venda
    public void deletarVenda(Integer id) {
        vendaRepository.deleteById(id);
    }
}
