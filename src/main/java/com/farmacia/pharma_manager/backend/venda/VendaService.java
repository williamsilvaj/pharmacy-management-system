package com.farmacia.pharma_manager.backend.venda;

import com.farmacia.pharma_manager.backend.cliente.Cliente;
import com.farmacia.pharma_manager.backend.cliente.ClienteRepository;
import com.farmacia.pharma_manager.backend.farmaceutico.Farmaceutico;
import com.farmacia.pharma_manager.backend.farmaceutico.FarmaceuticoRepository;
import com.farmacia.pharma_manager.backend.item.Item;
import com.farmacia.pharma_manager.backend.item.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.PageRequest;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Map;
import java.util.HashMap;



@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    @Autowired
    private FarmaceuticoRepository farmaceuticoRepository;
    
    @Autowired
    private ItemRepository itemRepository;

    @Transactional(rollbackFor = Exception.class)
	public Venda criarVenda(Integer idCliente, Integer idFarmaceutico, List<Map<String, Object>> itensVenda) {
		// 1. Validação inicial
		Cliente cliente = clienteRepository.findById(idCliente)
				.orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
		Farmaceutico farmaceutico = farmaceuticoRepository.findById(idFarmaceutico)
				.orElseThrow(() -> new RuntimeException("Farmacêutico não encontrado"));

		// 2. Pré-validação de todos os itens
		Map<Integer, Integer> quantidadesPorProduto = new HashMap<>();
		for (Map<String, Object> itemMap : itensVenda) {
			Integer idProduto = (Integer) itemMap.get("idProduto");
			Integer quantidade = (Integer) itemMap.get("quantidade");
			quantidadesPorProduto.merge(idProduto, quantidade, Integer::sum);
		}

		// Verifica estoque para todos os produtos antes de processar
		for (Map.Entry<Integer, Integer> entry : quantidadesPorProduto.entrySet()) {
			int disponivel = itemRepository.somarQuantidadeNoEstoque(entry.getKey());
			if (disponivel < entry.getValue()) {
				throw new RuntimeException("Estoque insuficiente para produto: " + entry.getKey());
			}
		}
		
		// 3. Cria a venda
		Venda venda = new Venda();
		venda.setCliente(cliente);
		venda.setFarmaceutico(farmaceutico);
		venda.setData(LocalDate.now());
		venda.setQuantidade(0);
		venda.setValor(0.0);
		venda = vendaRepository.save(venda); // Persiste a venda primeiro

		// 4. Processa os itens
		int quantidadeTotal = 0;
		double valorTotal = 0.0;

		for (Map<String, Object> itemMap : itensVenda) {
			Integer idProduto = (Integer) itemMap.get("idProduto");
			Integer quantidade = (Integer) itemMap.get("quantidade");

			// Busca itens disponíveis (com paginação para evitar problemas de lock)
			List<Item> itensDisponiveis = itemRepository.findByProdutoIdProdutoAndVendaIsNull(
				idProduto, PageRequest.of(0, quantidade));

			// Associa itens à venda
			for (Item item : itensDisponiveis) {
				item.setVenda(venda);
				itemRepository.save(item);
				
				valorTotal += item.getValor();
				quantidadeTotal++;
			}
		}

		// 5. Atualiza totais da venda
		venda.setQuantidade(quantidadeTotal);
		venda.setValor(valorTotal);
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

	
	@Transactional
	public void desfazerVenda(Integer idVenda) {
		// 1. Busca a venda
		Venda venda = vendaRepository.findById(idVenda)
				.orElseThrow(() -> new RuntimeException("Venda não encontrada"));
		
		// 2. Remove a associação dos itens com esta venda
		List<Item> itens = itemRepository.findByVendaIdVenda(idVenda);
		for (Item item : itens) {
			item.setVenda(null);
			itemRepository.save(item);
		}
		
		// 3. Remove a venda
		vendaRepository.delete(venda);
	}
}