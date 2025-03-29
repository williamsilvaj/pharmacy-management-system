package com.farmacia.pharma_manager.backend.venda;

import com.farmacia.pharma_manager.backend.cliente.Cliente;
import com.farmacia.pharma_manager.backend.cliente.ClienteRepository;
import com.farmacia.pharma_manager.backend.farmaceutico.Farmaceutico;
import com.farmacia.pharma_manager.backend.farmaceutico.FarmaceuticoRepository;
import com.farmacia.pharma_manager.backend.item.Item;
import com.farmacia.pharma_manager.backend.item.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;



@Controller
@RequestMapping("/vendas")
public class VendaController {

    @Autowired
    private VendaService vendaService;
	
	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private FarmaceuticoRepository farmaceuticoRepository;

	@Autowired
	private ItemRepository itemRepository;

    /**
     * Metodo para redirecionar para a página de vendas.
     */
    @GetMapping("/pagina")
    public String redirecionarParaVendaPage() {
        return "venda/venda";  // Nome do arquivo venda.html na pasta resources/templates
    }

    /**
     * Endpoint para criar uma nova venda.
     *
     * @param venda Venda a ser criada.
     * @return A venda criada.
     */
    @PostMapping
    @Transactional
    public ResponseEntity<?> criarVenda(@RequestBody Map<String, Object> requestBody) {
        try {
			// Validação básica
            Integer idCliente = (Integer) requestBody.get("idCliente");
            Integer idFarmaceutico = (Integer) requestBody.get("idFarmaceutico");
            List<Map<String, Object>> itensVenda = (List<Map<String, Object>>) requestBody.get("itens");
            
            if (idCliente == null || idFarmaceutico == null || itensVenda == null) {
                return ResponseEntity.badRequest().body("Dados incompletos");
            }

            // Delega para o service
            Venda vendaCriada = vendaService.criarVenda(idCliente, idFarmaceutico, itensVenda);
			return ResponseEntity.status(HttpStatus.CREATED).body(vendaCriada);
            
        } catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro: " + e.getMessage());
        }
    }

    /**
     * Endpoint para listar todas as vendas.
     *
     * @return Lista de vendas.
     */
    @GetMapping
    public ResponseEntity<List<Venda>> listarVendas() {
        List<Venda> vendas = vendaService.listarVendas();
        return new ResponseEntity<>(vendas, HttpStatus.OK);
    }

    /**
     * Endpoint para obter uma venda pelo ID.
     *
     * @param id ID da venda.
     * @return A venda encontrada.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Venda> obterVendaPorId(@PathVariable Integer id) {
        Optional<Venda> venda = vendaService.obterVendaPorId(id);
        return venda.map(ResponseEntity::ok)
                     .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Endpoint para atualizar uma venda existente.
     *
     * @param id    ID da venda a ser atualizada.
     * @param venda Venda com as novas informações.
     * @return A venda atualizada.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Venda> atualizarVenda(@PathVariable Integer id, @RequestBody Venda venda) {
        Venda vendaAtualizada = vendaService.atualizarVenda(id, venda);
        if (vendaAtualizada != null) {
            return new ResponseEntity<>(vendaAtualizada, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Endpoint para desfazer uma venda pelo ID.
     *
     * @param id ID da venda a ser deletada.
     * @return Status de sucesso ou erro.
     */
	@PostMapping("/{id}/desfazer")
	@Transactional
	public ResponseEntity<?> desfazerVenda(@PathVariable Integer id) {
		try {
			vendaService.desfazerVenda(id);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Erro ao desfazer venda: " + e.getMessage());
		}
	}
}