package com.farmacia.pharma_manager.backend.venda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vendas")
public class VendaController {

    @Autowired
    private VendaService vendaService;

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
    public ResponseEntity<Venda> criarVenda(@RequestBody Venda venda) {
        Venda novaVenda = vendaService.criarVenda(venda);
        return new ResponseEntity<>(novaVenda, HttpStatus.CREATED);
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
     * Endpoint para deletar uma venda pelo ID.
     *
     * @param id ID da venda a ser deletada.
     * @return Status de sucesso ou erro.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarVenda(@PathVariable Integer id) {
        if (vendaService.deletarVenda(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}